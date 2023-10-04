public class Magenta extends Pikinim {
	/*
	 * Nombre: Magenta
	 *
	 * Descripcion: Constructor de la clase Magenta, pone los valores de
	 * ataque, capacidad y cantidad de los Pikinims Magenta.
	 */
	public Magenta() {
		this.setAtaque(2);
		this.setCapacidad(1);
		this.setCantidad(10);
	}

	/*
	 * Nombre: multiplicar
	 *
	 * Descripcion: Implementacion de la funcion abstracta multiplicar, en este
	 * caso agrega la cantidad de pikinims dada por cantidad * ataque.
	 *
	 * Paramtros:
	 * - int cantidad, cantidad a multiplicar los pikinims
	 */
	public void multiplicar(int cantidad) {
		this.setCantidad(this.getCantidad() + cantidad * this.getAtaque());
	}
}
