abstract public class Pikinim {
	protected int ataque;
	protected int capacidad;
	protected int cantidad;

	/*
	 * Nombre: getAtaque
	 *
	 * Descripcion: Getter del atributo ataque.
	 *
	 * Returns:
	 * - int, valor de ataque
	 */
	public int getAtaque() {
		return ataque;
	}

	/*
	 * Nombre: getCapacidad
	 *
	 * Descripcion: Getter del atributo capacidad.
	 *
	 * Returns:
	 * - int, valor de capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/*
	 * Nombre: getCantidad
	 *
	 * Descripcion: Getter del atributo cantidad.
	 *
	 * Returns:
	 * - int, cantidad de pikinims de la clase
	 */
	public int getCantidad() {
		return cantidad;
	}

	/*
	 * Nombre: disminuir
	 *
	 * Descripcion: Disminuye la cantidad de pikinims en la cantidad indicada
	 * manteniendo un valor valido (>=0)/
	 *
	 * Parametros:
	 * - int cantidad, cantidad a disminuir a los pikinims
	 */
	public void disminuir(int cantidad) {
		this.cantidad -= cantidad;
		if (this.cantidad < 0)
			this.cantidad = 0;
	}

	/*
	 * Nombre: multiplicar
	 *
	 * Descripcion: Funcion abstracta a implementar en las clases de pikinims
	 * especificos, que indica cuanto se multiplican segun una cantidad dada.
	 *
	 * Parametros:
	 * - int cantidad, cantidad a multiplicar a los pikinims
	 */
	abstract public void multiplicar(int cantidad);
}
