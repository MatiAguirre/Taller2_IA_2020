
/**
 *
 * @author EServicesEntertained
 */

/*
* Programa realizado por encontrar la mejor solucion de un tablero de Gato 
* Mediante algoritmo MINIMAX
* DEFINICION DE LAS FUNCIONES:
* static class Movimiento - Genera el espacio donde puede ir el movimiento en el tablero, fila y columna
 */
import java.util.Scanner;

class GatoMiniMax {

    static Scanner scan = new Scanner(System.in);

    static class Movimiento {

        int fila, columna;
    };

    static char jugador = 'x', computador = 'o';
    static int MinNc = 0;
    static int MaxNa = 0;

    /*
     * Esta funcion devuelve TRUE si existen movimiento en el tablero Falso si no
     * existen movimientos por jugar
     */
    static Boolean existenMovimientos(char tablero[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '_') {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Funcion de evaluacion de resultados Si gana X dale valor +10 Si gana O dale
     * valor -10 Si existe un empata dale valor 0
     */
    static int evaluar(char tablero[][]) {
        // Revisa en las filas si gano X u O
        for (int fila = 0; fila < 3; fila++) {
            if (tablero[fila][0] == tablero[fila][1] && tablero[fila][1] == tablero[fila][2]) {
                if (tablero[fila][0] == jugador) {
                    return +10;
                } else if (tablero[fila][0] == computador) {
                    return -10;
                }
            }
        }

        // Revisa en las columnas si gano X u O
        for (int columna = 0; columna < 3; columna++) {
            if (tablero[0][columna] == tablero[1][columna] && tablero[1][columna] == tablero[2][columna]) {
                if (tablero[0][columna] == jugador) {
                    return +10;
                } else if (tablero[0][columna] == computador) {
                    return -10;
                }
            }
        }

        // Revisa en las diagonales si gano X u O
        if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            if (tablero[0][0] == jugador) {
                return +10;
            } else if (tablero[0][0] == computador) {
                return -10;
            }
        }

        if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            if (tablero[0][2] == jugador) {
                return +10;
            } else if (tablero[0][2] == computador) {
                return -10;
            }
        }

        // Si nadie gana retorna 0
        return 0;
    }

    /*
     * Funcion euristica U(t)= Na(t)- Nc(t) Na(t) = numero de filas, columnas,
     * diagonales abiertas para MAX Nc(t) = numero de filas, columnas, diagonales
     * abiertas para MIN
     */
    static int euristica(int maxEuristica, int minEuristica) {
        int valorEuristica;
        valorEuristica = maxEuristica - minEuristica;
        return valorEuristica;
    }

    /*
     * Funcion Na(t) para el calculo de las filas, columnas y diagonales para MAX en
     * su movimiento
     */
    static int maxEuristica(char tablero[][]) {
        int maxNa = 0;
        for (int fila = 0; fila < 3; fila++) {
            if (tablero[fila][0] == tablero[fila][1] && tablero[fila][1] == tablero[fila][2]) {
                if (tablero[fila][0] == jugador) {
                    maxNa++;
                }
            }
        }
        for (int columna = 0; columna < 3; columna++) {
            if (tablero[0][columna] == tablero[1][columna] && tablero[1][columna] == tablero[2][columna]) {
                if (tablero[0][columna] == jugador) {
                    maxNa++;
                }
            }
        }
        if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            if (tablero[0][0] == jugador) {
                maxNa++;
            }
        }

