import java.util.Scanner;

public class Juego {
	public static Scanner input;

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

		Cyan cyan = new Cyan();
		Magenta magenta = new Magenta();
		Amarillo amarillo = new Amarillo();

		int posicion = 5;
		int turno = 0;
		int direccion = 0; // 1 izqueirda, 2 derecha
		int noPuedePasar = 0;
		// 0 se puede pasar
		// 1 no se puede pasar por la izquierda
		// 2 no se puede pasar por la derecha

		// Introducción
		System.out.println(
			  "> MAL ATERRIZAJE\n"
			+ "> Choqué con un asteroide en un viaje de rutina a comprar leche para mis hijos y ahora me encuentro solo en un planeta extraño.\n"
			+ "> Mi nave está destrozada y no sé a dónde fueron a parar sus piezas.\n"
			+ "> Por suerte he encontrado unas criaturas curiosas, a lo mejor pueden ayudarme a volver a casa.\n"
			+ "> (Convenientemente para el plot) Se ven emocionados de conocerme.\n"
			+ "> Los llamaré pikinims (nombre protegido bajo las leyes de copyright sansanas ©),\n"
			+ "> porque cada uno puede levantar exactamente un kilopikinim de peso.\n"
			+ "> Debo ser veloz, no queda tanto para que se acabe el tanque de oxígeno, solo me quedan 30 horas."
		);

		// Loop principal de juego
		while (true) {
			turno++;

			// Finales
			/// Bad Ending (Se acabaron las 30 horas)
			if (turno > 30) {
				System.out.println(
					  ": Mira lo que has hecho, Lomiar se ha quedado sin oxigeno y ha muerto por tu culpa.\n"
					+ ": Ahora su cuerpo quedará en este planeta por siempre, y sus hijos nunca volveran a saber de el.\n"
					+ ": Lo ultimo que recordaran fue que les dijo que iba a salir a comprar leche.\n"
					+ "\n\n=== GAME OVER ===\n\n"
				);
				break;
			}

			/// Good Ending (Conseguiste escapar a tiempo)
			if (Pieza.piezasEncontradas >= 3) {
				System.out.println(
					  ": Has encontrado todas las piezas!!!\n"
					+ ": Quien diria que lograrias conseguirlas a tiempo.\n"
					+ ": Ahora escapas del planeta, dejando abandonados a los pikinims que hicieron la mayor parte del trabajo. Que agradecido eres.\n"
					+ ": Ahora por fín podrás ir a comprar la leche para tus hijos y volver para el desayuno.\n"
					+ "\n\n=== YOU WIN ===\n\n"
				);
				break;
			}

			// Interactuar con la zona actual
			System.out.println("\n----------------------------------------------------------------------\n");
			mapa[posicion].interactuar(cyan, magenta, amarillo);
			System.out.println("");

			// Mostrar datos del turno actual
			System.out.println(
				  "| Horas de Oxigeno Restante: " + (31 - turno) + " horas\n"
				+ "| Pikinims:\n"
				+ "| - Cyan     (ATK 1 | CAP 1): " + cyan.getCantidad() + "\n"
				+ "| - Magenta  (ATK 2 | CAP 1): " + magenta.getCantidad() + "\n"
				+ "| - Amarillo (ATK 1 | CAP 3): " + cyan.getCantidad() + "\n"
				+ "| Piezas Encontradas: " + Pieza.piezasEncontradas + "/3\n"
				+ "| Zona Actual: " + mapa[posicion].getClass().getName() + "\n"
			);

			// Elección de en que gastar el turno
			System.out.println("? Hacia donde quieres ir?");

			// Chequeo de murallas
			if (mapa[posicion].getClass().getName() == "Muralla" && !mapa[posicion].isCompletada()) {
				if (direccion == 1) {
					System.out.println(
						"? 2. Derecha ("
							+ mapa[posicion + 1].getClass().getName()
							+ ")   3. Quedarse aquí"
					);
					noPuedePasar = 1;
				} else if (direccion == 2) {
					System.out.println(
						"? 1. Izquierda ("
							+ mapa[posicion - 1].getClass().getName()
							+ ")   3. Quedarse aquí"
					);
					noPuedePasar = 2;
				}
			// Chequeo de circunavegación
			} else {
				int posicionIzq = 0;
				if (posicion == 0) {
					posicionIzq = 10;
				} else {
					posicionIzq = posicion - 1;
				}

				int posicionDer = 0;
				if (posicion == 10) {
					posicionDer = 0;
				} else {
					posicionDer = posicion + 1;
				}

				// Decisión default
				System.out.println(
					"? 1. Izquierda (" + mapa[posicionIzq].getClass().getName()
						+ ")   2. Derecha ("
						+ mapa[posicionDer].getClass().getName()
						+ ")   3. Quedarse aquí"
				);
				noPuedePasar = 0;
			}

			// Input usuario
			System.out.print("? Elección: ");
			int eleccion = input.nextInt();

			// Movimiento en el mapa y circunavegación
			if (eleccion == 1 && !(noPuedePasar == 1)) {
				if (posicion == 0) {
					posicion = 10;
				} else {
					posicion--;
				}

				direccion = 1;
			} else if (eleccion == 2 && !(noPuedePasar == 2)) {
				if (posicion == 10) {
					posicion = 0;
				} else {
					posicion++;
				}

				direccion = 2;
			}
		}

		// Pantalla final de puntaje
		System.out.println(
			  "======================================================================\n"
			+ "| PUNTAJE OBTENIDO\n"
			+ "======================================================================\n"
			+ "| Horas de Oxigeno Restante: " + (31 - turno) + " horas\n"
			+ "| Cantidad de pikinims: Cyan " + cyan.getCantidad()
				+ " - Magenta " + magenta.getCantidad() + " - Amarillos "
				+ amarillo.getCantidad() + "\n"
			+ "| Piezas Encontradas: " + Pieza.piezasEncontradas + "/3\n"
			+ "======================================================================"
		);

		Juego.input.close();
	}
}
