#ifndef TABLERO_H
#define TABLERO_H
#include "Bomba.h"

extern void ***tablero;
extern int dimension; // 7 en 7x7, 10 en 10x10, 12 en 12x12
extern int **tablero_bombas;
extern int cantidad_tesoros;
extern int tesoros_encontrados;

void IniciarTablero(int n);
void PasarTurno();
void ColocarBomba(Bomba *b, int fila, int columna);
void MostrarTablero();
void MostrarBombas();
void VerTesoros();
void BorrarTablero();
#endif
