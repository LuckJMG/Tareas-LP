#include "Tablero.h"
#include "Bomba.h"
#include "Tierra.h"
#include <stdlib.h>
#include <stdio.h>

int dimension = 0;
void ***tablero = NULL;
int **tablero_bombas = NULL; // 0 indica tierra y 1 indica bomba
int cantidad_tesoros = 0;
int tesoros_encontrados = 0;

void IniciarTablero(int n)
{
    int max_vida = 3;
    int porcentaje_tesoro = 5;

    // Inicializar tablero principal y tablero de tierra
    dimension = n;
    tablero = (void ***)malloc(sizeof(void **) * dimension);
    tablero_bombas = (int **)malloc(sizeof(int *) * dimension);

    for (int row = 0; row < dimension; row++)
    {
        tablero[row] = (void **)malloc(sizeof(void *) * dimension);
        tablero_bombas[row] = (int *)malloc(sizeof(int) * dimension);

        for (int column = 0; column < dimension; column++)
        {
            Tierra *tierra = (Tierra *)malloc(sizeof(Tierra));
            tierra->vida = rand() % max_vida + 1;
            tierra->es_tesoro = rand() % 100 + 1 <= porcentaje_tesoro;
            tablero[row][column] = tierra;

            if (tierra->es_tesoro)
                cantidad_tesoros++;
            tablero_bombas[row][column] = 0;
        }
    }

    // Rehacer tablero si no hay tesoros
    if (cantidad_tesoros == 0)
    {
        BorrarTablero();
        IniciarTablero(n);
    }

    return;
}

void PasarTurno()
{
    for (int row = 0; row < dimension; row++)
    {
        for (int column = 0; column < dimension; column++)
        {
            if (!tablero_bombas[row][column])
                continue;

            TryExplotar(row, column);
        }
    }

    return;
}

void ColocarBomba(Bomba *b, int fila, int columna)
{
    // No agregar si ya existe una bomba
    if (tablero_bombas[fila][columna])
        return;

    b->tierra_debajo = (Tierra *)tablero[fila][columna];
    tablero[fila][columna] = b;
    tablero_bombas[fila][columna] = 1;
    return;
}

void MostrarTablero()
{
    tesoros_encontrados = 0;
    for (int row = 0; row < dimension; row++)
    {
        for (int column = 0; column < dimension; column++)
        {
            char bar = '|';
            if (column == 0)
                bar = '\0';

            if (tablero_bombas[row][column])
            {
                printf("%c o ", bar);
                continue;
            }

            Tierra *tierra = (Tierra *)tablero[row][column];
            if (tierra->es_tesoro && tierra->vida == 0)
            {
                printf("%c * ", bar);
                tesoros_encontrados++;
                continue;
            }

            printf("%c %d ", bar, tierra->vida);
        }

        printf("\n");
    }

    return;
}

void MostrarBombas()
{
    for (int row = 0; row < dimension; row++)
    {
        for (int column = 0; column < dimension; column++)
        {
            if (!tablero_bombas[row][column])
                continue;

            Bomba *bomba = (Bomba *)tablero[row][column];
            printf("Turnos para explotar: %d\n", bomba->contador_turnos);
            printf("Coordenada: %d %d\n", row, column);
            if (bomba->explotar == *ExplosionPunto)
                printf("Forma Explosión: ExplosionPunto\n");
            else
                printf("Froma Explosión: ExplosionX\n");
            printf("Vida de Tierra Debajo: %d\n", bomba->tierra_debajo->vida);
        }
    }

    return;
}

void VerTesoros()
{
    for (int row = 0; row < dimension; row++)
    {
        for (int column = 0; column < dimension; column++)
        {
            char bar = '|';
            if (column == 0)
                bar = '\0';

            if (tablero_bombas[row][column])
            {
                Bomba *bomba = (Bomba *)tablero[row][column];
                if (bomba->tierra_debajo->es_tesoro)
                {
                    printf("%c * ", bar);
                    continue;
                }

                printf("%c o ", bar);
                continue;
            }

            Tierra *tierra = (Tierra *)tablero[row][column];
            if (tierra->es_tesoro)
            {
                printf("%c * ", bar);
                continue;
            }

            printf("%c %d ", bar, ((Tierra *)tablero[row][column])->vida);
        }
        printf("\n");
    }

    return;
}

void BorrarTablero()
{
    for (int row = 0; row < dimension; row++)
    {
        for (int column = 0; column < dimension; column++)
        {
            if (tablero_bombas[row][column])
                BorrarBomba(row, column);
            free(tablero[row][column]);
        }

        free(tablero_bombas[row]);
        free(tablero[row]);
    }

    free(tablero_bombas);
    free(tablero);
    return;
}
