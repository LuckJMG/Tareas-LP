public class Muralla extends Zona {
	private int vida;

	/*
	 * Nombre: Muralla
	 *
	 * Descripcion: Constructor de la clase Muralla, pone el atributo vida.
	 *
	 * Parametros:
	 * - int vida, vida de la muralla
	 */
	public Muralla(int vida) {
		this.vida = vida;
	}

	/*
	 * Nombre: interactuar
	 *
	 * Descripcion: Implementacion de la funcion interactuar, intenta romper la
	 * muralla con los pikinims que le da, en caso de conseguirlo se marca como
	 * completada y subsecuentes llamadas de la funcion llamaran a la funcion
	 * base.
	 *
	 * Parametros:
	 * - Pikinim[] pikinims, pikinims con los que romper la muralla
	 */
	public void interactuar(Pikinim[] pikinims) {
		// Chequeo de si está completada
		if (this.isCompletada()) {
			super.interactuar(pikinims);
			return;
		}

		System.out.println(
			  ": Te encuentras una muralla que cubre el camino de cal a canto.\n"
			+ ": Intentaras romperla lanzando a tus pobres pikinims contra ella.\n"
			+ "> Id pequeñines! Trabajad!\n"
		);

		// Intentar romper la muralla
		if (tryRomper(pikinims)) {
			this.completar();

			System.out.println(
				  "! A pesar de tu futil esfuerzo, DESTRUISTE LA MURALLA!!!\n"
				+ "! El camino que antes estaba cerrado se abré ante tí.\n"
				+ "> Con mi fuerza está muralla no ha sido nada para mí."
			);

			return;
		}

		// No se pudo destruir
		System.out.println(
			  "! Que pena tan grande, NO PUDISTE DESTRUIR tan fragil MURALLA.\n"
			+ "! A la muralla le quedan " + this.vida + " puntos de vida.\n"
			+ "> Claramente la muralla esta hecha de obsidiana."
		);
	}

	/*
	 * Nombre: tryRomper
	 *
	 * Descripcion: Intenta romper la muralla, se le va restando vida en base al
	 * total de ataque de los pikinims dados, si no lo consigue retorna falso,
	 * en caso de conseguirlo retorna verdadero.
	 *
	 * Parametros:
	 * - Pikinim[] pikinims, array de pikinims con los que se rompera la muralla
	 *
	 * Returns:
	 * - boolean, verdadero si se logro destruir la muralla
	 */
	private boolean tryRomper(Pikinim[] pikinims) {
		// Calculo de daño causado
		for (Pikinim color : pikinims) {
			this.vida -= color.getCantidad() * color.getAtaque();
		}

		if (vida < 0)
			vida = 0;

		return vida <= 0;
	}

	/*
	 * Nombre: getInfo
	 *
	 * Descripcion: Implementacion de getInfo, en este caso muestra la vida
	 * restante de la muralla, una vez complatada la zona devuelve el caso base.
	 *
	 * Returns:
	 * - String, informacion de la zona
	 */
	public String getInfo() {
		if (this.isCompletada()) {
			return super.getInfo();
		}

		return "Muralla (HP " + this.vida + ")";
	}
}
