//4. Определить класс Комплекс, действительная и мнимая часть
// которой представлены в виде Рациональной Дроби. Создать
// массив/список/множество размерности n из комплексных координат.
// Передать его в метод, который выполнит сложение/умножение его элементов.
package org.example;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Complex[] numbers = new Complex[n];

        for (int i = 0; i < n; ++i) {
            System.out.printf("[%d]. Enter numerator of real part: ", i);
            int realPartNum = in.nextInt();
            System.out.printf("[%d]. Enter denominator of real part: ", i);
            int realPartDenom = in.nextInt();

            System.out.printf("[%d]. Enter numerator of imaginary part: ", i);
            int imPartNum = in.nextInt();
            System.out.printf("[%d]. Enter denominator of imaginary part: ", i);
            int imPartDenom = in.nextInt();

            Rational realPart = new Rational(realPartNum, realPartDenom);
            Rational imPart = new Rational(imPartNum, imPartDenom);


            numbers[i] = new Complex(realPart, imPart);
        }

        Complex result = new Complex(0, 0);
        result.addAll(numbers);

        result.printComplexNumber();

    }
}