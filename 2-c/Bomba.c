#include "Tierra.h"
#include "Tablero.h"
#include "Bomba.h"
#include <stdlib.h>

void TryExplotar(int fila, int columna)
{
    Bomba *bomba = (Bomba *)tablero[fila][columna];
    bomba->contador_turnos--;
    if (bomba->contador_turnos == 0)
    {
        bomba->explotar(fila, columna);
        BorrarBomba(fila, columna);
    }

    return;
}

void BorrarBomba(int fila, int columna)
{
    if (!tablero_bombas[fila][columna])
        return;

    Bomba *bomba = (Bomba *)tablero[fila][columna];
    tablero[fila][columna] = bomba->tierra_debajo;
    tablero_bombas[fila][columna] = 0;
    free(bomba);

    return;
}

void ExplosionPunto(int fila, int columna)
{
    Bomba *bomba = (Bomba *)tablero[fila][columna];
    if (bomba->tierra_debajo->vida > 0)
        bomba->tierra_debajo->vida -= bomba->tierra_debajo->vida;

    return;
}

void ExplosionX(int fila, int columna)
{
    int fila_arriba = SerieCircular(fila, 1);
    int fila_abajo = SerieCircular(fila, 0);
    int columna_izquierda = SerieCircular(columna, 1);
    int columna_derecha = SerieCircular(columna, 0);

    BajarVida(fila, columna);
    BajarVida(fila_arriba, columna_izquierda);
    BajarVida(fila_arriba, columna_derecha);
    BajarVida(fila_abajo, columna_izquierda);
    BajarVida(fila_abajo, columna_derecha);

    return;
}

int SerieCircular(int n, int resta)
{
    if (n == 0 && resta)
        return dimension - 1;
    else if (n == dimension - 1 && !resta)
        return 0;
    else
    {
        if (resta)
            return n - 1;
        else
            return n + 1;
    }
}

void BajarVida(int fila, int columna)
{
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

    return;
}
