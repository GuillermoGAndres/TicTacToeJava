package tictactoe;
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class App {
    private static Scanner parse = new Scanner(System.in);

    public static void main(String[] args) {
	// The first one of you moves as X, and then the other one moves as O.
	App tictactoe = new App();
	
	//Create q empty field at the beginning of the game
	String emptyField = "_________";
	char[] game = emptyField.toCharArray();
	char[][] board  = tictactoe.makeBoard(game);
	draw(board);
	
	// Creates a game loop where the program asks the user to enter the cell coordinates, analyzes the move for correctness, and shows a field with the changes if everything is ok.
	String stage;
	while(true) {
	    board = moveUser(board, 'X');
	    draw(board);
	    stage = tictactoe.findStage(board);
	    if(stage.equals("X wins") || stage.equals("Draw"))
		break;
	    board = moveUser(board, 'O');
	    draw(board);
	    stage = tictactoe.findStage(board);
	    if(stage.equals("O wins") || stage.equals("Draw"))
		break;
	}
	
	// Ends the game when someone wins or when there is a draw
	System.out.println(stage);
      
    }
    

    /*
     * Jugadas del user y verifica que si la casilla esta permitida.
     * @return board - Devuelve un nuevo table con la jugada del usuario.
     */
    public static char[][] moveUser(char[][] board, char symbol) {
	int x = -1, y = -1; 
	boolean flag = true;
	// Jugada del usuario
	do {
	    //Validar datos (exeptions)
	    int[] coordinates = readCoordinates(x,y); // Read and valite coordinates
	    x = coordinates[0]; // Representa el valor de x
	    y = coordinates[1]; // Representa el valor de y
	    // Tranform coordinate and modicar board by every movement.
	    int newx = 3 - y, newy= x - 1;
	    if (board[newx][newy] == ' ') {
		board[newx][newy] = symbol;
		flag = false;
	    }else
		System.out.println("This cell is occupied! Choose another one!");
	} while(flag);
	return board;
	
    }
    /* 
     *Lees los datos y los valida para que no sea una cadena y que esten en el rango
     * Pasare valores por referencia porque quiero que se guarden esos valores
     */
    public static int[] readCoordinates(Integer x, Integer y){
	int[] coordinates = new int[2];
	boolean flag = true;
	do {
	    
	    System.out.print("Enter the coordinate: ");
	    try{
		x = parse.nextInt();
		y = parse.nextInt();
		flag = false;
	    }catch(InputMismatchException e) {
		System.out.println("You should enter numbers!");
		// Limpiar las cadenas  que se quedo guardada en la entrada.
		parse.nextLine();
		flag = true;
	    }
	    if(!flag) { // Significa que ya paso las pruebes de ser nuermso enteros
		if((x >= 1 && x <=3) && (y >= 1 && y <= 3))
		    flag = false;
		else{
		    flag = true;
		    System.out.println("Coordinates should be from 1 to 3!");
		}
	    }
		 
	}while(flag);
	//Sale hasta que se haya ingresado todo correctamente.
	coordinates[0] = x;
	coordinates[1] = y;
	return coordinates;
    }
    
    /**
     *Dubuja el board de los jugadores
     */
    public static void draw(char[][] board) {
	System.out.println("---------");
	for (int i=0; i < 3; i++) {
	     System.out.print("| ");
	    for( int j=0; j < 3; j++) {
		  System.out.print(board[i][j] + " ");
	    }
	     System.out.print("|");
            System.out.println();
	}
	System.out.println("---------");
    }

    /**
     * Verifica un rango [1,3]
     */
    public static boolean isRange(int num){
        if(num >=1 || num <= 3) {
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String num){
        
        try{
            Integer.parseInt(num);
            return true;
        }catch(NumberFormatException e) {
            return false;
        }
    }


    /**
     *  Crea el board  de los jugadores: XXXOO__O_
     *     X O X 
     *     O X O
     *     X O X
     */
    public char[][] makeBoard(char[] symbols) {
        char[][] table = new char[3][3];
        int count = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
		if(symbols[count] == '_') {
		    symbols[count] = ' ';
		}
		table[i][j] = symbols[count];
                count++;
            }
        }
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
     *
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