        if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            if (tablero[0][2] == jugador) {
                maxNa++;
            }
        }
        return maxNa;
    }

    /*
     * Funcion Nc(t) para el calculo de las filas, columnas y diagonales para MIN en
     * su movimiento
     */
    static int minEuristica(char tablero[][]) {
        int minNc = 0;
        for (int fila = 0; fila < 3; fila++) {
            if (tablero[fila][0] == tablero[fila][1] && tablero[fila][1] == tablero[fila][2]) {
                if (tablero[fila][0] == computador) {
                    minNc++;
                }
            }
        }
        for (int columna = 0; columna < 3; columna++) {
            if (tablero[0][columna] == tablero[1][columna] && tablero[1][columna] == tablero[2][columna]) {
                if (tablero[0][columna] == computador) {
                    minNc++;
                }
            }
        }
        if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            if (tablero[0][0] == computador) {
                minNc++;
            }
        }

        if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            if (tablero[0][2] == computador) {
                minNc++;
            }
        }
        return minNc;
    }

    static char[][] replicarTablero(char tablero[][]) {
        char[][] tableroPrediccion = new char[3][3];
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[fila].length; columna++) {
                tableroPrediccion[fila][columna] = tablero[fila][columna];
            }
        }
        return tableroPrediccion;
    }

    static int maxNaEuristica(char tablero[][]) {
        int maxNa;
        char[][] tableroPrediccionMax = replicarTablero(tablero);
        /*
         * Dibuja el tablero con los posibles movimientos
         * dibujarTablero(tableroPrediccionMax); System.out.println("\n");
         */

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tableroPrediccionMax[i][j] == '_') {
                    tableroPrediccionMax[i][j] = jugador;
                }
            }
        }
        maxNa = maxEuristica(tableroPrediccionMax);
        return maxNa;
    }

    static int minNcEuristica(char tablero[][]) {
        Movimiento mov = new Movimiento();
        int minNc;
        char[][] tableroPrediccionMin = replicarTablero(tablero);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tableroPrediccionMin[i][j] == '_') {
                    tableroPrediccionMin[i][j] = computador;
                }
            }
        }
        minNc = minEuristica(tableroPrediccionMin);
        return minNc;
    }

    /*
     * Funcion minimax que considera los posibles movimientos y decide la mejor
     * solucion
     */
    static int minimax(char tablero[][], int profundidad, Boolean esMaximo) {
        int resultado = evaluar(tablero);

        /*
         * Si es maximo devuelve el valor que gano el jugador (+10)
         */
        if (resultado == 10) {
            return resultado;
        }

        /*
         * Si es minimo devuelve el valor que gano el computador (-10)
         */
        if (resultado == -10) {
            return resultado;
        }

        /*
         * Si no existen movimientos en el tablero, se define que es un empate (0)
         */
        if (existenMovimientos(tablero) == false) {
            return 0;
        }

        // Si es maximo
        if (esMaximo) {
            int mejor = -1000;
            // Recorre todos los espacios del tablero
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Comprueba que la celda este vacia
                    if (tablero[i][j] == '_') {
                        // Realiza el movimiento del jugador
                        tablero[i][j] = jugador;

                        /*
                         * Llama a la funcion minimax de forma recursiva y elije el mejor valor de todos
                         * (MAX) Ya que se esta buscando el MAXIMO, por eso se declara para el jugador
                         */
                        mejor = Math.max(mejor, minimax(tablero, profundidad + 1, !esMaximo));

                        // Deshace el movimiento
                        tablero[i][j] = '_';
                    }
                }
            }
            return mejor;
        } // Si es minimo
        else {
            int mejor = 1000;

            // Recorre todos los espacios del tablero
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Comprueba que la celda este vacia
                    if (tablero[i][j] == '_') {
                        // Realiza el movimiento del computador
                        tablero[i][j] = computador;

                        /*
                         * Llama a la funcion minimax de forma recursiva y elije el mejor valor de todos
                         * (MIN) Ya que se esta buscando el MINIMO, por eso se declara para el
                         * computador
                         */
                        mejor = Math.min(mejor, minimax(tablero, profundidad + 1, !esMaximo));

                        // Deshace el movimiento
                        tablero[i][j] = '_';
                    }
                }
            }
            return mejor;
        }
    }

    /*
     * Esta funcion devuelve el mejor posible movimiento para la persona/jugador
     */
    static Movimiento buscarMejorMovimiento(char tablero[][]) {
        int mejorVal = -1000;
        Movimiento mejorMovimiento = new Movimiento();
        mejorMovimiento.fila = -1;
        mejorMovimiento.columna = -1;
        /*
         * Recorre cada celda En cada celda vacia realiza la funcion minimax jugador
         * Devuelve la celda con el mejor valor posible (si dos o mas tienen el mismo
         * valor retorna la primera)
         */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Comprueba que esta vacia
                if (tablero[i][j] == '_') {
                    // Realiza el movimiento con el jugador (X)
                    tablero[i][j] = jugador;
                    // Calcula la funcion minimax para este movimiento
                    int MovimientoVal = minimax(tablero, 0, false);
                    // Deshace el movimiento
                    tablero[i][j] = '_';
                    MaxNa = maxNaEuristica(tablero);
                    /*
                     * Si el valor del MovimientoVal es mayor que el mejorVal actualizamos el valor
                     * y asignamos las filas y columnas segun el valor de la i,j que son la posicion
                     * del tablero
                     */
                    // System.out.println("MOV VAL Jugador i,j: " + MovimientoVal + " " + i + "," +
                    // j);
                    if (MovimientoVal > mejorVal) {
                        mejorMovimiento.fila = i;
                        mejorMovimiento.columna = j;
                        mejorVal = MovimientoVal;
                    }
                }
            }
        }
        return mejorMovimiento;
    }

    static Movimiento buscarMejorMovimientoPC(char tablero[][]) {
        int mejorVal = 1000;
        Movimiento mejorMovimiento = new Movimiento();
        mejorMovimiento.fila = -1;
        mejorMovimiento.columna = -1;

        /*
         * Recorre cada celda En cada celda vacia realiza la funcion minimax computador
         * Devuelve la celda con el mejor valor posible (si dos o mas tienen el mismo
         * valor retorna la primera)
         */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Comprueba que esta vacia
                if (tablero[i][j] == '_') {
                    // Realiza el movimiento con el computador (O)
                    tablero[i][j] = computador;
                    // Calcula la funcion minimaxPC para este movimiento
                    int MovimientoVal = minimax(tablero, 0, true);
                    MinNc = minNcEuristica(tablero);
                    // Deshace el movimiento
                    tablero[i][j] = '_';
                    /*
                     * Si el valor del MovimientoVal es menor que el mejorVal actualizamos el valor
                     * y asignamos las filas y columnas segun el valor de la i,j que son la posicion
                     * del tablero
                     */
                    // System.out.println("MOV VAL i,j: " + MovimientoVal + " " + i + "," + j);
                    if (MovimientoVal < mejorVal) {
                        mejorMovimiento.fila = i;
                        mejorMovimiento.columna = j;
                        mejorVal = MovimientoVal;
                    }
                }
            }
        }
        /*
         * System.out.printf("El valor para el mejor Movimiento para el PC es : %d\n\n",
         * mejorVal);
         */

        return mejorMovimiento;
    }

    public static void dibujarTablero(char[][] tablero) {
        for (int x = 0; x < tablero.length; x++) {
            System.out.print("| ");
            for (int y = 0; y < tablero[x].length; y++) {
                System.out.print(tablero[x][y]);
                if (y != tablero[x].length - 1) {
                    System.out.print("   ");
                }
            }
            System.out.println(" |");
        }
    }

    public static boolean puedeJugar(char[][] tablero, int i, int j, int player) {
        // juega el jugador
        if (existenMovimientos(tablero)) {
            if (player == 1) {
                if (tablero[i][j] == '_') {
                    tablero[i][j] = jugador;
                    return true;
                } else {
                    return false;
                }
            } // juega el PC
            else {
                if (tablero[i][j] == '_') {
                    tablero[i][j] = computador;
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }

    }

    public static int comprobarGanador(char[][] tablero) {
        // Revisa en las filas si gano X u O
        for (int fila = 0; fila < 3; fila++) {
            if (tablero[fila][0] == tablero[fila][1] && tablero[fila][1] == tablero[fila][2]) {
                if (tablero[fila][0] == jugador) {
                    return +10;
                } else if (tablero[fila][0] == computador) {
                    return -10;
                }
            }
        }

        // Revisa en las columnas si gano X u O
        for (int columna = 0; columna < 3; columna++) {
            if (tablero[0][columna] == tablero[1][columna] && tablero[1][columna] == tablero[2][columna]) {
                if (tablero[0][columna] == jugador) {
                    return +10;
                } else if (tablero[0][columna] == computador) {
                    return -10;
                }
            }
        }

        // Revisa en las diagonales si gano X u O
        if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            if (tablero[0][0] == jugador) {
                return +10;
            } else if (tablero[0][0] == computador) {
                return -10;
            }
        }

        if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            if (tablero[0][2] == jugador) {
                return +10;
            } else if (tablero[0][2] == computador) {
                return -10;
            }
        }

        // Si nadie gana retorna 0
        return 0;
    }

    public static boolean juego(char[][] tablero) {
        int filaJuego;
        int columnaJuego;
        boolean paso = false;
        // Captura el valor de la fila y columna del jugador, solo permite entre 0-2 si
        // no, vuelve a solicitar
        System.out.println("Seleccione su fila (0-2)");
        filaJuego = scan.nextInt();
        while (filaJuego < 0 || filaJuego > 2) {
            System.out.println("Seleccione su fila (0-2)");
            filaJuego = scan.nextInt();
        }
        System.out.println("Seleccione su columna (0-2)");
        columnaJuego = scan.nextInt();
        while (columnaJuego < 0 || columnaJuego > 2) {
            System.out.println("Seleccione su columna (0-2)");
            columnaJuego = scan.nextInt();
        }
        // mientras existan movimiento en el tablero va a ejecutarse el "juego"
        while (existenMovimientos(tablero)) {
            // si no hay ganadores en el tablero va a ejeutarse el juego en caso contrario
            // lo termina y da el ganador
            if (comprobarGanador(tablero) == 10 || comprobarGanador(tablero) == -10) {
                if (comprobarGanador(tablero) == 10) {
                    MaxNa = (int) Double.POSITIVE_INFINITY;
                    System.out.println(
                            "Valor U(t): " + euristica(MaxNa, MinNc) + " Na(t), Nc(t): " + MaxNa + "," + MinNc);
                    System.out.println("Gano el jugador");
                    return false;
                } else if (comprobarGanador(tablero) == -10) {
                    MaxNa = maxNaEuristica(tablero);
                    MinNc = (int) Double.NEGATIVE_INFINITY;
                    System.out.println(
                            "Valor U(t): " + euristica(MaxNa, MinNc) + " Na(t), Nc(t): " + MaxNa + "," + MinNc);
                    System.out.println("Gano la PC");
                    return false;
                }
            } else {
                // comprueba si puede jugar el jugador en esa posicion.
                // si puede hace jugar al computador
                paso = puedeJugar(tablero, filaJuego, columnaJuego, 1);
                if (paso) {
                    Movimiento mejorMovimientoPC = buscarMejorMovimientoPC(tablero);
                    if (mejorMovimientoPC.fila != -1 && mejorMovimientoPC.columna != -1) {
                        System.out.printf("El mejor movimiento para el PC es :\n");
                        System.out.printf("fila: %d columna: %d\n\n", mejorMovimientoPC.fila,
                                mejorMovimientoPC.columna);
                        puedeJugar(tablero, mejorMovimientoPC.fila, mejorMovimientoPC.columna, 0);
                    }
                    MaxNa = maxNaEuristica(tablero);
                    System.out.println(
                            "Valor U(t): " + euristica(MaxNa, MinNc) + " Na(t), Nc(t): " + MaxNa + "," + MinNc);
                    dibujarTablero(tablero);
                } // si no, pide nuevamente fila y columna hasta que pueda hacer un movimiento
                  // valido
                else {
                    while (!paso) {
                        System.out.println("Seleccione su fila (0-2)");
                        filaJuego = scan.nextInt();
                        while (filaJuego < 0 || filaJuego > 2) {
                            System.out.println("Seleccione su fila (0-2)");
                            filaJuego = scan.nextInt();
                        }
                        System.out.println("Seleccione su columna (0-2)");
                        columnaJuego = scan.nextInt();
                        while (columnaJuego < 0 || columnaJuego > 2) {
                            System.out.println("Seleccione su columna (0-2)");
                            columnaJuego = scan.nextInt();
                        }
                        paso = puedeJugar(tablero, filaJuego, columnaJuego, 1);
                    }
                    // si el movimiento del pc esta dentro de los parametros del juego realiza el
                    // movimiento
                    // esto es porque la funcion minimax del PC
                    Movimiento mejorMovimientoPC = buscarMejorMovimientoPC(tablero);
                    if (mejorMovimientoPC.fila != -1 && mejorMovimientoPC.columna != -1) {
                        System.out.printf("El mejor movimiento para el PC es :\n");
                        System.out.printf("fila: %d columna: %d\n\n", mejorMovimientoPC.fila,
                                mejorMovimientoPC.columna);
                        puedeJugar(tablero, mejorMovimientoPC.fila, mejorMovimientoPC.columna, 0);
                    }
                    MaxNa = maxNaEuristica(tablero);
                    System.out.println(
                            "Valor U(t): " + euristica(MaxNa, MinNc) + " Na(t), Nc(t): " + MaxNa + "," + MinNc);
                    dibujarTablero(tablero);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char tablero[][] = { { '_', '_', '_' }, { '_', '_', '_' }, { '_', '_', '_' } };
        System.out.println("Bienvenido, ahora va a jugar como las X");
        System.out.println(
                "Su tablero es [fila,columna]:\n | [0,0] [0,1] [0,2] |\n | [1,0] [1,1] [1,2] |\n | [2,0] [2,1] [2,2] |");
        if (!juego(tablero)) {
            System.out.println("Se acabo el juego");
        }
        /*
         * Si queremos evaluar un tablero como ejemplo podemos usar estas funciones char
         * tablero[][] = { { '_', '_', '_' }, { '_', '_', '_' }, { '_', '_', '_' } };
         * Movimiento mejorMovimiento = buscarMejorMovimiento(tablero);
         * 
         * System.out.printf("El mejor movimiento para el Jugador es :\n");
         * System.out.printf("fila: %d columna: %d\n\n",
         * mejorMovimiento.fila,mejorMovimiento.columna); Movimiento mejorMovimientoPC =
         * buscarMejorMovimientoPC(tablero);
         * 
         * System.out.printf("El mejor movimiento para el PC es :\n");
         * System.out.printf("fila: %d columna: %d\n\n",
         * mejorMovimientoPC.fila,mejorMovimientoPC.columna);
         */
    }

}
