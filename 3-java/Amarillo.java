public class Amarillo extends Pikinim {
	public Amarillo() {
		this.ataque = 1;
		this.capacidad = 3;
		this.cantidad = 10;
	}

	public void multiplicar(int cantidad) {
		this.cantidad += Math.ceil(cantidad * 1.5);
	}
}
