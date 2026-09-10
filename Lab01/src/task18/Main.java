// 18. В заданной последовательности натуральных чисел для всех трех-
//        , четырех- и
//        пятизначных чисел определить, образуют ли цифры натурального числа убывающую
//        последовательность. Вывести соответствующее сообщение.

package task18;

import java.util.Scanner;
import util.NumberAnalyzer;

public class Main {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = in.nextInt();

        int[] numbers = new int[n];

        for (int i = 0; i < n; ++i) {
            System.out.printf("numbers[%d]: ", i);
            numbers[i] = in.nextInt();
        }

        boolean allSatisfy = true;
        int checkedCount = 0;
        for (int number : numbers) {
            int numberOfDigits = NumberAnalyzer.numberOfDigits(number);
            if (numberOfDigits >= 3 && numberOfDigits <= 5) {
                ++checkedCount;
                boolean result = NumberAnalyzer.isDescendingSequence(number);
                if (result) System.out.printf("%d satisfies the condition(digits are descending sequence) %n", number);
                else {
                    System.out.printf("%d doesnt satisfy the condition(digits are descending sequence) %n", number);
                    allSatisfy = false;
                }
            }
        }
        if (checkedCount > 0) {
            if (allSatisfy) System.out.println("All 3-4-5-digit numbers satisfy the condition");
            else System.out.println("%nNot all 3-4-5-digit numbers satisfy the condition");
        }
        else System.out.println("No 3-4-5 digit numbers were entered");

        in.close();
    }
}
