public class Magenta extends Pikinim {
	public Magenta() {
		this.ataque = 2;
		this.capacidad = 1;
		this.cantidad = 10;
	}

	public void multiplicar(int cantidad) {
		this.cantidad += cantidad * this.ataque;
	}
}
