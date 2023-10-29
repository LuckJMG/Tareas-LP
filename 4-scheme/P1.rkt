#lang scheme

;; len - funcion que calcula el largo de una lista
;; mediante recursion simple.
;;
;; lista: lista a la que calcularle el largo
(define (len lista)
  (if (null? lista)
      0
      (+ 1 (len (cdr lista)))))

;; checkear - Verifica que la lista tenga la cantidad
;; dada de items para devolver true, en caso contrario
;; devuelve false
;;
;; cantidad: cantidad de items que debe de tener la lista
;; lista: lista a verificar
(define (checkear cantidad lista) (= cantidad (len lista)))
