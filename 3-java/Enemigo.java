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

    public void interactuar(Cyan pikinimCyan, Magenta pikinimMagenta, Amarillo pikinimAmarillo) {
        if (this.completada) {
            super.interactuar(pikinimCyan, pikinimMagenta, pikinimAmarillo);
            return;
        }

        Pelear(pikinimCyan, pikinimMagenta, pikinimAmarillo);
    }

    private boolean Pelear(Cyan pikinimCyan, Magenta pikinimMagenta, Amarillo pikinimAmarillo) {
        if (this.vida > 0) {
            System.out.println("\n: Te pones el reto de ir a pelear contra el enemigo que acecha en esta zona.");
            System.out.println(": (Y con ir a pelear, te refieres tirar a los pikinims que conociste hace menos de 30 horas a su segura muerte)\n");

            int ataqueTotal = 0;
            ataqueTotal += pikinimCyan.cantidad * pikinimCyan.cantidad;
            ataqueTotal += pikinimMagenta.cantidad * pikinimMagenta.cantidad;
            ataqueTotal += pikinimAmarillo.cantidad * pikinimAmarillo.cantidad;
            this.vida -= ataqueTotal;

            if (this.vida < 0) this.vida = 0;

            System.out.println(": PIM! PAM! PUM!");
            System.out.println(": Dejaste al enemigo con " + this.vida + " de vida!!!\n");

            Random rand = new Random();
            int randomNumber = rand.nextInt(3) + 1;
            if (randomNumber == 1) {
                pikinimCyan.disminuir(this.ataque);
                System.out.println(": Oh no! Han muerto " + this.ataque + " pikinims cyan de un puro wate.");
                System.out.println(": Parece que a los pikinims cyan les falta calle.\n");
            } else if (randomNumber == 2) {
                pikinimMagenta.disminuir(this.ataque);
                System.out.println(": Despues del ajuste de cuentas, " + this.ataque + " pikinims magenta han fallecido.");
                System.out.println(": El pesar que sientes ahora mismo durara hasta que el sucio alcohol te lo haga olvidar.\n");
            } else if (randomNumber == 3) {
                pikinimAmarillo.disminuir(this.ataque);
                System.out.println(": El enemigo les hace la envolvente y PUMMMM!!! " + this.ataque + " pikinims amarillos han muerto en combate");
                System.out.println(": Su servicio nunca será olvidado, seran conmemorados todos los años en esta misma fecha... hasta que todos se olviden de ellos.");
            } 
        }

        if (this.vida <= 0) {
            Levantar(pikinimCyan, pikinimMagenta, pikinimAmarillo);
        }
        
        return this.vida <= 0;
    }

    public void Levantar(Cyan pikinimCyan, Magenta pikinimMagenta, Amarillo pikinimAmarillo) {
        System.out.println("\n: El enemigo yace muerto en el suelo luego de una dura batalla.");
        System.out.println(": Con los pikimin restantes intentas levantar al enemigo caido.\n");

        int capacidadTotal = 0;
        capacidadTotal += pikinimCyan.getCapacidad() * pikinimCyan.getCantidad();
        capacidadTotal += pikinimMagenta.getCapacidad() * pikinimMagenta.getCantidad();
        capacidadTotal += pikinimAmarillo.getCapacidad() * pikinimAmarillo.getCantidad();

        if (capacidadTotal >= peso) {
            System.out.println(": Tal cual un azteca, te comeras a tu enemigo para hacer tuya su fuerza");
            System.out.println("? Que color de pikinim quieres aumentar?");
            System.out.println("? 1. Cyan   2. Magenta   3. Amarillo");
            System.out.print("? Elección: ");

            int eleccion = Juego.input.nextInt();

            if (eleccion == 1) {
                pikinimCyan.multiplicar(this.peso);
                System.out.println("\n: Los pikinim cyan ahora son " + pikinimCyan.getCantidad() + "!\n");
            } else if (eleccion == 2) {
                pikinimMagenta.multiplicar(this.peso);
                System.out.println("\n: Los pikinim magenta ahora son " + pikinimMagenta.getCantidad() + "!\n");
            } else if (eleccion == 3) {
                pikinimAmarillo.multiplicar(this.peso);
                System.out.println("\n: Los pikinim amarillos ahora son " + pikinimAmarillo.getCantidad() + "!\n");
            }

            this.completada = true;
        }
    }
}