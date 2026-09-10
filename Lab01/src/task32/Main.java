// 32. Для каждого числа простого числа из заданной последовательности натуральных
//        чисел найти максимальную и минимальную цифры в нём и поменять их местами.

package task32;

import java.util.Scanner;
import util.NumberAnalyzer;

public class Main {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = in.nextInt();

        int[] numbers = new int[n];
        for (int i = 0; i < n; ++i) {
            System.out.printf("numbers[%d]: ", i);
            numbers[i] = in.nextInt();
        }

        for (int i = 0; i < n; ++i) {
            if (NumberAnalyzer.isPrime(numbers[i])) {
                int editedNumber = NumberAnalyzer.swapMinMaxDigits(numbers[i]);
                System.out.printf("%d was edited to %d %n", numbers[i], editedNumber);
                numbers[i] = editedNumber;
            }
        }

        for (int number : numbers) {
            System.out.printf("%d ", number);
        }

        in.close();
    }
}
