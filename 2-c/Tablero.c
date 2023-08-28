#include "Tablero.h"
#include "Bomba.h"
#include "Tierra.h"
#include <stdlib.h>
#include <stdio.h>

int dimension = 0;
void ***tablero = NULL;
Tierra **tablero_tierra = NULL;
int **tablero_bombas = NULL;

void IniciarTablero(int n)
{
    int max_vida = 3;
    int porcentaje_tesoro = 5;

    // Inicializar tablero principal y tablero de tierra
    srand(20);
    dimension = n;
    tablero = (void ***)malloc(sizeof(void **) * dimension);
    tablero_tierra = (Tierra **)malloc(sizeof(Tierra *) * dimension);
    tablero_bombas = (int **)malloc(sizeof(int *) * dimension);

    for (int row = 0; row < dimension; row++)
    {
        tablero[row] = (void **)malloc(sizeof(void *) * dimension);
        tablero_tierra[row] = (Tierra *)malloc(sizeof(Tierra) * dimension);
        tablero_bombas[row] = (int *)malloc(sizeof(int) * dimension);

        for (int column = 0; column < dimension; column++)
        {
            tablero_bombas[row][column] = 0;
            tablero_tierra[row][column].vida = rand() % max_vida + 1;
            tablero_tierra[row][column].es_tesoro = rand() % 100 + 1 <= porcentaje_tesoro;
            tablero[row][column] = &tablero_tierra[row][column];
        }
    }

    return;
}

void PasarTurno()
{
    // Su codigo
    return;
}

void ColocarBomba(Bomba *b, int fila, int columna)
{
    b->tierra_debajo = (Tierra *)tablero[fila][columna];
    tablero[fila][columna] = b;
    tablero_bombas[fila][columna] = 1;
    return;
}

void MostrarTablero()
{
    for (int row = 0; row < dimension; row++)
    {
        // Primera Columna
        if (tablero_bombas[row][0])
        {
            printf("o |");
            continue;
        }
        printf("%d |", ((Tierra *)tablero[row][0])->vida);

        // Columnas de al medio
        for (int column = 1; column < dimension - 1; column++)
        {
            if (tablero_bombas[row][column])
            {
                printf(" o |");
                continue;
            }
            printf(" %d |", ((Tierra *)tablero[row][column])->vida);
        }

        // Ultima Columna
        if (tablero_bombas[row][dimension - 1])
        {
            printf(" o\n");
            continue;
        }
        printf(" %d\n", ((Tierra *)tablero[row][dimension - 1])->vida);
    }

    return;
}

void MostrarBombas()
{
    // Su codigo
    return;
}

void VerTesoros()
{
    for (int row = 0; row < dimension; row++)
    {
        // Primera Columna
        if (tablero_bombas[row][0])
        {
            printf("o |");
            continue;
        }
        if (((Tierra *)tablero[row][0])->es_tesoro)
        {
            printf("* |");
            continue;
        }
        printf("%d |", ((Tierra *)tablero[row][0])->vida);

        // Columnas de al medio
        for (int column = 1; column < dimension - 1; column++)
        {
            if (tablero_bombas[row][column])
            {
                printf(" o |");
                continue;
            }
            if (((Tierra *)tablero[row][0])->es_tesoro)
            {
                printf(" * |");
                continue;
            }
            printf(" %d |", ((Tierra *)tablero[row][column])->vida);
        }

        // Ultima Columna
        if (tablero_bombas[row][dimension - 1])
        {
            printf(" o\n");
            continue;
        }
        if (((Tierra *)tablero[row][0])->es_tesoro)
        {
            printf(" *\n");
            continue;
        }
        printf(" %d\n", ((Tierra *)tablero[row][dimension - 1])->vida);
    }
    return;
}

void BorrarTablero()
{
    for (int row = 0; row < dimension; row++)
    {
        free(tablero_bombas[row]);
        free(tablero_tierra[row]);
        free(tablero[row]);
    }
    free(tablero_bombas);
    free(tablero_tierra);
    free(tablero);
    return;
}
