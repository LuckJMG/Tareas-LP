% relaciones de codigo binario con su respectiva base de ADN
cifrado([0,0], a).
cifrado([0,1], g).
cifrado([1,0], c).
cifrado([1,1], t).

/*
 * descifrar(L1, L2): Relación lista de codigo binario y lista
 * de bases de ADN, ocupa recursión para descifrar la lista completa.
 *
 * - L1 = [A | [B| R1]], lista en codigo binario, con A y B los primeros
 * 2 digitos de la lista y R1 la lista restante.
 *
 * - L2 = [C | R2], lista en bases de ADN, con C la base de ADN y R2 el
 * resto.
 */
descifrar([], []).
descifrar([A | [B | R1]], [C | R2]) :- cifrado([A, B], C), descifrar(R1, R2).
