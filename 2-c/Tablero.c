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

/*
 * Nombre: IniciarTablero
 *
 * Descripción: Inicializar el tablero principal y el
 * tablero de bombas según la dimension dada
 *
 * Parametros:
 * - int n, dimension del tablero
 */
void IniciarTablero(int n)
{
    int max_vida = 3;
    int porcentaje_tesoro = 5;

    // Inicializar tablero principal y tablero de tipos
    dimension = n;
    tablero = (void ***)malloc(sizeof(void **) * dimension);
    tablero_bombas = (int **)malloc(sizeof(int *) * dimension);

    for (int fila = 0; fila < dimension; fila++)
    {
        // Inicializar filas
        tablero[fila] = (void **)malloc(sizeof(void *) * dimension);
        tablero_bombas[fila] = (int *)malloc(sizeof(int) * dimension);

        for (int columna = 0; columna < dimension; columna++)
        {
            // Inicializar columnas
            tablero_bombas[fila][columna] = 0;

            Tierra *tierra = (Tierra *)malloc(sizeof(Tierra));
            tierra->vida = rand() % max_vida + 1;
            tierra->es_tesoro = rand() % 100 + 1 <= porcentaje_tesoro;
            tablero[fila][columna] = tierra;

            if (tierra->es_tesoro)
                cantidad_tesoros++;
        }
    }

    // Rehacer tablero si no hay tesoros
    if (cantidad_tesoros == 0)
    {
        BorrarTablero();
        IniciarTablero(n);
    }
}

/*
 * Nombre: PasarTurno
 *
 * Descripción: Pasa el turno, intentado explotar
 * todas las bombas del tablero
 */
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
        v
    }
}

/*
 * Nombre: ColocarBomba
 *
 * Descripción: Coloca la bomba dada en las
 * coordanadas dadas
 *
 * Parametros:
 * - Bomba *b, puntero a la bomba a colorcar
 * - int fila, fila a colocar la bomba
 * - int columna, columna a colocar la bomba
 */
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

/*
 * Nombre: MostrarTablero
 *
 * Descripción: Muestra el tablero con las vidas de las tierra,
 * las bombas colocadas y los tesoros descubiertos
 */
void MostrarTablero()
{
    tesoros_encontrados = 0;
    for (int fila = 0; fila < dimension; fila++)
    {
        for (int columna = 0; columna < dimension; columna++)
        {
            // Poner barras entre celdas, no al inicio ni al final
            char bar = '|';
            if (columna == 0)
                bar = '\0';

            // Mostrar bombas
            if (tablero_bombas[fila][columna])
            {
                printf("%c o ", bar);
                continue;
            }

            Tierra *tierra = (Tierra *)tablero[fila][columna];
            // Mostrar tesoros descubiertos
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
}

/*
 * Nombre: MostrarBombas
 *
 * Descripción: Muestra la descripción de las bombas que
 * están en el tablero
 */
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

/*
 * Nombre: VerTesoros
 *
 * Descripción: Para debuguear, muestra todos los tesoros del tablero
 * sobre el MostrarTablero normal.
 */
void VerTesoros()
{
    for (int fila = 0; fila < dimension; fila++)
    {
        for (int columna = 0; columna < dimension; columna++)
        {
            // Poner barras entre celdas, no al inicio ni al final
            char bar = '|';
            if (columna == 0)
                bar = '\0';

            // Mostrar bombas, tesoros tienen prioridad sobre las bombas
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
            // Mostrar tesoros
            if (tierra->es_tesoro)
            {
                printf("%c * ", bar);
                continue;
            }

            printf("%c %d ", bar, ((Tierra *)tablero[fila][columna])->vida);
        }
        printf("\n");
    }
}

/*
 * Nombre: BorrarTablero
 *
 * Descripción: Borra la memoria de ambos tableros,
 * borrando los punteros a tierra y a bombas
 */
void BorrarTablero()
{
    for (int fila = 0; fila < dimension; fila++)
    {
        for (int columna = 0; columna < dimension; columna++)
        {
            // Borrar columnas, ya sean bombas o tierras
            if (tablero_bombas[fila][columna])
                free(((Bomba *)tablero[fila][columna])->tierra_debajo);
            free(tablero[fila][columna]);
        }

        // Borrar filas
        free(tablero_bombas[fila]);
        free(tablero[fila]);
    }

    // Borrar tableros
    free(tablero_bombas);
    free(tablero);
}
