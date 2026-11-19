import org.example.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlantTest {

    private BushyPlant bush;
    private FloweringPlant flower;
    private IndoorPlant indoor;

    @BeforeMethod
    public void setUp() {
        bush = new BushyPlant("Куст", "Bushus", "Лес", 1.5);
        flower = new FloweringPlant("Цветок", "Flos", "Степь", "жёлтый", 10);
        indoor = new IndoorPlant("Фикус", "Ficus", "Тропики", "пластик", 10);
    }

    @Test
    public void testCreatePlantWithNullNameThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new BushyPlant(null, "sp", "Лес", 1.0));
    }

    @Test
    public void testWaterPlant() {
        assertFalse(bush.isWatered());
        bush.water();
        assertTrue(bush.isWatered());
    }

    @Test
    public void testSetTemperature() {
        bush.setTemperature(25.5);
        assertEquals(bush.getTemperature(), 25.5);
    }

    @Test
    public void testSetTemperatureInvalid() {
        assertThrows(IllegalArgumentException.class, () -> bush.setTemperature(100));
    }

    @Test
    public void testSetLightingInvalid() {
        assertThrows(IllegalArgumentException.class, () -> flower.setLightLevel(150));
    }

    @Test
    public void testBushPrune() {
        double before = bush.getHeightMeters();
        bush.markNeedsPruning();
        assertTrue(bush.isNeedsPruning());
        bush.prune();
        assertFalse(bush.isNeedsPruning());
        assertTrue(bush.getHeightMeters() < before);
    }

    @Test
    public void testFlowerBloom() {
        assertFalse(flower.isBlooming());
        flower.startBlooming();
        assertTrue(flower.isBlooming());
        flower.stopBlooming();
        assertFalse(flower.isBlooming());
    }

    @Test
    public void testIndoorRepot() {
        assertTrue(indoor.needsRepot());
        indoor.repot(20);
        assertEquals(indoor.getPotDiameterCm(), 20);
        assertFalse(indoor.needsRepot());
    }

    @Test
    public void testIndoorRepotSmallerThrows() {
        assertThrows(IllegalArgumentException.class, () -> indoor.repot(5));
    }

    @Test
    public void testCareStrings() {
        assertNotNull(bush.care());
        assertNotNull(flower.care());
        assertNotNull(indoor.care());
    }

    @Test
    public void testGetInfoContainsName() {
        assertTrue(bush.getInfo().contains("Куст"));
    }

    @Test
    public void testSetOrigin() {
        bush.setOrigin("Субтропики");
        assertEquals(bush.getOrigin(), "Субтропики");
    }
}