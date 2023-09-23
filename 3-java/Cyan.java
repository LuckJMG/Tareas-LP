public class Cyan extends Pikinim {
	public Cyan() {
		ataque = 1;
		capacidad = 1;
		cantidad = 10;
	}

	public void multiplicar(int cantidad) {
		this.cantidad += cantidad * 3;
	}
}
