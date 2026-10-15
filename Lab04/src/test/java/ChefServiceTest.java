import org.example.service.ChefService;
import org.example.entity.LeafVegetable;
import org.example.entity.RootVegetable;
import org.example.entity.Vegetable;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ChefServiceTest {

    private ChefService chef;
    private Vegetable[] vegetables;

    @BeforeMethod
    public void setUp() {
        chef = new ChefService();
        vegetables = new Vegetable[]{
                new LeafVegetable("Шпинат", 200, 23, 90),
                new RootVegetable("Морковь", 150, 41, 2.8),
                new LeafVegetable("Салат", 100, 15, 95)
        };
    }

    @Test
    public void testCalculateTotalCalories() {
        // 200/100*23 + 150/100*41 + 100/100*15 = 46 + 61.5 + 15 = 122.5
        double total = chef.calculateTotalCalories(vegetables);
        assertEquals(total, 122.5, 0.0001);
    }

    @Test
    public void testCalculateTotalCalories_EmptyArray() {
        double total = chef.calculateTotalCalories(new Vegetable[0]);
        assertEquals(total, 0.0, 0.0001);
    }

    @Test
    public void testCalculateTotalCalories_SingleVegetable() {
        Vegetable[] single = {new LeafVegetable("Тест", 100, 50, 90)};
        assertEquals(chef.calculateTotalCalories(single), 50.0, 0.0001);
    }

    @Test
    public void testSortByCalories() {
        chef.sortByCalories(vegetables);
        assertTrue(vegetables[0].getCalories() <= vegetables[1].getCalories());
        assertTrue(vegetables[1].getCalories() <= vegetables[2].getCalories());
        assertEquals(vegetables[0].getName(), "Салат");      // 15
        assertEquals(vegetables[1].getName(), "Шпинат");     // 23
        assertEquals(vegetables[2].getName(), "Морковь");    // 41
    }

    @Test
    public void testSortByCalories_EmptyArray() {
        Vegetable[] empty = new Vegetable[0];
        chef.sortByCalories(empty);
        assertEquals(empty.length, 0);
    }

    @Test
    public void testFindByCaloriesRange() {
        Vegetable[] result = chef.findByCaloriesRange(vegetables, 20, 30);
        assertEquals(result.length, 1);
        assertEquals(result[0].getName(), "Шпинат");
    }

    @Test
    public void testFindByCaloriesRange_Boundaries() {
        // Границы включительно
        Vegetable[] result = chef.findByCaloriesRange(vegetables, 15, 41);
        assertEquals(result.length, 3);
    }

    @Test
    public void testFindByCaloriesRange_NoMatches() {
        Vegetable[] result = chef.findByCaloriesRange(vegetables, 100, 200);
        assertEquals(result.length, 0);
    }

    @Test
    public void testFindByCaloriesRange_EmptyArray() {
        Vegetable[] empty = new Vegetable[0];
        Vegetable[] result = chef.findByCaloriesRange(empty, 0, 100);
        assertEquals(result.length, 0);
    }
}