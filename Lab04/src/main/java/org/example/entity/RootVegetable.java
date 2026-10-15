package org.example.entity;

public class RootVegetable extends Vegetable {
    private double fiberContent;

    public RootVegetable(String name, double weight, double calories, double fiberContent) {
        super(name, weight, calories);
        this.fiberContent = fiberContent;
    }

    public double getFiberContent() {
        return this.fiberContent;
    }
}