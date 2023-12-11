% Relaciones de quien sigue a quien
sigue(p1, p2).
sigue(p1, p9).
sigue(p1, p15).
sigue(p2, p3).
sigue(p3, p4).
sigue(p4, p5).
sigue(p5, p6).
sigue(p6, p7).
sigue(p7, p8).
sigue(p7, p17).
sigue(p8, p1).
sigue(p9, p10).
sigue(p10, p11).
sigue(p11, p12).
sigue(p12, p13).
sigue(p13, p14).
sigue(p15, p16).
sigue(p16, p20).
sigue(p17, p18).
sigue(p18, p19).
sigue(p20, p21).
sigue(p21, p22).

/* member(X, L): Verifica si X pertenece a L.
 *
 *  - X: Valor a verificar.
 *  - L: Lista en la que verificar.
 */
member(X, [X | _]).
member(X, [_ | C]) :- member(X, C).

/* helper(F, P, L, R): Función de ayuda para "principal" que ocupa recursón
 * para recorrer el grafo y determinar si la persona indicada esta
 * dentro de la fila principal o no.
 *
 * - F: Persona que se quiere determinar si esta en la fila principal.
 * - P: Persona actual que esta en la cadena que sigue a F.
 * - L: Lista de personas en la cadena que sigue a F.
 * - R: Respuesta de si F está o no en la fila principal.
 */
helper(F, P, L, "Es de la rama principal") :- P == F, delete(L, F, L1), print(L1).
helper(_, P, L, "No es de la rama principal") :- sigue(P1, P), member(P1, L), print(L).
helper(F, P, L, R) :- sigue(P1, P), append(L, [P1], L1), helper(F, P1, L1, R).

/* principal(P, R): Determina si la persona indicada es parte o no de la fila
 *  principal del grafo de relaciones con ayuda de la función "helper".
 *
 * - P: Persona a verificar.
 * - R: Respuesta de si P está o no en la fila principal.
 */
principal(P, R) :- sigue(P1, P), helper(P, P1, [P1], R).






