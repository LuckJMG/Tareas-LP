#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Tierra.h"
#include "Tablero.h"
#include "Bomba.h"

int main(int argc, char const *argv[])
{
    // Generar semilla random con el tiempo actual
    srand(time(NULL));

    // Inicio de juego
    printf("¡Bienvenido a TreasureFinder!\n");
    printf("Indique el tamaño del tablero a jugar:\n");
    printf("1.7x7 2.10x10 3.12x12\n");

    // Selección del tamaño del tablero
    int option;
    printf("Su input: ");
    scanf("%d", &option);
    switch (option)
    {
    case 1:
        IniciarTablero(7);
        break;

    case 2:
        IniciarTablero(10);
        break;

    case 3:
        IniciarTablero(12);
        break;
    }

    // Loop de juego
    int turno = 1;
    printf("Empezando juego... ¡listo!\n");
    printf("Tablero (Turno %d)\n", turno);
    MostrarTablero();
    while (1)
    {
        // Acabar juego al encontrar todos los tesoros
        if (cantidad_tesoros == tesoros_encontrados)
            break;

        // Acción del turno
        printf("Seleccione una acción:\n");
        printf("1.Colocar Bomba 2.Mostrar Bombas 3.Mostrar Tesoros\n");
        printf("Escoja una opcion: ");
        scanf("%d", &option);
        switch (option)
        {
        case 1:
            // Colocar nueva bomba
            Bomba *bomba = (Bomba *)malloc(sizeof(Bomba));
            int fila;
            int columna;

            // Coordenadas Bombas
            printf("Indique las coordenadas de la bomba\n");
            printf("Fila: ");
            scanf("%d", &fila);
            printf("Columna: ");
            scanf("%d", &columna);

            // Explosión Bomba
            printf("Indique forma en que explota la bomba\n");
            printf("1.Punto 2.X\n");
            printf("Su input: ");
            scanf("%d", &option);
            switch (option)
            {
            case 1:
                bomba->contador_turnos = 1;
                bomba->explotar = &ExplosionPunto;
                break;

            case 2:
                bomba->contador_turnos = 3;
                bomba->explotar = &ExplosionX;
                break;
            }

            ColocarBomba(bomba, fila - 1, columna - 1);

            // Siguiente turno
            turno++;
            PasarTurno();
            printf("\nTablero (Turno %d)\n", turno);
            MostrarTablero();
            break;

        case 2:
            MostrarBombas();
            break;

        case 3:
            VerTesoros();
            break;
        }
    }

    // Finalización del juego, limpieza de memoria final
    printf("Felicidades, encontraste todos los tesoros!!!\n");
    BorrarTablero();
}
