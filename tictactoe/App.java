package tictactoe;
import java.util.Scanner;
import java.util.Arrays;

public class App {
    private Scanner parse = new Scanner(System.in);

    public static void Main(String[] args) {
        String word = "Hola mundo";
        for(char letter : word) {
            System.out.println(letter);
        }
        //draw();
    }

    private void draw() {
        System.out.println("X O X");
        System.out.println("O X O");
        System.out.println("X O X");
    }

    public char[] readInputUser() {
        int n = 9; // Nine symbols
        char[] symbols = new char[n];
        for(int i=0; i < n; i++) {
        }
        return symbols;
    }
}
