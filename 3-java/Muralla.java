public class Muralla extends Zona {
	private int vida;

	public Muralla(int vida) {
		this.vida = vida;
	}

	public void interactuar(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		// Chequeo de si está completada
		if (this.isCompletada()) {
			super.interactuar(cyan, magenta, amarillo);
			return;
		}

		System.out.println(
			  ": Te encuentras una muralla que cubre el camino de cal a canto.\n"
			+ ": Haras el mejor intento de romperla lanzando a los pobres pikinims que te acompañan.\n"
		);

		// Intentar romper la muralla
		if (tryRomper(cyan, magenta, amarillo)) {
			this.completar();

			System.out.println(
				  "! A pesar del futil esfuerzo que haces para recuperar las piezas, has DESTRUIDO la muralla!!!\n"
				+ "! El camino que antes estaba cerrado se abré ante tí.\n"
				+ "! Que te esperara al otro lado de la muralla?"
			);

			return;
		}

		// No se pudo destruir
		System.out.println(
			  "! Ay que pena, NO pudiste DESTRUIR ni esta fragil muralla.\n"
			+ "! A la muralla le quedan " + this.vida + " puntos de vida.\n"
			+ "! Por si quieres hacer el futil intento de destruirla de nuevo."
		);
	}

	private boolean tryRomper(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		// Calculo de daño causado
		this.vida -= cyan.getCantidad() * cyan.getAtaque();
		this.vida -= magenta.getCantidad() * magenta.getAtaque();
		this.vida -= amarillo.getCantidad() * amarillo.getAtaque();

		if (vida < 0)
			vida = 0;

		return vida <= 0;
	}

	public String getInfo() {
		if (this.isCompletada()) {
			return super.getInfo();
		}

		return "Muralla (HP " + this.vida + ")";
	}
}
