import java.util.Random;

public class Enemigo extends Zona implements ILevantar {
	private int vida;
	private int peso;
	private int ataque;

	public Enemigo(int vida, int peso, int ataque) {
		this.vida = vida;
		this.peso = peso;
		this.ataque = ataque;
	}

	public void interactuar(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		// Chequeo de si está completada
		if (this.completada) {
			super.interactuar(cyan, magenta, amarillo);
			return;
		}

		Pelear(cyan, magenta, amarillo);
	}

	private boolean Pelear(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		if (this.vida > 0) {
			System.out.println(
				  ": Te pones el reto de ir a pelear contra el enemigo que acecha en esta zona.\n"
				+ ": (Y con ir a pelear, te refieres tirar a los pikinims que conociste hace menos de 30 horas a su segura muerte)\n"
			);

			// Caculo daño causado
			int ataqueTotal = 0;
			ataqueTotal += cyan.getCantidad() * cyan.getAtaque();
			ataqueTotal += magenta.getCantidad() * magenta.getAtaque();
			ataqueTotal += amarillo.getCantidad() * amarillo.getAtaque();
			this.vida -= ataqueTotal;

			if (this.vida < 0)
				this.vida = 0;

			System.out.println(
				  ": PIM! PAM! PUM!\n"
				+ ": Dejaste al enemigo con " + this.vida + " de vida!!!\n"
			);

			// Calculo de daño recibido
			Random rand = new Random();
			int randomNumber = rand.nextInt(3) + 1;
			switch (randomNumber) {
				case 1:
					cyan.disminuir(this.ataque);
					System.out.println(
						  ": Oh no! Han muerto " + this.ataque + " pikinims cyan de un puro wate.\n"
						+ ": Parece que a los pikinims cyan les falta calle."
					);
					break;

				case 2:
					magenta.disminuir(this.ataque);
					System.out.println(
						  ": Despues del ajuste de cuentas, " + this.ataque + " pikinims magenta han fallecido.\n"
						+ ": El pesar que sientes ahora mismo durara hasta que el sucio alcohol te lo haga olvidar."
					);
					break;

				case 3:
					amarillo.disminuir(this.ataque);
					System.out.println(
						  ": El enemigo les hace la envolvente y PUMMMM!!! "
								+ this.ataque + " pikinims amarillos han muerto en combate.\n"
						+ ": Su servicio nunca será olvidado, seran conmemorados todos los años en esta misma fecha... hasta que todos se olviden de ellos."
					);
					break;
			}
		}

		if (this.vida <= 0)
			Levantar(cyan, magenta, amarillo);

		return this.vida <= 0;
	}

	public void Levantar(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		System.out.println(
			"\n: El enemigo yace muerto en el suelo luego de una dura batalla.\n"
			+ ": Con los pikimins restantes intentas levantar al enemigo caido.\n"
		);

		// Calculo de capacidad total
		int capacidadTotal = 0;
		capacidadTotal += cyan.getCapacidad() * cyan.getCantidad();
		capacidadTotal += magenta.getCapacidad() * magenta.getCantidad();
		capacidadTotal += amarillo.getCapacidad() * amarillo.getCantidad();

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
			int eleccion = Juego.input.nextInt();
			switch (eleccion) {
				case 1:
					cyan.multiplicar(this.peso);
					System.out.println(
						"\n: Los pikinim cyan ahora son "
							+ cyan.getCantidad() + "!"
					);
					this.completada = true;
					break;

				case 2:
					magenta.multiplicar(this.peso);
					System.out.println(
						"\n: Los pikinim magenta ahora son "
							+ magenta.getCantidad() + "!"
					);
					this.completada = true;
					break;

				case 3:
					amarillo.multiplicar(this.peso);
					System.out.println(
						"\n: Los pikinim amarillos ahora son "
							+ amarillo.getCantidad() + "!"
					);
					this.completada = true;
					break;

				default:
					System.out.println(
						  ": Acabo de decir que era 1, 2 y 3, no era tan complicado.\n"
						+ ": Por error o por hacerte el chistosito perdiste una hora de vida.\n"
						+ ": (Sinceramente me dio paja programar el intentar de nuevo)"
					);
					break;
			}

			return;
		}

		// Caso de no tener suficiente capacidad
		System.out.println(
			  "\n: Ni con la fuerza de todos los pikimins juntos lo han podido levantar.\n"
			+ ": El sacrificio tendra que esperar, solo esperemos que no se pudra."
		);
	}
}
