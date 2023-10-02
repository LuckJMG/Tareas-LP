public class Amarillo extends Pikinim {
	/*
	 * Nombre: Amarillo
	 *
	 * Descripcion: Constructor de la clase Amarillo, pone los valores de
	 * ataque, capacidad y cantidad de los Pikinims Amarillos.
	 */
	public Amarillo() {
		this.ataque = 1;
		this.capacidad = 3;
		this.cantidad = 10;
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
		this.cantidad += Math.ceil(cantidad * 1.5);
	}
}
