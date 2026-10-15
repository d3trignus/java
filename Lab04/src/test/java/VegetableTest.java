import org.example.entity.*;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

public class VegetableTest {

    @Test
    public void testCalculateTotalCalories_LeafVegetable() {
        LeafVegetable leaf = new LeafVegetable("Шпинат", 200, 23, 90);
        // 200 / 100 * 23 = 46
        assertEquals(leaf.calculateTotalCalories(), 46.0, 0.0001);
    }

    @Test
    public void testCalculateTotalCalories_RootVegetable() {
        RootVegetable root = new RootVegetable("Морковь", 150, 41, 2.8);
        // 150 / 100 * 41 = 61.5
        assertEquals(root.calculateTotalCalories(), 61.5, 0.0001);
    }

    @Test
    public void testCalculateTotalCalories_ZeroWeight() {
        LeafVegetable leaf = new LeafVegetable("Тест", 0, 50, 90);
        assertEquals(leaf.calculateTotalCalories(), 0.0, 0.0001);
    }

    @Test
    public void testGetters_LeafVegetable() {
        LeafVegetable leaf = new LeafVegetable("Салат", 100, 15, 95);
        assertEquals(leaf.getName(), "Салат");
        assertEquals(leaf.getWeight(), 100.0, 0.0001);
        assertEquals(leaf.getCalories(), 15.0, 0.0001);
        assertEquals(leaf.getWaterPercentage(), 95.0, 0.0001);
    }

    @Test
    public void testGetters_RootVegetable() {
        RootVegetable root = new RootVegetable("Редис", 120, 20, 1.6);
        assertEquals(root.getName(), "Редис");
        assertEquals(root.getWeight(), 120.0, 0.0001);
        assertEquals(root.getCalories(), 20.0, 0.0001);
        assertEquals(root.getFiberContent(), 1.6, 0.0001);
    }

    @DataProvider(name = "calorieData")
    public Object[][] calorieData() {
        return new Object[][]{
                {new LeafVegetable("A", 100, 10, 50), 10.0},
                {new LeafVegetable("B", 250, 20, 60), 50.0},
                {new RootVegetable("C", 300, 77, 2.0), 231.0},
                {new RootVegetable("D", 1, 1, 1), 0.01}
        };
    }

    @Test(dataProvider = "calorieData")
    public void testCalculateTotalCalories_Parametrized(Vegetable v, double expected) {
        assertEquals(v.calculateTotalCalories(), expected, 0.0001);
    }
}