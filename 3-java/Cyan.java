public class Cyan extends Pikinim {
	/*
	 * Nombre: Cyan
	 *
	 * Descripcion: Constructor de la clase Cyan, pone los valores de
	 * ataque, capacidad y cantidad de los Pikinims Cyan.
	 */
	public Cyan() {
		this.ataque = 1;
		this.capacidad = 1;
		this.cantidad = 10;
	}

	/*
	 * Nombre: multiplicar
	 *
	 * Descripcion: Implementacion de la funcion abstracta multiplicar, en este
	 * caso agrega la cantidad de pikinims dada por cantidad * 3.
	 *
	 * Paramtros:
	 * - int cantidad, cantidad a multiplicar los pikinims
	 */
	public void multiplicar(int cantidad) {
		this.cantidad += cantidad * 3;
	}
}
