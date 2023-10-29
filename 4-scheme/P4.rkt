#lang scheme

;; ingrediente_in_lista - función que retorna si
;; el ingrediente dado se encuentra dentro de la lista
;; dada o no
;;
;; ingrediente: ingrediente a buscar
;; lista: lista de ingredientes en la que se busca
(define (ingrediente_in_lista ingrediente lista)
  (if (null? lista)
      #f
      (if (eqv? ingrediente (car lista))
          #t
          (ingrediente_in_lista ingrediente (cdr lista)))))

;; buscar_recetas - busca todas las recetas dentro de una lista
;; en la que aparezca el ingrediente dado como uno de los ingredientes
;;
;; ingrediente: ingrediente a buscar
;; lista: lista de recetas en donde buscar
(define (buscar_recetas ingrediente lista)
  (if (null? lista)
      '()
      (if (null? ingrediente)
          '()
          (if (ingrediente_in_lista (car ingrediente) (cdr (car lista)))
              (cons (car (car lista)) (buscar_recetas ingrediente (cdr lista)))
              (buscar_recetas ingrediente (cdr lista))))))
