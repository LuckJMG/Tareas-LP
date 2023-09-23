public class Zona {
	protected boolean completada;

	public Zona() {
		this.completada = false;
	}

	public boolean isCompletada() {
		return completada;
	}

	protected void interactuar(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		System.out.println(": Ya has hecho todo lo que puedes hacer aquí, no queda nada por hacer.");
	}
}
