abstract class Pikinim {
	protected int ataque;
	protected int capacidad;
	protected int cantidad;

	public int getAtaque() {
		return ataque;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void disminuir(int cantidad) {
		this.cantidad -= cantidad;
		if (this.cantidad < 0)
			this.cantidad = 0;
	}

	abstract public void multiplicar(int cantidad);
}
