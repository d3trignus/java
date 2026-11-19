package org.example.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractPlant implements Plant {
    protected static final Logger logger = LogManager.getLogger(AbstractPlant.class);

    protected String name;
    protected String species;
    protected String origin;
    protected double temperature = 20.0;
    protected int lightLevel = 50;
    protected boolean watered = false;

    protected AbstractPlant(String name, String species, String origin) {
        if (name == null || name.isBlank()) {
            logger.warn("Попытка создать растение с пустым именем");
            throw new IllegalArgumentException("Имя растения не может быть пустым");
        }
        this.name = name;
        this.species = species;
        this.origin = origin;
        logger.info("Создано растение: {} (вид: {}, происхождение: {})", name, species, origin);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        logger.info("Растение переименовано в {}", name);
    }

    @Override
    public String getSpecies() {
        return species;
    }

    @Override
    public void setSpecies(String species) {
        this.species = species;
        logger.info("Вид растения '{}' изменён на '{}'", name, species);
    }

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public void setOrigin(String origin) {
        this.origin = origin;
        logger.info("Происхождение растения '{}' изменено на '{}'", name, origin);
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public void setTemperature(double temperature) {
        if (temperature < -50 || temperature > 60) {
            logger.warn("Недопустимая температура {}°C для '{}'", temperature, name);
            throw new IllegalArgumentException("Температура вне допустимого диапазона");
        }
        this.temperature = temperature;
        logger.info("Для растения '{}' задана температура {}°C", name, temperature);
    }

    @Override
    public int getLightLevel() {
        return lightLevel;
    }

    @Override
    public void setLightLevel(int lightLevel) {
        if (lightLevel < 0 || lightLevel > 100) {
            logger.warn("Недопустимый уровень освещения {}%", lightLevel);
            throw new IllegalArgumentException("Освещение должно быть 0..100%");
        }
        this.lightLevel = lightLevel;
        logger.info("Для растения '{}' задано освещение {}%", name, lightLevel);
    }

    @Override
    public boolean isWatered() {
        return watered;
    }

    @Override
    public void water() {
        this.watered = true;
        logger.info("Растение '{}' полито", name);
    }

    @Override
    public String getInfo() {
        return String.format("%s [%s, %s] T=%.1f°C, свет=%d%%, полит=%s",
                name, species, origin, temperature, lightLevel, watered ? "да" : "нет");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getInfo();
    }
}