public class Pieza extends Zona implements ILevantar {
	public static int piezasEncontradas;
	private int peso;

	/*
	 * Nombre: Pieza
	 *
	 * Descripcion: Constructor de la clase Pieza, pone el peso de la pieza.
	 *
	 * Parametros:
	 * - int peso, peso de la pieza
	 */
	public Pieza(int peso) {
		this.peso = peso;
	}

	/*
	 * Nombre: interactuar
	 *
	 * Descripcion: Implementacion de interactuar, en este caso intenta levantar
	 * la pieza.
	 *
	 * Parametros:
	 * - Pikinim[] pikinims, array de pikinims con los que levantar la pieza
	 */
	public void interactuar(Pikinim[] pikinims) {
		// Chequeo de si está completada
		if (this.isCompletada()) {
			super.interactuar(pikinims);
			return;
		}

		System.out.println(
			  ": Encontraste una de las piezas perdidas!!!\n"
			+ ": La pieza pesa " + this.peso
				+ " kilopikinims, podran levantarlo?\n"
		);

		levantar(pikinims);
	}

	/*
	 * Nombre: levantar
	 *
	 * Descripcion: Implementacion de la interfaz ILevantar, en este caso
	 * intenta levantar la pieza con los pikinims que se le dan, completa la
	 * zona si la logran levantar.
	 *
	 * Parametros:
	 * - Pikinim[] pikinims, array de pikinims con los que levantar la pieza
	 */
	public void levantar(Pikinim[] pikinims) {
		// Calculo de capacidad total
		int capacidadTotal = 0;
		for (Pikinim color : pikinims) {
			capacidadTotal += color.getCapacidad() * color.getCantidad();
		}

		// Intentar levantar
		if (capacidadTotal >= this.peso) {
			Pieza.piezasEncontradas++;
			this.completar();

			System.out.println(
				  "! Has recuperado la pieza!!!\n"
				+ "! Algo que nunca debiste haber perdido en primer lugar.\n"
				+ "> Un paso más cerca de volver a mi hogar."
			);

			return;
		}

		// No se pudo levantar
		System.out.println(
			  "! No se pudo levantar, parece que te falta "
					+ (this.peso - capacidadTotal) + " de capacidad.\n"
			+ "! Vuelve a intentarlo cuando tengas más pikinims."
			+ "> Triste :c."
		);
	}

	/*
	 * Nombre: getInfo
	 *
	 * Descripcion: Implementacion de getInfo, en este caso muestra el peso de
	 * la pieza cuando la zona no esta completada, cuando esta completada
	 * muestra el caso base.
	 *
	 * Returns:
	 * - String, informacion de la zona
	 */
	public String getInfo() {
		if (this.isCompletada()) {
			return super.getInfo();
		}

		return "Pieza (PESO " + this.peso + ")";
	}
}
