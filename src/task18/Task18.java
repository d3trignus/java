//18.
//        Округлить все элементы матрицы до целого числа, в строках с
//        четным номером - с недостатком, в строках с нечетным номером - с избытком).

package task18;

import java.util.Scanner;
import util.MatricesHandler;

public class Task18 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = in.nextInt();

        double[][] matrix = new double[n][n];
        MatricesHandler.fillMatrixWithRandoms(matrix, n);
//        MatricesHandler.fillMatrixWithRandomsStream(matrix, n);

        System.out.println("Your matrix: ");
        MatricesHandler.printMatrix(matrix, n);
//        MatricesHandler.printMatrixStream(matrix, n);

        MatricesHandler.roundMatrix(matrix, n);
//        MatricesHandler.roundMatrixStream(matrix, n);

        System.out.println("New matrix: ");
        MatricesHandler.printMatrix(matrix, n);
//        MatricesHandler.printMatrixStream(matrix, n);

        in.close();
    }
}
