public class Pildora extends Zona {
	private int cantidad;

	/*
	 * Nombre: Pildora
	 *
	 * Descripcion: Constructor de la clase Pildora, pone la cantidad que da la
	 * pildora al consumirla.
	 *
	 * Parametros:
	 * - int cantidad, cantidad de la pildora al consumirla
	 */
	public Pildora(int cantidad) {
		this.setCantidad(cantidad);
	}

	/*
	 * Nombre: getCantidad
	 *
	 * Descripción: getter del atributo cantidad
	 *
	 * Returns:
	 * - int, valor del atributo cantidad
	 */
	public int getCantidad() {
		return this.cantidad;
	}

	/* 
	 * Nombre: setCantidad
	 *
	 * Descripción: setter del atributo cantidad
	 *
	 * Parametros:
	 * - int cantidad, valor nuevo del atributo cantidad.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/*
	 * Nombre: interactuar
	 *
	 * Descripcion: Implementacion de interactuar, en este caso consume la
	 * pildora y aumenta a los pikinims del color que el jugador elija, luego
	 * marca como completada la zona.
	 *
	 * Parametros:
	 * - Pikinim[] pikinims, array de pikinims del turno actual
	 */
	public void interactuar(Pikinim[] pikinims) {
		// Chequeo de si está completada
		if (this.isCompletada()) {
			super.interactuar(pikinims);
			return;
		}

		System.out.println(
			  ": Has encontrado una pildora de dudosa procedencia.\n"
			+ ": Ves que dice en grande 'Cantidad: " + this.getCantidad() + "'.\n"
			+ "> Que hace una pildora gigante en mitad de un planeta abandonado?\n"
		);

		// Elección de que color aumentar
		System.out.print(
			  "? Que color de pikinim quieres aumentar?\n"
			+ "? 1. Cyan   2. Magenta   3. Amarillo\n"
			+ "? Elección: "
		);

		int color = Juego.input.nextInt() - 1;

		if (color < 0 || color >= pikinims.length) {
			System.out.println(
				  "\n: Creo haber dicho que era o 1 o 2 o 3.\n"
				+ ": Ahora perdiste una hora de valioso oxigeno.\n"
				+ ": Estas un paso más cerca de la muerte."
			);
			return;
		}

		pikinims[color].multiplicar(this.getCantidad());
		System.out.println(
			"\n! Los Pikinim " + pikinims[color].getClass().getName()
				+ " ahora son " + pikinims[color].getCantidad() + "!"
		);
		this.completar();
	}

	/*
	 * Nombre: getInfo
	 *
	 * Descripcion: Implementacion de getInfo, en este caso muestra la cantidad
	 * asociada a la pildora antes de completar la zona, luego muestra el caso
	 * base.
	 *
	 * Returns:
	 * - String, informacion de la zona
	 */
	public String getInfo() {
		if (this.isCompletada()) {
			return super.getInfo();
		}

		return "Pildora (CANT " + this.getCantidad() + ")";
	}
}
