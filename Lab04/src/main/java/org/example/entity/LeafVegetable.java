package org.example.entity;

public class LeafVegetable extends Vegetable {
    private double waterPercentage;

    public LeafVegetable(String name, double weight, double calories, double waterPercentage) {
        super(name, weight, calories);
        this.waterPercentage = waterPercentage;
    }

    public double getWaterPercentage() {
        return this.waterPercentage;
    }
}