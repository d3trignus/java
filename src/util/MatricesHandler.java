package util;

import java.util.Scanner;
import java.util.stream.*;

public final class MatricesHandler {
    private MatricesHandler() {}

    public static void fillMatrix(int[][] matrix, int n, Scanner in) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.printf("A[%d][%d]: ", i, j);
                matrix[i][j] = in.nextInt();
            }
        }
    }

    public static void fillMatrixStream(int[][] matrix, int n, Scanner in) {
        IntStream.range(0, n).forEach(i -> {
            IntStream.range(0, n).forEach(j -> {
                System.out.printf("A[%d][%d]: ", i, j);
                matrix[i][j] = in.nextInt();
            });
        });
    }

    public static void fillMatrixWithRandoms(double[][] matrix, int n) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = Math.random() * 200 - 100;
            }
        }
    }

    public static void fillMatrixWithRandomsStream(double[][] matrix, int n) {
        IntStream.range(0, n).forEach(i -> {
            IntStream.range(0, n).forEach(j -> matrix[i][j] = Math.random() * 200 - 100);
        });
    }

    public static void printMatrix(int[][] matrix, int n) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void printMatrixStream(int[][] matrix, int n) {
        IntStream.range(0, n).forEach(i -> {
            IntStream.range(0, n).forEach(j -> {
                System.out.printf("%d ", matrix[i][j]);
            });
            System.out.println();
        });
    }

    public static void printMatrix(double[][] matrix, int n) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == (int)matrix[i][j]) System.out.printf("%.0f ", matrix[i][j]);
                else System.out.printf("%f ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void printMatrixStream(double[][] matrix, int n) {
        IntStream.range(0, n).forEach(i -> {
            IntStream.range(0, n).forEach(j -> {
                if (matrix[i][j] == (int)matrix[i][j]) System.out.printf("%.0f ", matrix[i][j]);
                else System.out.printf("%f", matrix[i][j]);
            });
        });
    }

    public static void swapColumns(int[][] matrix, int n) {
        for (int i = 0; i < (n / 2); ++i) {
            for (int j = 0; j < n;++j) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    public static void swapColumnsStream(int[][] matrix, int n) {
        IntStream.range(0, n).forEach(i -> {
            matrix[i] = IntStream.range(0, n).map(j -> matrix[i][n - 1 - j]).toArray();
        });
    }

    public static void roundMatrix(double[][] matrix, int n) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i % 2 == 0) matrix[i][j] = Math.floor(matrix[i][j]);
                else matrix[i][j] = Math.ceil(matrix[i][j]);
            }
        }
    }

    public static void roundMatrixStream(double[][] matrix, int n) {
        IntStream.range(0, n).forEach(i -> {
            matrix[i] = IntStream.range(0, n)
                    .mapToDouble(j -> (i % 2 == 0) ? Math.floor(matrix[i][j]) : Math.ceil(matrix[i][j]))
                    .toArray();
        });
    }
}
