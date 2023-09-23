public class Pieza extends Zona implements ILevantar {
	public static int piezasEncontradas;
	private int peso;

	public Pieza(int peso) {
		this.peso = peso;
	}

	public void interactuar(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		// Chequeo de si está completada
		if (this.completada) {
			super.interactuar(cyan, magenta, amarillo);
			return;
		}

		System.out.println(
			  ": Llegas al sector y te encuentras con una de las piezas de la nave.\n"
			+ ": Tu solo no puedes levantarlo (falta gym), pero si ocupas la fuerza conjunta de tus pequeños amigos tal vez puedas recuperar la pieza.\n"
			+ ": La pieza pesa " + this.peso
				+ " kilopikinims, procedes a intentar levantarla con los pikinims que tienes.\n"
		);

		Levantar(cyan, magenta, amarillo);
	}

	public void Levantar(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		// Calculo de capacidad total
		int capacidadTotal = 0;
		capacidadTotal += cyan.getCapacidad() * cyan.getCantidad();
		capacidadTotal += magenta.getCapacidad() * magenta.getCantidad();
		capacidadTotal += amarillo.getCapacidad() * amarillo.getCantidad();

		// Intentar levantar
		if (capacidadTotal >= this.peso) {
			Pieza.piezasEncontradas++;
			this.completada = true;

			System.out.println(
				  ": Has recuperado la pieza!!!\n"
				+ ": Esto nunca debio haber pasado..."
			);

			return;
		}

		// No se pudo levantar
		System.out.println(
			  ": Tus pequeños amigos no son suficientes para levantarla, parece que te falta "
					+ (this.peso - capacidadTotal) + " de capacidad para poder levantarla.\n"
			+ ": Vuelve a intentarlo cuando tengas más pikinims."
		);
	}
}
