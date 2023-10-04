public class Amarillo extends Pikinim {
	/*
	 * Nombre: Amarillo
	 *
	 * Descripcion: Constructor de la clase Amarillo, pone los valores de
	 * ataque, capacidad y cantidad de los Pikinims Amarillos.
	 */
	public Amarillo() {
		this.setAtaque(1);
		this.setCapacidad(3);
		this.setCantidad(10);
	}

	/*
	 * Nombre: multiplicar
	 *
	 * Descripcion: Implementacion de la funcion abstracta multiplicar, en este
	 * caso agrega la cantidad de pikinims dada por Math.Ceil(cantidad * 1.5).
	 *
	 * Paramtros:
	 * - int cantidad, cantidad a multiplicar los pikinims
	 */
	public void multiplicar(int cantidad) {
		this.setCantidad(this.getCantidad() + (int)Math.ceil(cantidad * 1.5));
	}
}
