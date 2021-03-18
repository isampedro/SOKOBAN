# TP1 SOKOBAN SOLVER

**Para ejecutar el programa, posicionarse desde la terminal en el root del proyecto, y ejecutar el siguiente comando:**

`java -jar TP1.jar [nro_nivel] [algoritmo_de_búsqueda] [heurística]`

**[nro_nivel]:** representa el tablero a ser utilizado para el juego. Se dispone de tres tableros distintos. Por lo tanto, los valores que puede tomar [nro_nivel] son “1”, “2”, o “3”. En caso de ingresar un valor fuera de este rango, el sistema lanzará un mensaje de error.

**[algoritmo_de_búsqueda]:** representa la clase de algoritmo con el cual queremos realizar la búsqueda de la solución. Se dispone de seis algoritmos de búsqueda distintos. Los valores que puede tomar [algoritmo_de_búsqueda] son: “BFS”, “DFS”, “IDDFS”, “AStar”, “GGS” o “IDAStar”.

**[heurística]:** representa la heurística a ser utilizada por el algoritmo de búsqueda informado. Se dispone de cuatro heurísticas distintas.  Los valores que puede tomar [heurística] son:  "ManhattanBO” (Manhattan Distance Box Objective), “ManhattanPBO” (Manhattan Distance Player Box Objective), “MinMatching” (Minimum Matching Lower Bound) o “Corner” (Corner Dead End + Manhattan Distance Box Objective). En caso de ingresar una heurística al usar un algoritmo de búsqueda no informado, el sistema va a ignorar la heurística. En caso de que se este usando un algoritmo de búsqueda informado y se ingrese una heurística incorrecta, el sistema lanzará un error.


**Casos de uso de ejemplo:**

`java -jar TP1.jar 1 BFS`

`java -jar TP1.jar 2 IDAStar ManhattanPBO`
