// 4. Найти все p-значные числа из заданной последовательности натуральных чисел,
//        сумма цифр которых равна заданному числу k, и подсчитать их количество.

package task4;

import java.util.Scanner;
import util.NumberAnalyzer;

public class Main {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter p: ");
        int p = in.nextInt();

        System.out.print("Enter k: ");
        int k = in.nextInt();

        System.out.print("Enter number of elements: ");
        int n = in.nextInt();

        int[] numbers = new int[n];

        for (int i = 0; i < n; ++i) {
            System.out.printf("numbers[%d]: ", i);
            numbers[i] = in.nextInt();
        }

        int[] result = NumberAnalyzer.findNumbersWithDigitsAndSum(numbers, p, k);
        System.out.printf("There are %d numbers with %d digits and their sum = %d: %n", result.length, p, k);

        for (int number : result) {
            System.out.printf("%d ", number);
        }

        in.close();
    }
}