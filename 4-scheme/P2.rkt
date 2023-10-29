#lang scheme

;; helper - funcion auxiliar de cantidades_cola, ocupa una lista carry
;; para ir guardando los calculos que va haciendo y no tener que esperar
;; a calcular todos los valores. Esta lista carry se pasa al siguiente nivel
;; de recursion hasta que la lista 'lista' esta vacia.
;;
;; base: valor al que aplicarle las funciones de lista
;; lista: lista de funciones que aplicar
;; carry: lista que guarda los valores computados previamente
(define (helper base lista carry)
  (if (null? lista)
      carry
      (helper base
              (cdr lista)
              (append carry (list ((car lista) base))))))

;; cantidades_simple - mapea un numero base a traves de una lista
;; de funciones para devolver una lista de los resultados, esto a
;; traves de recursion simple
;;
;; base: valor a mapear
;; lista: lista de funciones
(define (cantidades_simple base lista)
  (if (null? lista)
      '()
      (cons
       ((car lista) base)
       (cantidades_simple base (cdr lista)))))

;; cantidades_cola - mapea un numero base a traves de una lista
;; de funciones para devolver una lista de los resultados, esto a
;; traves de recursion de cola gracias a la funcion helper
;;
;; base: valor a mapear
;; lista: lista de funciones
(define (cantidades_cola base lista) (helper base lista '()))
