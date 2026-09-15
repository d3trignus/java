package task4;

import java.util.Scanner;
import util.MatricesHandler;

public class Task4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = in.nextInt();

        int[][] matrix = new int[n][n];
        MatricesHandler.fillMatrix(matrix, n, in);

        System.out.println("Your matrix: ");
        MatricesHandler.printMatrix(matrix, n);

        MatricesHandler.swapColumns(matrix, n);

        System.out.println("Edited matrix: ");
        MatricesHandler.printMatrix(matrix, n);

        in.close();
    }
}