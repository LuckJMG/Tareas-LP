#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Tierra.h"
#include "Tablero.h"
#include "Bomba.h"

int main(int argc, char const *argv[])
{
    IniciarTablero(7);
    MostrarTablero();
    printf("---------\n");
    VerTesoros();
    printf("---------\n");
    Bomba bomba;
    bomba.contador_turnos = 1;
    bomba.explotar = &ExplosionPunto;
    bomba.tierra_debajo = NULL;
    ColocarBomba(&bomba, 3, 4);
    MostrarTablero();
    BorrarTablero();
    return 0;
}

/* EJEMPLOS DE RANDOM PARA FACILITAR SU USO.
 *
 *   srand(time(0)); // Setea la seed del random.
 *   int ejemplo_vida = (rand() % 3) + 1; // Obtiene al azar la vida de Tierra a asignar.
 */
