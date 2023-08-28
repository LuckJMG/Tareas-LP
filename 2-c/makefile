output: TreasureFinder.c Bomba.o Tablero.o
	gcc Bomba.o Tablero.o TreasureFinder.c -o output

Bomba.o: Bomba.c
	gcc -c Bomba.c

Tablero.o: Tablero.c
	gcc -c Tablero.c

clean:
	rm *.o
	rm output
