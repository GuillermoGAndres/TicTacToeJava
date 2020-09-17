package tictactoe;
import java.util.Scanner;
import java.util.Arrays;

public class App {
    private static Scanner parse = new Scanner(System.in);

    public static void main(String[] args) {
        App tictactoe = new App();
        char[] game = tictactoe.readInputUser();
        char[][] table  = tictactoe.draw(game);
        String state = tictactoe.findStage(table);
        System.out.println(state);

    }

    /**
     * Dibuja el table de los jugadores: XXXOO__O_
     *     X O X 
     *     O X O
     *     X O X
     */
    public char[][] draw(char[] symbols) {
        char[][] table = new char[3][3];
        System.out.println("---------");
        int count = 0;
        for(int i = 0; i < 3; i++) {
            System.out.print("| ");
            for(int j = 0; j < 3; j++) {
                System.out.print(symbols[count] + " ");
                table[i][j] = symbols[count];
                count++;
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
        return table;
    }


    /**
     * Va leer la entrada y la va descomponer 
     * en un arreglo de caracteres, alternative:
     * String.toCharArray() -> char[] Genera un arreglo de caracteres.
     */
    public char[] readInputUser() {
        System.out.print("Enter cells: ");
        String word = parse.next();
        char[] patron = new char[word.length()];
        for(int i = 0; i < word.length(); i++) {
            patron[i] = word.charAt(i);
        }
        return patron;
    }
    

    /**
     * Encontra los escenarios de las posibles jugadas
     * @return - Regresa el posible estado.
     */
    public String findStage(char[][] table) {
        //Si nadie a ganado entonces revisamo que si todavia hay jugadas vacias, sino significa que
        //ya esta lleno el tablero(draw)
        // Encontramos el excenario " X wins"
        
        if (!isPosible(table)) {
            return "Impossible";
        } 
        if ( findByRow(table, 'O') || findByCol(table, 'O') || findByDiag(table,'O') ) {

             if ( findByRow(table, 'X') || findByCol(table, 'X') || findByDiag(table,'X') ) {
                 return "Impossible";
             }
             return "X wins";
         }
         // Escenario " O win"
        if( findByRow(table, 'X') || findByCol(table, 'X') || findByDiag(table,'X') ) {
             return "O wins";
         }


        if (isIncomplete(table)) {
             return "Game not finished";
         } 
        return "Draw"; 
        // return "";
    }
    
    /**
     * Verifica si escenacrio es valido, 
     * La diferencia de todas las x y O debe ser siempre uno
     * independiente quien inicie primero. 5-4 = 1;
     */
    private boolean isPosible(char[][] table) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if( table[i][j] == 'X' ) {
                    countX++;
                } else if (table[i][j] == 'O') {
                    countO++;
                }
            }
        }
        return Math.abs(countX - countO) < 2;

    }

    /**
     * Revisa si hay jugadas vacias en el tablero
     * Si devuelve falso, significa que esta lleno 
     */
    private boolean isIncomplete(char[][] table) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == ' ' || table[i][j] == '_') {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean isValidCaracter(char symbol, char enemy) {
        if (symbol == enemy || symbol == '_' || symbol ==' ') {
            return false;
        }
        return true;
    }

    /**
     * Encuentra el patro del jugador en horizontables dependien el enemy que sea
     * @param enemy - Son los valores 'X' o 'O', si se elige enemy = 'O', buscara las xxx 
     */
    private boolean findByRow(char[][] table, char enemy) {
        for (char[] row : table) {
            int marks = 0;
            for (char symbol : row) {
                marks++;
                if (!isValidCaracter(symbol, enemy)) {
                    marks = 0;
                }
                if(marks == 3) {
                    return true;
                }
            }
        } 
        return false;
    }

    private boolean findByCol(char[][] table, char enemy) {

        for (int j = 0; j < 3; j++) {
            int marks = 0;
            for (int i = 0; i < table.length; i++) {
                marks++;
                if (!isValidCaracter(table[i][j], enemy)) {
                    marks = 0;
                }
                if (marks == 3) {
                    return true;
                }
                
            }
        }
        return false;
    }

    private  boolean findByDiag(char[][] table, char enemy) {
        // Para la diagonal izquierda
        int marks = 0;
        for(int i=0, j = i; i < 3; i++, j++) { // 3 por es un juego de 3x3
            marks++;
            if (!isValidCaracter(table[i][j], enemy)) {
                marks = 0;
            }
            if (marks == 3) {
                return true;
            }
            
        }
        // Para la diagonal derecha
        marks = 0;
        for(int i=0, j = 2; i < 3; i++, j--) {
            marks++;
            if (!isValidCaracter(table[i][j], enemy)) {
                marks = 0;
            }
            if (marks == 3) {
                return true;
            }

        }
        return false;
    }

}

