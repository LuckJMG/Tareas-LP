public class Pieza extends Zona implements ILevantar {
    public static int piezasEncontradas;
    private int peso;

    public Pieza(int peso) {
        this.peso = peso;
    }

    public void interactuar(Cyan pikinimCyan, Magenta pikinimMagenta, Amarillo pikinimAmarillo) {
        if (this.completada) {
            super.interactuar(pikinimCyan, pikinimMagenta, pikinimAmarillo);
            return;
        }

        System.out.println("\n: Llegas al sector y te encuentras con una de las piezas de la nave.");
        System.out.println(": Tu solo no puedes levantarlo (falta gym), pero si ocupas la fuerza conjunta de tus pequeños amigos tal vez puedas recuperar la pieza.");
        System.out.println(": La pieza pesa " + this.peso + " kilopikinims, procedes a intentar levantarla con los pikinims que tienes.\n");
        Levantar(pikinimCyan, pikinimMagenta, pikinimAmarillo);
    }

    public void Levantar(Cyan pikinimCyan, Magenta pikinimMagenta, Amarillo pikinimAmarillo) {
        int capacidadTotal = 0;
        capacidadTotal += pikinimCyan.getCapacidad() * pikinimCyan.getCantidad();
        capacidadTotal += pikinimMagenta.getCapacidad() * pikinimMagenta.getCantidad();
        capacidadTotal += pikinimAmarillo.getCapacidad() * pikinimAmarillo.getCantidad();

        if (capacidadTotal >= this.peso) {
            Pieza.piezasEncontradas++;
            this.completada = true;
            System.out.println(": Has recuperado la pieza!!!");
            System.out.println(": Esto nunca debio haber pasado...\n");
            return;
        }

        System.out.println(": Tus pequeños amigos no son suficientes para levantarla, parece que te falta " + (this.peso - capacidadTotal) + " de capacidad para poder levantarla.");
        System.out.println(": Vuelve a intentarlo cuando tengas más pikinims.\n");
    }
}