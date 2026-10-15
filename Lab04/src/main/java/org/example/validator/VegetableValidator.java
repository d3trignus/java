package org.example.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Vegetable;

public class VegetableValidator {
    private static final Logger logger = LogManager.getLogger(VegetableValidator.class);
    private static final String delimiter = ";";

    public boolean isValidLine(String line) {
        if (line == null || line.strip().isBlank()) {
            logger.warn("Пустая строка");
            return false;
        }

        String[] vegetable = line.split(delimiter);

        if (vegetable.length != 5) {
            logger.warn("Некорректное количество аргументов в строке {}. Ожидается 5, получено {}", line, vegetable.length);
            return false;
        }

        String type = vegetable[0].strip().toLowerCase();
        if (!type.equals("leaf") && !type.equals("root")) {
            logger.warn("Неизвестный тип овоща");
        }

        try {
            double weight = Double.parseDouble(vegetable[2].strip());
            double calories = Double.parseDouble(vegetable[3].strip());
            double param = Double.parseDouble(vegetable[4].strip());

            return isValidWeight(weight) && isValidCalories(calories) && isValidParam(param);
        } catch (NumberFormatException e) {
            logger.warn("Ошибка формата числа (NumberFormatException) в строке: '{}'", line);
            return false;
        }
    }

    public boolean isValidWeight(double weight) {
        if (weight <= 0) {
            logger.warn("Некорректный вес: {}. Вес должен быть больше 0", weight);
            return false;
        }
        return true;
    }

    public boolean isValidCalories(double calories) {
        if (calories < 0) {
            logger.warn("Некорректная калорийность: {}. Калории должны быть больше 0", calories);
            return false;
        }
        return true;
    }

    public boolean isValidParam(double param) {
        if (param < 0) {
            logger.warn("Некорректный доп параметр: {}. Должен быть больше 0", param);
            return false;
        }
        return true;
    }
}