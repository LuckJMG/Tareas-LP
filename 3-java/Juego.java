import java.util.Scanner;

public class Juego {
	public static Scanner input;
	private static int turno;
	private static enum Direccion {
		IZQUIERDA,
		CENTRO,
		DERECHA,
	}

	/*
	 * Nombre: main
	 *
	 * Descripcion: Loop principal del programa.
	 *
	 * Parametros:
	 * - String[] args, no usados
	 */
	public static void main(String[] args) {
		// Inicialización del juego
		Juego.input = new Scanner(System.in);

		Zona[] mapa = {
				new Pieza(50),
				new Enemigo(130, 20, 25),
				new Enemigo(50, 10, 15),
				new Pildora(25),
				new Muralla(50),
				new Pieza(100),
				new Enemigo(45, 8, 10),
				new Pieza(35),
				new Pildora(15),
				new Enemigo(75, 15, 20),
				new Muralla(150),
		};

		Pikinim[] pikinims = {
			new Cyan(),
			new Magenta(),
			new Amarillo(),
		};

		Juego.turno = 0;
		int posicion = 5;
		Direccion direccion = Direccion.IZQUIERDA;
		Direccion noPuedePasar = Direccion.CENTRO;

		// Introducción
		System.out.println(
			  "\n\n=== MAL ATERRIZAJE ===\n\n"
			+ "> Choqué con un asteroide en un viaje de rutina a comprar leche para mis hijos\n"
			+ "> y ahora me encuentro solo en un planeta extraño.\n"
			+ "> Mi nave está destrozada y no sé a dónde fueron a parar sus piezas.\n"
			+ "> Por suerte he encontrado unas criaturas curiosas, a lo mejor pueden ayudarme a volver a casa.\n"
			+ "> (Convenientemente para el plot) Se ven emocionados de conocerme.\n"
			+ "> Los llamaré pikinims (nombre protegido bajo las leyes de copyright sansanas ©),\n"
			+ "> porque cada uno puede levantar exactamente un kilopikinim de peso.\n"
			+ "> Debo ser veloz, no queda tanto para que se acabe el tanque de oxígeno, solo me quedan 30 horas.\n"
		);

		System.out.println(
			  "> Comentario de Lomiar\n"
			+ "| Información del turno\n"
			+ ": Narrador / Información de la Zona\n"
			+ "! Acción\n"
			+ "? Elección"
		);

		// Loop principal de juego
		while (true) {
			// Interactuar con la zona actual
			System.out.println("\n----------------------------------------------------------------------\n");
			mapa[posicion].interactuar(pikinims);
			System.out.println("");

			// Chequear si se termino el juego
			if (checkEndings(mapa, pikinims)) {
				break;
			}

			// Mostrar datos del turno actual
			Zona zonaActual = mapa[posicion];
			mostrarDatos(pikinims, zonaActual);

			// Elección de en que gastar el turno
			System.out.println("? Hacia donde quieres ir?");

			// Mostrar movimientos disponibles
			String zonaIzquierda = mapa[Juego.wrapIndex(posicion - 1, 0, mapa.length)].getInfo();
			String zonaDerecha = mapa[Juego.wrapIndex(posicion + 1, 0, mapa.length)].getInfo();

			// Chequeo de murallas
			if (zonaActual.getClass().getName() == "Muralla" && !zonaActual.isCompletada()) {
				if (direccion == Direccion.IZQUIERDA) {
					// Bloqueo a la izquierda
					System.out.println(
						  "? La muralla bloquea tu paso a la izquierda.\n"
						+ "? 2. Derecha (" + zonaDerecha + ")   3. Quedarse aquí"
					);
					noPuedePasar = Direccion.IZQUIERDA;
				} else if (direccion == Direccion.DERECHA) {
					// Bloqueo a la derecha
					System.out.println(
						  "? La muralla bloquea tu paso a la derecha.\n"
						+ "? 1. Izquierda (" + zonaIzquierda + ")   3. Quedarse aquí"
					);
					noPuedePasar = Direccion.DERECHA;
				}
			} else {
				// No hay bloqueos
				System.out.println(
					"? 1. Izquierda (" + zonaIzquierda + ")   2. Derecha ("
						+ zonaDerecha + ")   3. Quedarse aquí"
				);
				noPuedePasar = Direccion.CENTRO;
			}

			// Input usuario
			System.out.print("? Elección: ");
			int eleccion = input.nextInt();

			// Movimiento en el mapa y circunavegación
			if (eleccion == 1 && noPuedePasar != Direccion.IZQUIERDA) {
				posicion = wrapIndex(posicion - 1, 0, mapa.length);
				direccion = Direccion.IZQUIERDA;
			} else if (eleccion == 2 && noPuedePasar != Direccion.DERECHA) {
				posicion = wrapIndex(posicion + 1, 0, mapa.length);
				direccion = Direccion.DERECHA;
			}

			Juego.turno++;
		}

		// Pantalla final de puntaje
		int cantidadTotal = 0;
		for (Pikinim color : pikinims) {
			cantidadTotal += color.getCantidad();
		}

		System.out.println(
			  "======================================================================\n"
			+ "| PUNTAJE OBTENIDO\n"
			+ "======================================================================\n"
			+ "| Horas de Oxigeno Restante: " + (30 - Juego.turno) + " horas\n"
			+ "| Piezas Encontradas: " + Pieza.piezasEncontradas + "/3\n"
			+ "| Zonas Completadas: " + Zona.getZonasCompletadas() + "/11\n"
			+ "| Pikinims: " + cantidadTotal
		);

		for (Pikinim color : pikinims) {
			System.out.println(
				"| - " + color.getCantidad() + " " + color.getClass().getName()
			);
		}

		System.out.println("======================================================================");

		Juego.input.close();
	}

