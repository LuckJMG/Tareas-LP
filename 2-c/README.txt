Nombre: Lucas Joaquín Mosquera Gaete
Rol: 202273504-k

- Compilado con gcc 11.4.0 en WSL2: Ubuntu y con GNU Make 4.3
- El archivo final generado por el makefile es "output", este
    es el archivo que se ejecuta con "./output"
- Leaks de memoria comprobados con valgrind 3.18.1

Bomba.h:
- Se agrego '#include "Tierra.h"' para compatibilidad
- Se agregaron 2 funciones, "SerieCircular" y "BajarVida", para
    mejorar el codigo de "ExplosionX"

Tablero.h:
- Se agrego '#include "Bomba.h"' para compatibilidad
- Se agrego la variable "tablero_bombas" para que Bomba.c pueda acceder
    al tablero de tipos.
- Se agregaron las variables "cantidad_tesoros" y "tesoros_encontrados" para que TreasureFinder.c
    pueda saber cuando se han encontrado todos los tesoros y así finalizar el juego.
