abstract public class Zona {
	private boolean completada;
	private static int zonasCompletadas = 0;

	/*
	 * Nombre: Zona
	 *
	 * Descripcion: Constructor de la clase abstracta zona, pone las zonas por
	 * default a sin completar.
	 */
	public Zona() {
		this.completada = false;
	}

	/*
	 * Nombre: isCompletada
	 *
	 * Descripcion: Getter del atributo completada.
	 *
	 * Returns:
	 * - boolean, si la zona esta completada o no
	 */
	protected boolean isCompletada() {
		return completada;
	}

	/*
	 * Nombre: completar
	 *
	 * Descripcion: Setter del atributo completada, solo cambia el valor de
	 * completada a verdad, porque no esta la opcion de descompletar una zona,
	 * ademas aumenta en uno la cantidad de zona completadas.
	 */
	protected void completar() {
		zonasCompletadas += 1;
		this.completada = true;
	}

	/*
	 * Nombre: getZonasCompletadas
	 *
	 * Descripcion: Getter del atributo estatico zonasCompletadas.
	 *
	 * Returns:
	 * - int, cantidad de zonas completadas
	 */
	public static int getZonasCompletadas() {
		return zonasCompletadas;
	}

	/*
	 * Nombre: interactuar
	 *
	 * Descripcion: Version base de la funcion interactuar, solo imprime en
	 * pantalla que la zona esta completada.
	 *
	 * Parametros:
	 * - Pikinim[], array de pikinims de turno (no usado)
	 */
	public void interactuar(Pikinim[] pikinims) {
		System.out.println(": Ya has hecho todo lo que puedes hacer aquí, no queda nada por hacer.");
	}

	/*
	 * Nombre: getInfo
	 *
	 * Descripcion: Funcion base para mostrar informacion detallada de la
	 * zona actual, solo muestra que la zona esta completada.
	 *
	 * Returns:
	 * - String, informacion de la zona
	 */
	public String getInfo() {
		return "Zona Completada";
	}
}
