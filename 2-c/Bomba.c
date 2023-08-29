#include "Tierra.h"
#include "Tablero.h"
#include "Bomba.h"
#include <stdlib.h>

/*
 * Nombre: TryExplotar
 *
 * Descripción: Disminuye el contador de la bomba que está
 * en las coordenadas dadas, una vez su contador llegue a 0
 * hace explotar a la bomba con su respectiva función y la
 * elimina.
 *
 * Parametros:
 * - int fila, fila de la bomba a explotar
 * - int columna, columna de la bomba a explotar
 */
void TryExplotar(int fila, int columna)
{
    Bomba *bomba = (Bomba *)tablero[fila][columna];
    bomba->contador_turnos--;
    if (bomba->contador_turnos == 0)
    {
        bomba->explotar(fila, columna);
        BorrarBomba(fila, columna);
    }
}

/*
 * Nombre: BorrarBomba
 *
 * Descripción: Elimina la bomba, dejando la tierra
 * que estaba debajo en el tablero.
 *
 * Parametros:
 * - int fila, fila de la bomba a eliminar
 * - int columna, columna de la bomba a eliminar
 */
void BorrarBomba(int fila, int columna)
{
    Bomba *bomba = (Bomba *)tablero[fila][columna];
    tablero[fila][columna] = bomba->tierra_debajo;
    tablero_bombas[fila][columna] = 0;
    free(bomba);
}

/*
 * Nombre: ExplosionPunto
 *
 * Descripción: Baja la vida de la tierra debajo
 * de la bomba a 0.
 *
 * Parametros:
 * - int fila, fila de la bomba a explotar en punto
 * - int columna, columna de la bomba a explotar en punto
 */
void ExplosionPunto(int fila, int columna)
{
    Bomba *bomba = (Bomba *)tablero[fila][columna];
    if (bomba->tierra_debajo->vida > 0)
        bomba->tierra_debajo->vida -= bomba->tierra_debajo->vida;
}

/*
 * Nombre: ExplosionX
 *
 * Descripción: Baja la vida de la tierra debajo de la bomba
 * y las tierras en las esquinas en 1 punto.
 *
 * Parametros:
 * - int fila, fila de la bomba a explotar en X
 * - int columna, columna de la bomba a explotar en X
 */
void ExplosionX(int fila, int columna)
{
    // Determinar rango de explosión
    int fila_arriba = SerieCircular(fila, 1);
    int fila_abajo = SerieCircular(fila, 0);
    int columna_izquierda = SerieCircular(columna, 1);
    int columna_derecha = SerieCircular(columna, 0);

    // Bajar la vidas de las tierras correspondientes
    BajarVida(fila, columna);
    BajarVida(fila_arriba, columna_izquierda);
    BajarVida(fila_arriba, columna_derecha);
    BajarVida(fila_abajo, columna_izquierda);
    BajarVida(fila_abajo, columna_derecha);
}

/*
 * Nombre: SerieCircular
 *
 * Descripción: Toma un index de array y le suma o le resta 1
 * según lo especificado, si el número sale del rango del array
 * lo devuelve al otro lado.
 *
 * Parametros:
 * - int n, número a sumarle o restarle
 * - int resta, 0 si se suma y 1 si se resta
 */
int SerieCircular(int n, int resta)
{
    if (n == 0 && resta) // Cambio de 0 - 1 a dimension - 1
        return dimension - 1;
    else if (n == dimension - 1 && !resta) // Cambio de dimension a 0
        return 0;

    if (resta)
        return n - 1;
    else
        return n + 1;
}

/*
 * Nombre: BajarVida
 *
 * Descripción: Baja la vida en 1 de la tierra
 * en las coordenadas dadas, mantiendo la vida >= 0
 *
 * Parametros:
 * - int fila, fila de la tierra a bajarle la vida
 * - int columna, columna de la tierra a bajarle la vida
 */
void BajarVida(int fila, int columna)
{
    // Si hay una bomba, bajarle la vida a la tierra debajo
    if (tablero_bombas[fila][columna])
    {
        if (((Bomba *)tablero[fila][columna])->tierra_debajo->vida > 0)
            ((Bomba *)tablero[fila][columna])->tierra_debajo->vida--;
    }
    else
    {
        if (((Tierra *)tablero[fila][columna])->vida > 0)
            ((Tierra *)tablero[fila][columna])->vida--;
    }
}
