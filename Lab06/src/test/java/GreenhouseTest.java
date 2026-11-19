import org.example.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class GreenhouseTest {

    private Greenhouse greenhouse;

    @BeforeMethod
    public void setUp() {
        greenhouse = new Greenhouse();
        greenhouse.purchase(new BushyPlant("Куст1", "Bushus", "Лес", 1.0));
        greenhouse.purchase(new BushyPlant("Куст2", "Bushus", "Лес", 1.2));
        greenhouse.purchase(new FloweringPlant("Тюльпан", "Tulipa",
                "Степь", "красный", 14));
        greenhouse.purchase(new IndoorPlant("Фикус", "Ficus",
                "Тропики", "керамика", 12));
    }

    @Test
    public void testAddNullPlantThrows() {
        assertThrows(IllegalArgumentException.class, () -> greenhouse.addPlant(null));
    }

    @Test
    public void testSize() {
        assertEquals(greenhouse.size(), 4);
    }

    @Test
    public void testWaterAll() {
        greenhouse.waterAll();
        greenhouse.getAllPlants().forEach(p -> assertTrue(p.isWatered()));
    }

    @Test
    public void testSetTemperatureAll() {
        greenhouse.setTemperatureAll(18);
        greenhouse.getAllPlants().forEach(p -> assertEquals(p.getTemperature(), 18.0));
    }

    @Test
    public void testSetLightingAll() {
        greenhouse.setLightingAll(60);
        greenhouse.getAllPlants().forEach(p -> assertEquals(p.getLightLevel(), 60));
    }

    @Test
    public void testFindBySpecies() {
        List<Plant> found = greenhouse.findBySpecies("Bushus");
        assertEquals(found.size(), 2);
    }

    @Test
    public void testFindBySpeciesCaseInsensitive() {
        List<Plant> found = greenhouse.findBySpecies("bushus");
        assertEquals(found.size(), 2);
    }

    @Test
    public void testFindByOrigin() {
        List<Plant> found = greenhouse.findByOrigin("Тропики");
        assertEquals(found.size(), 1);
        assertEquals(found.get(0).getName(), "Фикус");
    }

    @Test
    public void testFindByOriginCaseInsensitive() {
        List<Plant> found = greenhouse.findByOrigin("тропики");
        assertEquals(found.size(), 1);
    }

    @Test
    public void testFindByOriginNullReturnsEmpty() {
        assertTrue(greenhouse.findByOrigin(null).isEmpty());
    }

    @Test
    public void testFindByOriginNoMatch() {
        assertTrue(greenhouse.findByOrigin("Антарктида").isEmpty());
    }

    @Test
    public void testRemoveBySpecies() {
        int removed = greenhouse.removeBySpecies("Bushus");
        assertEquals(removed, 2);
        assertEquals(greenhouse.size(), 2);
        assertTrue(greenhouse.findBySpecies("Bushus").isEmpty());
    }

    @Test
    public void testRemoveNonExistentSpecies() {
        int removed = greenhouse.removeBySpecies("NoSuchSpecies");
        assertEquals(removed, 0);
        assertEquals(greenhouse.size(), 4);
    }
}