	/*
	 * Nombre: wrapIndex
	 *
	 * Descripcion: Funcion para mantener los index que se le pasen dentro de
	 * los limites prestablecidos, usada para poder circunavegar el mapa de
	 * manera segura. Si se le pasa un index por debajo del limite inferior,
	 * devuelve el mayor index valido, mientras que si se le pasa un index por
	 * encima de limite superior, devuelve el limite inferior, si esta dentro de
	 * los limites devuelve el mismo index.
	 *
	 * Parametros:
	 * - int index, index a verificar
	 * - int bottomLimit, limite inferior del rango
	 * - int topLimit, limite superior del rango
	 *
	 * Returns:
	 * - int, index circunavegado
	 */
	private static int wrapIndex(int index, int bottomLimit, int topLimit) {
		if (index >= topLimit) {
			return bottomLimit;
		}

		if (index < bottomLimit) {
			return topLimit - 1;
		}

		return index;
	}

	/*
	 * Nombre: mostrarDatos
	 *
	 * Descripcion: Muestra los datoa del turno actual, esto incluye, cuantos
	 * turnos quedan, la cantidad de pikinims con su respectiva informacion, la
	 * cantidad de piezas encontradas, la cantidad de zonas completadas e info
	 * de la zona actual.
	 *
	 * Parametros:
	 * - Pikinim[] pikinims, array de pikinims del turno actual
	 * - Zona zona, zona actual del turno
	 */
	private static void mostrarDatos(Pikinim[] pikinims, Zona zona) {
		System.out.print(
			  "| Horas de Oxigeno Restante: " + (30 - Juego.turno) + " horas\n"
			+ "| Pikinims:\n"
		);

		for (Pikinim color : pikinims) {
			System.out.println(
				"| - (ATK " + color.getAtaque() + " | CAP "
					+ color.getCapacidad() + ") " + color.getClass().getName()
					+ ": " + color.getCantidad()
			);
		}

		System.out.println(
			  "| Piezas Encontradas: " + Pieza.piezasEncontradas + "/3\n"
			+ "| Zonas Completadas: " + Zona.getZonasCompletadas() + "/11\n"
			+ "| Zona Actual: " + zona.getInfo() + "\n"
		);
	}

