package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;
import java.util.function.Predicate;

public class Lab07 {
    private static final Logger logger = LogManager.getLogger(Lab07.class);

    public interface Check {
        boolean test(int n);
    }

    public interface Summator {
        double sum(Object... args);
    }

    public interface TimeConverter {
        String convert(String time);
    }

    public static void main(String[] args) {

//        4. Написать функциональный интерфейс с методом, который принимает число и
//        возвращает булево значение. Написать реализацию такого интерфейса в
//        виде лямбда-выражения, которое возвращает true, если переданное
//        число делится без остатка на 13.

        Check c1 = n -> n % 13 == 0;
        Predicate<Integer> c2 = n -> n % 13 == 0;
        logger.info(c1.test(26));
        logger.info(c2.test(27));


//        16. Написать функцию, которая вычисляет сумму списка аргументов произвольной длины с
//        разными типами элементов массива.

        Summator s1 = a -> {
            double sum = 0;
            for (Object o : a) {
                if (o instanceof Number) {
                    sum += ((Number) o).doubleValue();
                }
            }
            return sum;
        };

        Function<Object[], Double> s2 = a -> {
            double sum = 0;
            for (Object o : a) {
                if (o instanceof Number) {
                    sum += ((Number) o).doubleValue();
                }
            }
            return sum;
        };
        logger.info(s1.sum(1, 2.5, 3L, "abc"));
        logger.info(s2.apply(new Object[]{1, 2.5f, 3L}));

// 28. Дано время в 12-часовом формате в виде строки.
// Конвертировать время в 24-часовой формат.

        TimeConverter t1 = s -> convert(s);
        Function<String, String> t2 = s -> convert(s);
        logger.info(t1.convert("07:05:45PM"));
        logger.info(t2.apply("12:00:00AM"));
    }

    public static String convert(String s) {
        int hour = Integer.parseInt(s.substring(0, 2));
        String rest = s.substring(2, 8);
        boolean pm = s.endsWith("PM");

        if (hour == 12) {
            hour = 0;
        }
        if (pm) {
            hour += 12;
        }
        return String.format("%02d", hour) + rest;
    }
}