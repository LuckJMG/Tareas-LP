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

/* is_valor(X, I, R): Verifica si el valor dado es el valor de la cerradura
 *  en la posición dada.
 *
 * - X: Valor entregado a comprobar.
 * - I: Posición en la que comprobar el valor.
 * - R: Resutado de la comprobación, 1 si es igual, 0 en caso contrario.
 */
is_valor(X, I, 1) :- valor(Y, I), Y =:= X.
is_valor(X, I, 0) :- valor(Y, I), Y =\= X.

/* verificar(X1, X2, X3, X4, X5, R): Te dice que tan cerca estas de
 * encontrar el valor de la contraseña de la cerradura.
 *
 * - Xn: Intento de valor X en la posición n de la cerradura.
 * - R: Resultado del intento, cuantos números acertaste.
 */
verificar(X1, X2, X3, X4, X5, "Contraseña descubierta") :- cerradura(X1, X2, X3, X4, X5).

verificar(X1, X2, X3, X4, X5, R) :- is_valor(X1, 1, R1), is_valor(X2, 2, R2), is_valor(X3, 3, R3), is_valor(X4, 4, R4), is_valor(X5, 5, R5), R is R1 + R2 + R3 + R4 + R5.

