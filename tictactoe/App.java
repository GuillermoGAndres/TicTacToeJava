package tictacoe;
import java.util.Scanner;
import java.util.Arrays;

public class App {
    private static Scanner parse = new Scanner(System.in);

    public static void main(String[] args) {
        App tictactoe = new App();
        char[] symbols = tictactoe.readInputUser();
        tictactoe.draw(symbols);

    }
    

    /**
     * Dibuja el table de los jugadores
     *     X O X 
     *     O X O
     *     X O X
     */
    public void draw(char[] symbols) {
        System.out.println("---------");
        int count = 0;
        for(int i = 0; i < 3; i++) {
            System.out.print("| ");
            for(int j = 0; j < 3; j++) {
                System.out.print(symbols[count] + " ");
                count++;
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }


    /**
     * Va leer la entrada y la va descomponer 
     * en un arreglo de caracteres
     */
    public char[] readInputUser() {
        String word = parse.next();
        char[] patron = new char[word.length()];
        for(int i = 0; i < word.length(); i++) {
            patron[i] = word.charAt(i);
        }
        return patron;
    }
}
