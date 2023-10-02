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
		this.vida = vida;
		this.peso = peso;
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
				": Te pones el reto de ir a pelear contra el enemigo que acecha en esta zona.\n"
			+ ": (Y con ir a pelear, te refieres tirar a los pikinims que conociste hace menos de 30 horas a su segura muerte)\n"
		);

		// Caculo daño causado
		int ataqueTotal = 0;
		for (Pikinim color : pikinims) {
			ataqueTotal += color.getCantidad() * color.getAtaque();
		}
		this.vida -= ataqueTotal;

		if (this.vida < 0)
			this.vida = 0;

		System.out.println(
				"! PIM! PAM! PUM!\n"
			+ "! Dejaste al enemigo con " + this.vida + " de vida!!!\n"
		);

		// Calculo de daño recibido
		Random rand = new Random();
		while (true) {
			int color = rand.nextInt(3);
			if (pikinims[color].getCantidad() == 0) continue;

			System.out.println(
				  "! Oh no! Han muerto "
				  	+ (pikinims[color].getCantidad() <= this.ataque ? pikinims[color].getCantidad() : this.ataque)
					+ " Pikinims " + pikinims[color].getClass().getName() + "\n"
				+ "! Su servicio nunca será olvidado, seran conmemorados todos los años en esta misma fecha... hasta que todos se olviden de ellos."
			);
			pikinims[color].disminuir(this.ataque);
			break;
		}

		// Completar zona una vez muerto el enemigo
		if (this.vida <= 0) {
			this.completar();
			levantar(pikinims);
		}

		return this.vida <= 0;
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
		System.out.println(
			"\n: El enemigo yace muerto en el suelo luego de una dura batalla.\n"
			+ ": Con los pikimins restantes intentas levantar al enemigo caido.\n"
		);

		// Calculo de capacidad total
		int capacidadTotal = 0;
		for (Pikinim color : pikinims) {
			capacidadTotal += color.getCantidad() * color.getCapacidad();
		}

		if (capacidadTotal >= peso) {
			System.out.println(
				": Con todas tus fuerza (la de los pikimins) logras levantar al enemigo.\n"
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
					+ ": Una pena."
				);
				return;
			}

			pikinims[color].multiplicar(this.peso);
			System.out.println(
				"\n! Los pikinim " + pikinims[color].getClass().getName()
					+ " ahora son " + pikinims[color].getCantidad() + "!"
			);

			return;
		}

		// Caso de no tener suficiente capacidad
		System.out.println(
			  "\n: Ni con la fuerza de todos los pikimins juntos lo han podido levantar.\n"
			+ ": El sacrificio tendra que esperar, solo esperemos que no se pudra."
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

		return "Enemigo (HP " + this.vida + " | ATK " + this.ataque
					+ " | PESO " + this.peso + ")";
	}
}
