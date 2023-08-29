#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Tierra.h"
#include "Tablero.h"
#include "Bomba.h"

int main(int argc, char const *argv[])
{
    srand(time(NULL));
    printf("¡Bienvenido a TreasureFinder!\n");
    printf("Indique el tamaño del tablero a jugar:\n");
    printf("1.7x7 2.10x10 3.12x12\n");

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

    default:
        break;
    }

    int turno = 1;
    printf("Empezando juego... ¡listo!\n");
    printf("Tablero (Turno %d)\n", turno);
    MostrarTablero();
    while (1)
    {
        if (cantidad_tesoros == tesoros_encontrados)
            break;
        printf("Seleccione una acción:\n");
        printf("1.Colocar Bomba 2.Mostrar Bombas 3.Mostrar Tesoros\n");
        printf("Escoja una opcion: ");
        scanf("%d", &option);
        switch (option)
        {
        case 1:
            Bomba *bomba = (Bomba *)malloc(sizeof(Bomba));
            int fila;
            int columna;

            printf("Indique las coordenadas de la bomba\n");
            printf("Fila: ");
            scanf("%d", &fila);
            printf("Columna: ");
            scanf("%d", &columna);
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

            default:
                break;
            }

            ColocarBomba(bomba, fila - 1, columna - 1);
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

        default:
            break;
        }
    }
    printf("Felicidades, encontraste todos los tesoros!!!\n");
    BorrarTablero();

    return 0;
}
