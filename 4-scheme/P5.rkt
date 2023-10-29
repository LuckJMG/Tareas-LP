#lang scheme

;; len - funcion que calcula el largo de una lista
;; mediante recursion simple.
;;
;; lista: lista a la que calcularle el largo
(define (len lista)
  (if (null? lista)
      0
      (+ 1 (len (cdr lista)))))

;; buscar_utensilio - busca el camino más corto hacia el
;; utensilio dado dentro del arból binario entregado, si no
;; lo encuentra devuelve "no está"
;;
;; utensilio: utensilio a encontrar
;; arbol: arbol binario en donde buscar
(define (buscar_utensilio utensilio arbol)
  (if (or (null? arbol) (null? utensilio))
      "no está"
      (let ((parent (car arbol))
            (left (buscar_utensilio utensilio (car (cdr arbol))))
            (right (buscar_utensilio utensilio (car (cdr (cdr arbol))))))
        (if (eqv? parent (car utensilio))
            '()
            (if (eqv? left "no está")
                (if (eqv? right "no está")
                    "no está"
                    (cons parent right))
                (if (eqv? right "no está")
                    (cons parent left)
                    (if (<= (len left) (len right))
                        (cons parent left)
                        (cons parent right))))))))
