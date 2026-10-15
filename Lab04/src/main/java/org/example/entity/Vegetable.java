package org.example.entity;

public abstract class Vegetable {
    private String name;
    private double weight;
    private double calories;

    public Vegetable(String name, double weight, double calories) {
        this.name = name;
        this.weight = weight;
        this.calories = calories;
    }

    public double calculateTotalCalories() {
        return weight / 100 * calories;
    }

    public String getName() {
        return this.name;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getCalories() {
        return this.calories;
    }
}