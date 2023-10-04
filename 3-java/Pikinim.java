abstract public class Pikinim {
	private int ataque;
	private int capacidad;
	private int cantidad;

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
	 * Nombre: setAtaque
	 *
	 * Descripción: Setter del atributo ataque.
	 *
	 * Parametros:
	 * - int ataque, valor a poner del atributo ataque.
	 */
	public void setAtaque(int ataque) {
		this.ataque = ataque;
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
	 * Nombre: setCapacidad
	 *
	 * Descripción: Setter del atributo capacidad.
	 *
	 * Parametros:
	 * - int capacidad, valor a poner del atributo capacidad.
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
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
	 * Nombre: setCantidad
	 *
	 * Descripción: Setter del atributo cantidad.
	 *
	 * Parametros:
	 * - int cantidad, valor a poner del atributo cantidad.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
		this.setCantidad(this.getCantidad() - cantidad);
		this.cantidad -= cantidad;
		if (this.getCantidad() < 0)
			this.setCantidad(0);
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
