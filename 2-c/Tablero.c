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

    for (int fila = 0; fila < dimension; fila++)
    {
        tablero[fila] = (void **)malloc(sizeof(void *) * dimension);
        tablero_bombas[fila] = (int *)malloc(sizeof(int) * dimension);

        for (int columna = 0; columna < dimension; columna++)
        {
            Tierra *tierra = (Tierra *)malloc(sizeof(Tierra));
            tierra->vida = rand() % max_vida + 1;
            tierra->es_tesoro = rand() % 100 + 1 <= porcentaje_tesoro;
            tablero[fila][columna] = tierra;

            if (tierra->es_tesoro)
                cantidad_tesoros++;
            tablero_bombas[fila][columna] = 0;
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
    for (int fila = 0; fila < dimension; fila++)
    {
        for (int columna = 0; columna < dimension; columna++)
        {
            if (!tablero_bombas[fila][columna])
                continue;

            TryExplotar(fila, columna);
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
    for (int fila = 0; fila < dimension; fila++)
    {
        for (int columna = 0; columna < dimension; columna++)
        {
            char bar = '|';
            if (columna == 0)
                bar = '\0';

            if (tablero_bombas[fila][columna])
            {
                printf("%c o ", bar);
                continue;
            }

            Tierra *tierra = (Tierra *)tablero[fila][columna];
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
    for (int fila = 0; fila < dimension; fila++)
    {
        for (int columna = 0; columna < dimension; columna++)
        {
            if (!tablero_bombas[fila][columna])
                continue;

            Bomba *bomba = (Bomba *)tablero[fila][columna];
            printf("Turnos para explotar: %d\n", bomba->contador_turnos);
            printf("Coordenada: %d %d\n", fila, columna);
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
    for (int fila = 0; fila < dimension; fila++)
    {
        for (int columna = 0; columna < dimension; columna++)
        {
            char bar = '|';
            if (columna == 0)
                bar = '\0';

            if (tablero_bombas[fila][columna])
            {
                Bomba *bomba = (Bomba *)tablero[fila][columna];
                if (bomba->tierra_debajo->es_tesoro)
                {
                    printf("%c * ", bar);
                    continue;
                }

                printf("%c o ", bar);
                continue;
            }

            Tierra *tierra = (Tierra *)tablero[fila][columna];
            if (tierra->es_tesoro)
            {
                printf("%c * ", bar);
                continue;
            }

            printf("%c %d ", bar, ((Tierra *)tablero[fila][columna])->vida);
        }
        printf("\n");
    }

    return;
}

void BorrarTablero()
{
    for (int fila = 0; fila < dimension; fila++)
    {
        for (int columna = 0; columna < dimension; columna++)
        {
            if (tablero_bombas[fila][columna])
                free(((Bomba *)tablero[fila][columna])->tierra_debajo);
            free(tablero[fila][columna]);
        }

        free(tablero_bombas[fila]);
        free(tablero[fila]);
    }

    free(tablero_bombas);
    free(tablero);
    return;
}
