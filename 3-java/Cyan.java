public class Cyan extends Pikinim {
	public Cyan() {
		this.ataque = 1;
		this.capacidad = 1;
		this.cantidad = 10;
	}

	public void multiplicar(int cantidad) {
		this.cantidad += cantidad * 3;
	}
}
