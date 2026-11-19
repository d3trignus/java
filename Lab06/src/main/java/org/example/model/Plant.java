package org.example.model;

public interface Plant {

    String getName();

    void setName(String name);

    String getSpecies();

    void setSpecies(String species);

    String getOrigin();

    void setOrigin(String origin);

    double getTemperature();

    void setTemperature(double temperature);

    int getLightLevel();       // освещение, %

    void setLightLevel(int lightLevel);

    boolean isWatered();

    void water();

    String getInfo();

    String care();
}