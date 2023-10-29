#lang scheme

;; len - funcion que calcula el largo de una lista
;; mediante recursion simple.
;;
;; lista: lista a la que calcularle el largo
(define (len lista)
  (if (null? lista)
      0
      (+ 1 (len (cdr lista)))))

;; armar_lista - arma una lista de compra con los productos
;; que faltan y cuanto falta de esos productos
;;
;; stock: lista de pares de (cantidad_desada lista_producto)
(define (armar_lista stock)
  (if (null? stock)
      '()
      (let ((cantidad (car (car stock))) (lista (car (cdr (car stock)))))
        (if (= (- cantidad (len lista)) 0)
            (armar_lista (cdr stock))
            (cons (list (- cantidad (len lista)) (car lista)) (armar_lista (cdr stock)))))))
