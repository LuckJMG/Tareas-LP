% código de la cerradura
cerradura(1, 4, 5, 1, 0).

/* valor(X, I): Relación de valor con posicion en la cerradura.
 *
 * - X: Valor de la cerradura.
 * - I: Indice de posición.
 */
valor(X, 1) :- cerradura(X, _, _, _, _).
valor(X, 2) :- cerradura(_, X, _, _, _).
valor(X, 3) :- cerradura(_, _, X, _, _).
valor(X, 4) :- cerradura(_, _, _, X, _).
valor(X, 5) :- cerradura(_, _, _, _, X).

/* distancia(X, I, D): Relación de distancia entre un valor X y el valor
 * de la cerradura en la posición I.
 *
 * - X: Valor probado.
 * - I: Posición a comprobar la distancia
 * - D: Distancia entre ambos valores.
 */
distancia(X, I, D) :- valor(Y, I), Y >= X, D is Y - X.
distancia(X, I, D) :- valor(Y, I), Y < X, D is X - Y.

/* verificar(X1, X2, X3, X4, X5, R): Te dice que tan cerca estas de
 * encontrar el valor de la contraseña de la cerradura.
 *
 * - Xn: Intento de valor X en la posición n de la cerradura.
 * - R: Resultado del intento, si estas cerca, lejos o acertaste.
 */
verificar(X1, X2, X3, X4, X5, "Contraseña descubierta") :- cerradura(X1, X2, X3, X4, X5).

verificar(X1, X2, X3, X4, X5, "Cerca") :- distancia(X1, 1, D1), distancia(X2, 2, D2), distancia(X3, 3, D3), distancia(X4, 4, D4), distancia(X5, 5, D5), (D1 + D2 + D3 + D4 + D5) / 5 < 1.

verificar(X1, X2, X3, X4, X5, "Lejos") :- distancia(X1, 1, D1), distancia(X2, 2, D2), distancia(X3, 3, D3), distancia(X4, 4, D4), distancia(X5, 5, D5), (D1 + D2 + D3 + D4 + D5) / 5 > 1.
