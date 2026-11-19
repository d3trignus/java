package org.example.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Greenhouse {
    private static final Logger logger = LogManager.getLogger(Greenhouse.class);

    private final List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant plant) {
        if (plant == null) {
            logger.warn("Попытка добавить null-растение");
            throw new IllegalArgumentException("Растение не может быть null");
        }
        plants.add(plant);
        logger.info("В оранжерею добавлено растение: {}", plant.getName());
    }

    public Plant purchase(Plant plant) {
        logger.info("Закупка нового растения: {}", plant.getName());
        addPlant(plant);
        return plant;
    }

    public int removeBySpecies(String species) {
        List<Plant> toRemove = plants.stream()
                .filter(p -> p.getSpecies().equalsIgnoreCase(species))
                .toList();
        plants.removeAll(toRemove);
        logger.info("Из оранжереи удалено {} растений вида '{}'", toRemove.size(), species);
        return toRemove.size();
    }

    public void waterAll() {
        logger.info("Полив всех растений оранжереи ({} шт.)", plants.size());
        plants.forEach(Plant::water);
    }

    public void setTemperatureAll(double t) {
        logger.info("Установка температуры {}°C для всех растений", t);
        plants.forEach(p -> p.setTemperature(t));
    }

    public void setLightingAll(int level) {
        logger.info("Установка освещения {}% для всех растений", level);
        plants.forEach(p -> p.setLightLevel(level));
    }

    public List<Plant> getAllPlants() {
        return new ArrayList<>(plants);
    }

    public List<Plant> findBySpecies(String species) {
        return plants.stream()
                .filter(p -> p.getSpecies().equalsIgnoreCase(species))
                .collect(Collectors.toList());
    }

    public List<Plant> findByOrigin(String origin) {
        if (origin == null) {
            return new ArrayList<>();
        }
        return plants.stream()
                .filter(p -> p.getOrigin() != null
                        && p.getOrigin().equalsIgnoreCase(origin))
                .collect(Collectors.toList());
    }

    public int size() {
        return plants.size();
    }
}