public class Pildora extends Zona {
	private int cantidad;

	public Pildora(int cantidad) {
		this.cantidad = cantidad;
	}

	public void interactuar(Cyan cyan, Magenta magenta, Amarillo amarillo) {
		// Chequeo de si está completada
		if (this.isCompletada()) {
			super.interactuar(cyan, magenta, amarillo);
			return;
		}

		System.out.println(
			  ": Has encontrado una pildora de dudosa procedencia.\n"
			+ ": Ves que dice en grande 'Cantidad: " + this.cantidad + "'.\n"
			+ ": Como no piensas consumirla tu, prefieres darselas a tus pikinims.\n"
			+ ": Al tirarles la pildora aparece un gran popup gigante gigante preguntado: \n"
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
				cyan.multiplicar(this.cantidad);
				System.out.println(
					"\n! Los pikinim cyan ahora son " + cyan.getCantidad() + "!"
				);
				this.completar();
				break;

			case 2:
				magenta.multiplicar(this.cantidad);
				System.out.println(
					"\n! Los pikinim magenta ahora son " + magenta.getCantidad()
						+ "!"
				);
				this.completar();
				break;

			case 3:
				amarillo.multiplicar(this.cantidad);
				System.out.println(
					"\n! Los pikinim amarillos ahora son " + amarillo.getCantidad()
						+ "!"
				);
				this.completar();
				break;

			default:
				System.out.println(
					  ": Creo haber dicho que era o 1 o 2 o 3.\n"
					+ ": No pense que iba a ser tan dificil lidiar contigo.\n"
					+ ": Ahora perdiste una hora de valioso oxigeno.\n"
					+ ": Estas un paso más cerca de la muerte."
				);
				break;
		}
	}

	public String getInfo() {
		if (this.isCompletada()) {
			return super.getInfo();
		}

		return "Pildora (CANT " + this.cantidad + ")";
	}
}
