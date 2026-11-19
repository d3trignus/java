package org.example.model;

public class FloweringPlant extends AbstractPlant {
    private String flowerColor;
    private int bloomingPeriodDays;
    private boolean blooming = false;

    public FloweringPlant(String name, String species, String origin,
                          String flowerColor, int bloomingPeriodDays) {
        super(name, species, origin);
        this.flowerColor = flowerColor;
        this.bloomingPeriodDays = bloomingPeriodDays;
    }

    public String getFlowerColor() {
        return flowerColor;
    }

    public void setFlowerColor(String flowerColor) {
        this.flowerColor = flowerColor;
        logger.info("Цвет цветков '{}' изменён на '{}'", name, flowerColor);
    }

    public int getBloomingPeriodDays() {
        return bloomingPeriodDays;
    }

    public boolean isBlooming() {
        return blooming;
    }

    public void startBlooming() {
        this.blooming = true;
        logger.info("Цветковое растение '{}' начало цвести (цвет: {})", name, flowerColor);
    }

    public void stopBlooming() {
        this.blooming = false;
        logger.info("Цветковое растение '{}' отцвело", name);
    }

    @Override
    public String care() {
        return "Цветковое растение: полив и подкормка, цветение " + bloomingPeriodDays + " дн.";
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(", цвет=%s, цветёт=%s",
                flowerColor, blooming ? "да" : "нет");
    }
}