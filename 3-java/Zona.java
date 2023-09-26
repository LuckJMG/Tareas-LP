public class Zona {
	private boolean completada;
	private static int zonasCompletadas = 0;

	public Zona() {
		this.completada = false;
	}

	protected boolean isCompletada() {
		return completada;
	}

	protected void completar() {
		zonasCompletadas += 1;
		this.completada = true;
	}

	public static int getZonasCompletadas() {
		return zonasCompletadas;
	}

	public void interactuar(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		System.out.println(": Ya has hecho todo lo que puedes hacer aquí, no queda nada por hacer.");
	}

	public String getInfo() {
		return "Zona Completada";
	}
}
