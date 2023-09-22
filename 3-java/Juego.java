import java.util.Scanner;

public class Juego {
    public static Scanner input;

    public static void main(String[] args) {
        Juego.input = new Scanner(System.in);

        Zona[] mapa = {
                new Pieza(50),
                new Enemigo(130, 20, 25),
                new Enemigo(50, 10, 15),
                new Pildora(25),
                new Muralla(50),
                new Pieza(100),
                new Enemigo(45, 8, 10),
                new Pieza(35),
                new Pildora(15),
                new Enemigo(75, 15, 20),
                new Muralla(150),
        };

        Cyan pikinimCyan = new Cyan();
        Magenta pikinimMagenta = new Magenta();
        Amarillo pikinimAmarillo = new Amarillo();

        int posicion = 5;
        int turno = 0;
        int direccion = 0; // 1 izqueirda, 2 derecha
        int noPuedePasar = 0; // 0 se puede pasar, 1 no se puede pasar por la izquierda, 2 no se puede pasar
                              // por la derecha

        System.out.println("> MAL ATERRIZAJE");
        System.out.println(
                "> Lomiar: Choqué con un asteroide en un viaje de rutina a comprar leche para mis hijos y ahora me encuentro solo en un planeta extraño.");
        System.out.println("> Mi nave está destrozada y no sé a dónde fueron a parar sus piezas.");
        System.out.println(
                "> Por suerte he encontrado unas criaturas curiosas, a lo mejor pueden ayudarme a volver a casa.");
        System.out.println("> (Convenientemente para el plot) Se ven emocionados de conocerme.");
        System.out.println("> Los llamaré pikinims (nombre protegido bajo las leyes de copyright sansanas ©),");
        System.out.println("> porque cada uno puede levantar exactamente un kilopikinim de peso.");
        System.out.println(
                "> Debo ser veloz, no queda tanto para que se acabe el tanque de oxígeno, solo me quedan 30 horas.\n");

        while (true) {
            turno++;
            if (turno > 30) {
                System.out
                .println(": Mira lo que has hecho, Lomiar se ha quedado sin oxigeno y ha muerto por tu culpa.");
                System.out.println(
                    ": Ahora su cuerpo quedará en este planeta por siempre, y sus hijos nunca volveran a saber de el.");
                    System.out.println(": Lo ultimo que recordaran fue que les dijo que iba a salir a comprar leche.");
                    System.out.println("\n\n=== GAME OVER ===\n\n");
                    break;
            }

            if (Pieza.piezasEncontradas >= 3) {
                System.out.println(": Has encontrado todas las piezas!!!");
                System.out.println(": Quien diria que lograrias conseguirlas a tiempo.");
                System.out.println(
                        ": Ahora escapas del planeta, dejando abandonados a los pikinims que hicieron la mayor parte del trabajo. Que agradecido eres.");
                System.out.println(
                        ": Ahora por fín podrás ir a comprar la leche para tus hijos y volver para el desayuno.");
                System.out.println("\n\n=== YOU WIN===\n\n");
                break;
            }
            
            System.out.println("\n----------------------------------------------------------------------");

            
            System.out.println("| Horas de Oxigeno Restante: " + (31 - turno) + " horas");
            System.out.println("| Cantidad de pikinims: Cyan " + pikinimCyan.getCantidad() + " - Magenta "
            + pikinimMagenta.getCantidad() + " - Amarillos " + pikinimAmarillo.getCantidad());
            System.out.println("| Piezas Encontradas: " + Pieza.piezasEncontradas + "/3");
            System.out.println("| Zona Actual: " + mapa[posicion].getClass().getName());

            mapa[posicion].interactuar(pikinimCyan, pikinimMagenta, pikinimAmarillo);
            
            System.out.println("? Hacia donde quieres ir?");
            
            if (mapa[posicion].getClass().getName() == "Muralla" && !mapa[posicion].isCompletada()) {
                if (direccion == 1) {
                    System.out.println(
                            "? 2. Derecha (" + mapa[posicion + 1].getClass().getName() + ")   3. Quedarse aquí");
                    noPuedePasar = 1;
                } else if (direccion == 2) {
                    System.out.println(
                            "? 1. Izquierda (" + mapa[posicion - 1].getClass().getName() + ")   3. Quedarse aquí");
                    noPuedePasar = 2;
                }
            } else {
                int posicionIzq = 0;
                if (posicion == 0) {
                    posicionIzq = 10;
                } else {
                    posicionIzq = posicion - 1;
                }

                int posicionDer = 0;
                if (posicion == 10) {
                    posicionDer = 0;
                } else {
                    posicionDer = posicion + 1;
                }

                System.out.println("? 1. Izquierda (" + mapa[posicionIzq].getClass().getName() + ")   2. Derecha ("
                        + mapa[posicionDer].getClass().getName() + ")   3. Quedarse aquí");
                noPuedePasar = 0;
            }

            System.out.print("? Elección: ");
            int eleccion = input.nextInt();

            if (eleccion == 1 && !(noPuedePasar == 1)) {
                if (posicion == 0) {
                    posicion = 10;
                } else {
                    posicion--;
                }

                direccion = 1;
            } else if (eleccion == 2 && !(noPuedePasar == 2)) {
                if (posicion == 10) {
                    posicion = 0;
                } else {
                    posicion++;
                }

                direccion = 2;
            }
        }

        System.out.println("======================================================================");
        System.out.println("| PUNTAJE OBTENIDO");
        System.out.println("======================================================================");
        System.out.println("| Horas de Oxigeno Restante: " + (31 - turno) + " horas");
        System.out.println("| Cantidad de pikinims: Cyan " + pikinimCyan.getCantidad() + " - Magenta "
                + pikinimMagenta.getCantidad() + " - Amarillos " + pikinimAmarillo.getCantidad());
        System.out.println("| Piezas Encontradas: " + Pieza.piezasEncontradas + "/3");
        System.out.println("======================================================================");

        Juego.input.close();
    }
}