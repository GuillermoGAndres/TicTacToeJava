package tictactoe;
public class Test {
    public static void main(String[] args) {

	int[][] arr = new int[3][3];
	
	int N = 3;
	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		arr[i][j] = 250;
	    }
	}
	printArr(arr);
	arr = foo(arr);
	printArr(arr);
	
    }

    public static void printArr(int[][] arr) {
	int N = 3;
	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		System.out.print(arr[i][j] + " ");
	    }
	    System.out.println();
	}
    }

    public static int[][] foo(int[][] arr) {
	int N = 3;
	 for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		arr[i][j] = 77;
	    }
	}
	return arr;
    }
}
