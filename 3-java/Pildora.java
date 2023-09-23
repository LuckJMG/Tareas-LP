public class Pildora extends Zona {
	private int cantidad;

	public Pildora(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void interactuar(Cyan pikinimCyan, Magenta pikinimMagenta, Amarillo pikinimAmarillo) {
		if (this.completada) {
			super.interactuar(pikinimCyan, pikinimMagenta, pikinimAmarillo);
			return;
		}
		System.out.println("\n: Has encontrado una pildora de dudosa procedencia.");
		System.out.println(": Ves que dice en grande 'Cantidad: " + this.cantidad + "'");
		System.out.println(": Como no piensas consumirla tu, prefieres darselas a tus pikinims.");
		System.out.println(": Al tirarles la pildora aparece un gran popup gigante gigante preguntado: \n");

		System.out.println("? Que color de pikinim quieres aumentar?");
		System.out.println("? 1. Cyan   2. Magenta   3. Amarillo");
		System.out.print("? Elección: ");

		int eleccion = Juego.input.nextInt();

		if (eleccion == 1) {
			pikinimCyan.multiplicar(this.cantidad);
			System.out.println("\n: Los pikinim cyan ahora son " + pikinimCyan.getCantidad() + "!\n");
		} else if (eleccion == 2) {
			pikinimMagenta.multiplicar(this.cantidad);
			System.out.println("\n: Los pikinim magenta ahora son " + pikinimMagenta.getCantidad() + "!\n");
		} else if (eleccion == 3) {
			pikinimAmarillo.multiplicar(this.cantidad);
			System.out.println("\n: Los pikinim amarillos ahora son " + pikinimAmarillo.getCantidad() + "!\n");
		}

		this.completada = true;
	}

}
