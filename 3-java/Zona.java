public class Zona {
	protected boolean completada;

	public Zona() {
		this.completada = false;
	}

	public boolean isCompletada() {
		return completada;
	}

	protected void interactuar(Cyan pikinimCyan, Magenta pikinimMagenta, Amarillo pikinimAmarillo) {
		System.out.println("\n: Ya has hecho todo lo que puedes hacer aquí, no queda nada por hacer.\n");
	}
}
