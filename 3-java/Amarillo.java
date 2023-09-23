public class Amarillo extends Pikinim {
	public Amarillo() {
		ataque = 1;
		capacidad = 3;
		cantidad = 10;
	}

	public void multiplicar(int cantidad) {
		this.cantidad += Math.ceil(cantidad * 1.5);
	}
}
