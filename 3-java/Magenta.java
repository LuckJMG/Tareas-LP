public class Magenta extends Pikinim {
    public Magenta() {
        ataque = 2;
        capacidad = 1;
        cantidad = 10;
    }

    public void multiplicar(int cantidad) {
        this.cantidad += cantidad * this.ataque;
    }
}