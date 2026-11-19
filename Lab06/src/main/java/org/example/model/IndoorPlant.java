package org.example.model;

public class IndoorPlant extends AbstractPlant {
    private String potMaterial;
    private int potDiameterCm;

    public IndoorPlant(String name, String species, String origin,
                       String potMaterial, int potDiameterCm) {
        super(name, species, origin);
        this.potMaterial = potMaterial;
        this.potDiameterCm = potDiameterCm;
    }

    public String getPotMaterial() {
        return potMaterial;
    }

    public void setPotMaterial(String potMaterial) {
        this.potMaterial = potMaterial;
        logger.info("Материал горшка '{}' изменён на '{}'", name, potMaterial);
    }

    public int getPotDiameterCm() {
        return potDiameterCm;
    }

    public void repot(int newDiameterCm) {
        if (newDiameterCm <= potDiameterCm) {
            logger.warn("Новый диаметр {} <= текущего {} для '{}'",
                    newDiameterCm, potDiameterCm, name);
            throw new IllegalArgumentException("Новый горшок должен быть больше");
        }
        logger.info("Растение '{}' пересажено: {} см -> {} см",
                name, potDiameterCm, newDiameterCm);
        this.potDiameterCm = newDiameterCm;
    }

    public boolean needsRepot() {
        return potDiameterCm < 15;
    }

    @Override
    public String care() {
        return "Комнатное растение: горшок " + potMaterial + ", " + potDiameterCm + " см";
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(", горшок=%s %d см",
                potMaterial, potDiameterCm);
    }
}