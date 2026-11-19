package org.example.model;

public class BushyPlant extends AbstractPlant {
    private double heightMeters;
    private boolean needsPruning;

    public BushyPlant(String name, String species, String origin, double heightMeters) {
        super(name, species, origin);
        this.heightMeters = heightMeters;
    }

    public double getHeightMeters() {
        return heightMeters;
    }

    public void setHeightMeters(double heightMeters) {
        this.heightMeters = heightMeters;
        logger.info("Высота куста '{}' изменена на {} м", name, heightMeters);
    }

    public boolean isNeedsPruning() {
        return needsPruning;
    }

    public void prune() {
        this.needsPruning = false;
        this.heightMeters *= 0.85;
        logger.info("Куст '{}' подрезан, новая высота {} м", name, heightMeters);
    }

    public void markNeedsPruning() {
        this.needsPruning = true;
        logger.info("Куст '{}' помечен как требующий обрезки", name);
    }

    @Override
    public String care() {
        return "Кустовое растение: регулярная обрезка, высота " + heightMeters + " м";
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(", высота=%.2f м, обрезка=%s",
                heightMeters, needsPruning ? "требуется" : "не требуется");
    }
}