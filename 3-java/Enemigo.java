import java.util.Random;

public class Enemigo extends Zona implements ILevantar {
	private int vida;
	private int peso;
	private int ataque;

	/*
	 * Nombre: Enemigo
	 *
	 * Descripcion: Constructor de la clase Enemigo, pone la vida, el peso y el
	 * ataque del enemigo a crear.
	 *
	 * Parametros:
	 * - int vida, vida del enemigo
	 * - int peso, peso del enemigo al levantarlo
	 * - int ataque, ataque del enemigo
	 */
	public Enemigo(int vida, int peso, int ataque) {
		this.setVida(vida);
		this.setPeso(peso);
		this.setAtaque(ataque);
	}

	/*
	 * Nombre: getVida
	 *
	 * Descripcion: getter del atributo vida
	 *
	 * Returns:
	 * - int, valor de la vida
	 */
	public int getVida() {
		return this.vida;
	}

	/*
	 * Nombre: setVida
	 *
	 * Descripcion: setter del atributo vida
	 *
	 * Parametros:
	 * - int vida, nuevo valor de la vida
	 */
	public void setVida(int vida) {
		this.vida = vida;
	}

	/*
	 * Nombre: getPeso
	 *
	 * Descripcion: getter del atributo peso
	 *
	 * Returns:
	 * - int, valor del peso
	 */
	public int getPeso() {
		return this.peso;
	}

	/*
	 * Nombre: setPeso
	 *
	 * Descripcion: setter del atributo peso
	 *
	 * Parametros:
	 * - int peso, nuevo valor del peso
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/*
	 * Nombre: getAtaque
	 *
	 * Descripcion: getter del atributo ataque
	 *
	 * Returns:
	 * - int, valor del ataque
	 */
	public int getAtaque() {
		return this.ataque;
	}

	/*
	 * Nombre: setAtaque
	 *
	 * Descripcion: setter del atributo ataque
	 *
	 * Parametros:
	 * - int ataque, nuevo valor del ataque
	 */
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	/*
	 * Nombre: interactuar
	 *
	 * Descripcion: Implementacion de interactuar, llama a la funcion pelear
	 * si la zona no esta completada, en caso contrario llama al caso base.
	 *
	 * Parametros:
	 * - Pikinim[] pikinims, array de pikinims del turno actual
	 */
	public void interactuar(Pikinim[] pikinims) {
		// Chequeo de si está completada
		if (this.isCompletada()) {
			super.interactuar(pikinims);
			return;
		}

		pelear(pikinims);
	}

	/*
	 * Nombre: pelear
	 *
	 * Descripcion: Pelea con el enemigo, primero los pikinims le hacen el total
	 * de dano generado entre ellos al enemigo, luego el enemigo con 0 de vida o
	 * no, mata a una cantidad de pikinims de un color random,
	 * cantidad determinada por su ataque.
	 * Si lo logran matar, llama a la funcion levantar para intentar levantar
	 * el enemigo y consumirlo.
	 *
	 * Parametros:
	 * - Pikinim[] pikinims, array de pikinims con los que pelear
	 *
	 * Returns:
	 * - boolean, verdadero si el enemigo esta muerto
	 */
	private boolean pelear(Pikinim[] pikinims) {
		System.out.println(
			  ": Te pones el reto de ir a pelear contra el enemigo de la zona.\n"
			+ ": Y con ir a pelear te refieres a tirar a tus pikinims contra el.\n"
			+ "> Atacad mis queridos pikinims! El cielo los espera!\n"
		);

		// Caculo daño causado
		int ataqueTotal = 0;
		for (Pikinim color : pikinims) {
			ataqueTotal += color.getCantidad() * color.getAtaque();
		}
		this.setVida(this.getVida() - ataqueTotal);

		if (this.getVida() < 0)
			this.setVida(0);

		// Calculo de daño recibido
		Random rand = new Random();
		while (true) {
			int color = rand.nextInt(3);
			if (pikinims[color].getCantidad() == 0) continue;

			System.out.println(
				  "! Dejaste al enemigo con " + this.getVida() + " de vida!!!\n"
				+ "! Oh no! Han muerto "
				  	+ (pikinims[color].getCantidad() <= this.getAtaque() ? pikinims[color].getCantidad() : this.getAtaque())
					+ " Pikinims " + pikinims[color].getClass().getName() + "\n"
				+ "> Una gran meta necesita de grandes sacrificios.\n"
			);
			pikinims[color].disminuir(this.getAtaque());
			break;
		}

		// Completar zona una vez muerto el enemigo
		if (this.getVida() <= 0) {
			this.completar();
			levantar(pikinims);
		}

		return this.getVida() <= 0;
	}

	/*
	 * Nombre: levantar
	 *
	 * Descripcion: Implementacion de la interfaz ILevantar, intenta levantar al
	 * enemigo para consumirlo, si puede se aumenta la cantidad de pikinims de
	 * un color que el jugador elija, en caso contrario pierde la oportunida
	 * de consumirlo y se marca la zona como completada.
	 *
	 * Parametros:
	 * - Pikinim[] pikinims, pikinims con los que levantar al enemigo
	 */
	public void levantar(Pikinim[] pikinims) {
		// Calculo de capacidad total
		int capacidadTotal = 0;
		for (Pikinim color : pikinims) {
			capacidadTotal += color.getCantidad() * color.getCapacidad();
		}

		if (capacidadTotal >= this.getPeso()) {
			System.out.println(
				  ": Con todas tus fuerza (los pikimins) logras levantar al enemigo.\n"
				+ ": Y tal cual un azteca, te comeras a tu enemigo para hacer tuya su fuerza.\n"
			);

			// Elección de que color aumentar
			System.out.print(
				  "? Que color de pikinim quieres aumentar?\n"
				+ "? 1. Cyan   2. Magenta   3. Amarillo\n"
				+ "? Elección: "
			);
			int color = Juego.input.nextInt() - 1;

			if (color < 0 || color >= pikinims.length) {
				System.out.println(
					  ": Acabo de decir que era 1, 2 y 3, no era tan complicado.\n"
					+ ": Ahora perdiste la posibilidad de hacer nuevos pikinims.\n"
				);
				return;
			}

			pikinims[color].multiplicar(this.getPeso());
			System.out.println(
				"\n! Los pikinim " + pikinims[color].getClass().getName()
					+ " ahora son " + pikinims[color].getCantidad() + "!"
			);

			return;
		}

		// Caso de no tener suficiente capacidad
		System.out.println(
			  "\n: Ni con la fuerza de todos los pikimins lo han podido levantar.\n"
			+ ": El sacrificio se ha perdido."
		);
	}

	/*
	 * Nombre: getInfo
	 *
	 * Descripcion: Implementacion de getInfo, en este caso muestra los datos
	 * del enemigo mientras siga vivo, osea vida, ataque y peso, cuando muere
	 * se muestra el caso base.
	 *
	 * Returns:
	 * - String, informacion del enemigo
	 */
	public String getInfo() {
		if (this.isCompletada()) {
			return super.getInfo();
		}

		return "Enemigo (HP " + this.getVida() + " | ATK " + this.getAtaque()
					+ " | PESO " + this.getPeso() + ")";
	}
}
