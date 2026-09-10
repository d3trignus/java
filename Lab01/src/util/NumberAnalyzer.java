package util;

public final class NumberAnalyzer {
    private NumberAnalyzer() {}

    public static int[] findNumbersWithDigitsAndSum(int[] numbers, int p, int k) {
        int count = 0;
        for (int number : numbers) {
            int numberOfDigits = numberOfDigits(number);
            int sumOfDigits = sumOfDigits(number);

            if (numberOfDigits == p && sumOfDigits == k) {
                ++count;
            }
        }

        int[] result = new int[count];
        int index = 0;
        for (int number : numbers) {
            int numberOfDigits = numberOfDigits(number);
            int sumOfDigits = sumOfDigits(number);

            if (numberOfDigits == p && sumOfDigits == k) {
                result[index++] = number;
            }
        }

        return result;
    }

    public static int numberOfDigits(int number) {
        if (number == 0) return 1;
        int counter = 0;
        while (number > 0) {
            ++counter;
            number /= 10;
        }
        return counter;
    }

    public static int sumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public static boolean isDescendingSequence(int number) {
        while (number >= 10) {
         int lastDigit = number % 10;
         number /= 10;
         int prevDigit = number % 10;
         if (prevDigit < lastDigit) return false;
        }
        return true;
    }

    public static boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i * i <= number; ++i) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static int[] convertToArray(int number) {
        int numberOfDigits = numberOfDigits(number);
        int[] arrayedNumber = new int[numberOfDigits];

        for (int i = 0; i < numberOfDigits; i++, number /= 10) {
            arrayedNumber[numberOfDigits - 1 - i] = number % 10;
        }

        return arrayedNumber;
    }

    public static int convertToInt(int[] arrayedNumber) {
        int number = 0;
        for (int digit : arrayedNumber) {
            number = number * 10 + digit;
        }

        return number;
    }

    public static int findMinDigitIndex(int number) {
        int[] arrayedNumber = convertToArray(number);
        int minDigitIndex = 0;
        int length = arrayedNumber.length;

        for (int i = 1; i < length; ++i) {
            if (arrayedNumber[i] < arrayedNumber[minDigitIndex]) {
                minDigitIndex = i;
            }
        }

        return minDigitIndex;
    }

    public static int findMaxDigitIndex(int number) {
        int[] arrayedNumber = convertToArray(number);
        int maxDigitIndex = 0;
        int length = arrayedNumber.length;

        for (int i = 1; i < length; ++i) {
            if (arrayedNumber[i] > arrayedNumber[maxDigitIndex]) {
                maxDigitIndex = i;
            }
        }

        return maxDigitIndex;
    }

    public static int swapMinMaxDigits(int number) {
        int[] arrayedNumber = convertToArray(number);

        int minIndex = findMinDigitIndex(number);
        int maxIndex = findMaxDigitIndex(number);

        int temp = arrayedNumber[minIndex];
        arrayedNumber[minIndex] = arrayedNumber[maxIndex];
        arrayedNumber[maxIndex] = temp;

        return convertToInt(arrayedNumber);
    }


}