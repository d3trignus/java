package org.example.factory;

import org.example.entity.Vegetable;
import org.example.entity.LeafVegetable;
import org.example.entity.RootVegetable;
import org.example.validator.VegetableValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class VegetableFactory {
    private static final Logger logger = LogManager.getLogger(VegetableFactory.class);

    private final VegetableValidator validator = new VegetableValidator();

    public Vegetable createVegetable(String vegetableType, String name, double weight, double calories, double param) {
        if (vegetableType == null) {
            logger.error("Тип овоща null");
            throw new IllegalArgumentException("Тип овоща не может быть null");
        }

        // Валидация числовых полей
        if (!validator.isValidWeight(weight)) {
            throw new IllegalArgumentException("Некорректный вес: " + weight);
        }
        if (!validator.isValidCalories(calories)) {
            throw new IllegalArgumentException("Некорректная калорийность: " + calories);
        }
        if (!validator.isValidParam(param)) {
            throw new IllegalArgumentException("Некорректный доп. параметр: " + param);
        }

        String type = vegetableType.strip().toLowerCase(Locale.ROOT);
        name = name.strip();

        switch (type) {
            case "leaf":
                logger.info("Создание листового овоща");
                return new LeafVegetable(name, weight, calories, param);
            case "root":
                logger.info("Создание корнеплода");
                return new RootVegetable(name, weight, calories, param);
            default:
                logger.error("Неизвестный тип овоща ({})", type);
                throw new IllegalArgumentException("Неизвестный тип овоща " + vegetableType);
        }
    }

    public Vegetable createVegetable(String[] vegetable) {
        if (vegetable.length != 5) {
            logger.error("Неверная длина массива");
            throw new IllegalArgumentException("Длина массива должна быть 5");
        }

        String type = vegetable[0].strip().toLowerCase(Locale.ROOT);
        String name = vegetable[1].strip();
        double weight = Double.parseDouble(vegetable[2].strip());
        double calories = Double.parseDouble(vegetable[3].strip());
        double param = Double.parseDouble(vegetable[4].strip());

        return createVegetable(type, name, weight, calories, param);
    }
}