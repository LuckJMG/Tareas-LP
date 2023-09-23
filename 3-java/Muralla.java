public class Muralla extends Zona {
	private int vida;

	public Muralla(int vida) {
		this.vida = vida;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public void interactuar(Cyan pikinimCyan, Magenta pikinimMagenta, Amarillo pikinimAmarillo) {
		if (this.completada) {
			super.interactuar(pikinimCyan, pikinimMagenta, pikinimAmarillo);
			return;
		}

		System.out.println("\n: Te encuentras una muralla que cubre el camino de cal a canto.");
		System.out.println(": Haras el mejor intento de romperla lanzando a los pobres pikinims que te acompañan.\n");

		if (tryRomper(pikinimCyan, pikinimMagenta, pikinimAmarillo)) {
			this.completada = true;
			System.out.println(
					": A pesar del futil esfuerzo que haces para recuperar las piezas, has DESTRUIDO la muralla!!!");
			System.out.println(": El camino que antes estaba cerrado se abré ante tí.");
			System.out.println(": Que te esperara al otro lado de la muralla?\n");
			return;
		}

		System.out.println(": Ay que pena, NO pudiste DESTRUIR ni esta fragil muralla.");
		System.out.println(": A la muralla le quedan " + this.vida + " puntos de vida.");
		System.out.println(": Por si quieres hacer el futil intento de destruirla de nuevo.\n");
	}

	private boolean tryRomper(Cyan pikinimCyan, Magenta pikinimMagenta, Amarillo pikinimAmarillo) {
		this.vida -= pikinimCyan.getCantidad() * pikinimCyan.getAtaque();
		this.vida -= pikinimMagenta.getCantidad() * pikinimMagenta.getAtaque();
		this.vida -= pikinimAmarillo.getCantidad() * pikinimAmarillo.getAtaque();

		return vida <= 0;
	}
}
