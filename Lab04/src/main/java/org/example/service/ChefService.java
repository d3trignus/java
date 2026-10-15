package org.example.service;

import org.example.entity.Vegetable;
import org.example.entity.RootVegetable;
import org.example.entity.LeafVegetable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;

public class ChefService {
    private static final Logger logger = LogManager.getLogger(ChefService.class);

    public double calculateTotalCalories(Vegetable[] vegetables) {
        if (vegetables.length == 0) return 0;
        logger.info("Рассчет каллорийности салата");
        double totalCalories = 0;
        for (Vegetable vegetable : vegetables) {
            totalCalories += vegetable.calculateTotalCalories();
        }
        return totalCalories;
    }

    public void sortByCalories(Vegetable[] vegetables) {
        logger.info("Сортировка овощей по калорийности");
        Arrays.sort(vegetables, Comparator.comparingDouble(Vegetable::getCalories));

    }

    public Vegetable[] findByCaloriesRange(Vegetable[] vegetables, double minCalories, double maxCalories) {
        if (vegetables.length == 0) return vegetables;

        return Arrays.stream(vegetables)
                .filter(v -> v.getCalories() >= minCalories && v.getCalories() <= maxCalories)
                .toArray(Vegetable[]::new);
    }


}