	/*
	 * Nombre: checkEndings
	 *
	 * Descripcion: Funcion con el solo proposito de chequear si se ha llegado
	 * a uno de los finales programados, chequea todas las condiciones para
	 * terminar (buenas y malas), si alguna es verdadera, devuelve verdadero
	 * que significa que termino el juego.
	 *
	 * Parametros:
	 * - Zona[] mapa, mapa del juego
	 * - Pikinim[] pikinims, array de pikinims del turno actual
	 *
	 * Returns:
	 * - boolean, verdadero si finalizo el juego; falso si no
	 */
	private static boolean checkEndings(Zona[] mapa, Pikinim[] pikinims) {
		// Finales
		/// Perfect Ending (Completadas todas las zonas)
		if (Zona.getZonasCompletadas() == mapa.length) {
			System.out.println(
				  ": Increible!!! Has completado todas las zonas del juego.\n"
				+ ": No pense que podrias ganar, menos que consiguieras este final!\n"
				+ ": Pero bueno, te mereces unas felicitaciones por completar al 100% el juego.\n"
				+ ": FELICIDADES POR COMPLETARLO AL 100%!!!\n"
				+ "\n\n=== YOU WIN - 100% ===\n\n"
			);
			return true;
		}

		if (Pieza.piezasEncontradas >= 3) {
			/// Secret Ending (Speedrun)
			if (Juego.turno <= 11) {
				System.out.println(
					  ": Wow, has encontrado todas las piezas en tiempo record!\n"
					+ ": Tienes algo que hacer que estas tan apurado?\n"
					+ ": No te metas al mundo del speedrun, no es bueno para la salud.\n"
					+ ": Pero por mientras, tus merecidas felicitaciones.\n"
					+ ": FELICIDADES POR COMPLETARLO EN TIEMPO RECORD!!!\n"
					+ "\n\n=== YOU WIN - SPEEDRUN ===\n\n"
					);
					return true;
			}

			/// Good Ending (Conseguiste escapar a tiempo)
			System.out.println(
				  ": Has encontrado todas las piezas!!!\n"
				+ ": Quien diria que lograrias conseguirlas a tiempo.\n"
				+ ": Dejas abandonados a los pikinims que hicieron la mayor parte del trabajo.\n"
				+ ": Ahora por fín podrás ir a comprar la leche para tus hijos y volver para el desayuno.\n"
				+ "\n\n=== YOU WIN - ESCAPED ===\n\n"
			);
			return true;
		}

		/// Bad Ending 1 (Se acabaron las 30 horas)
		if (Juego.turno >= 30) {
			System.out.println(
				  ": Lomiar se ha quedado sin oxigeno y ha muerto.\n"
				+ ": Ahora su cuerpo quedará en este planeta por siempre, y sus hijos nunca volveran a saber de el.\n"
				+ ": Lo ultimo que recordaran fue que les dijo que iba a salir a comprar leche.\n"
				+ "\n\n=== GAME OVER - DEAD ===\n\n"
			);
			return true;
		}

		int cantidadTotal = 0;
		for (Pikinim color : pikinims) {
			cantidadTotal += color.getCantidad();
		}

		if (cantidadTotal == 0) {
			/// Bad Ending 2 (No te quedan pikinims)
			System.out.println(
				  ": Acabaste por matar hasta el último de tus pequeños compañeros.\n"
				+ ": Solo por querer llegar una hora antes a tu casa.\n"
				+ ": Reflexiona lo que hiciste, porque no solo perdiste el juego.\n"
				+ ": También perdiste valiosos compañeros.\n"
				+ "\n\n=== GAME OVER - ALONE ===\n\n"
			);
			return true;
		} else if (cantidadTotal >= 250) {
			/// Secret Ending (Sobrepoblacion de pikinims)
			System.out.println(
				  ": Has hecho que los pikinims crezcan demasiado en número.\n"
				+ ": Están empezando a agruparse y a tomar el liderazgo por ellos mismos.\n"
				+ ": Se dieron cuenta de que ya no te necesitan, que ahora solo les perjudicas.\n"
				+ ": Los pikinims te han matado, para sobre tí formar un nuevo gobierno, mejor que cualquier otro.\n"
				+ ": Tu sacrificio no ha sido en vano, gracias a tí los pikinims han formado la Unión de Rojos amarilloS y Syan (URSS).\n"
				+ ": Ahora rearmaran tu nave para expandir su influencia, a las buenas y a las malas, a traves del cosmos!\n"
				+ ": VIVA LA URSS!!!\n"
				+ "\n\n=== WE WIN - URSS ===\n\n"
			);
			return true;
		}

		return false;
	}
}
