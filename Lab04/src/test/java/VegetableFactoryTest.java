import org.example.factory.VegetableFactory;

import org.example.entity.LeafVegetable;
import org.example.entity.RootVegetable;
import org.example.entity.Vegetable;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VegetableFactoryTest {

    private VegetableFactory factory;

    @BeforeMethod
    public void setUp() {
        factory = new VegetableFactory();
    }

    @Test
    public void testCreateVegetable_Leaf() {
        Vegetable v = factory.createVegetable("leaf", "Шпинат", 200, 23, 90);
        assertTrue(v instanceof LeafVegetable);
        assertEquals(v.getName(), "Шпинат");
        assertEquals(((LeafVegetable) v).getWaterPercentage(), 90.0, 0.0001);
    }

    @Test
    public void testCreateVegetable_Root() {
        Vegetable v = factory.createVegetable("root", "Морковь", 150, 41, 2.8);
        assertTrue(v instanceof RootVegetable);
        assertEquals(v.getName(), "Морковь");
        assertEquals(((RootVegetable) v).getFiberContent(), 2.8, 0.0001);
    }

    @Test
    public void testCreateVegetable_CaseInsensitive() {
        Vegetable v1 = factory.createVegetable("LEAF", "X", 100, 10, 50);
        Vegetable v2 = factory.createVegetable("Root", "Y", 100, 10, 1);
        assertTrue(v1 instanceof LeafVegetable);
        assertTrue(v2 instanceof RootVegetable);
    }

    @Test
    public void testCreateVegetable_WithSpaces() {
        Vegetable v = factory.createVegetable("  leaf  ", "  Шпинат  ", 200, 23, 90);
        assertTrue(v instanceof LeafVegetable);
        assertEquals(v.getName(), "Шпинат");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateVegetable_NullType() {
        factory.createVegetable(null, "X", 100, 10, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateVegetable_UnknownType() {
        factory.createVegetable("unknown", "X", 100, 10, 5);
    }

    @Test
    public void testCreateVegetableFromArray_Valid() {
        String[] data = {"leaf", "Салат", "100", "15", "95"};
        Vegetable v = factory.createVegetable(data);
        assertTrue(v instanceof LeafVegetable);
        assertEquals(v.getName(), "Салат");
        assertEquals(v.getWeight(), 100.0, 0.0001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateVegetableFromArray_WrongLength() {
        String[] data = {"leaf", "Салат", "100", "15"};
        factory.createVegetable(data);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testCreateVegetableFromArray_InvalidNumber() {
        String[] data = {"leaf", "Салат", "abc", "15", "95"};
        factory.createVegetable(data);
    }

    @DataProvider(name = "types")
    public Object[][] types() {
        return new Object[][]{
                {"leaf", LeafVegetable.class},
                {"root", RootVegetable.class}
        };
    }

    @Test(dataProvider = "types")
    public void testCreateVegetable_Types(String type, Class<?> expectedClass) {
        Vegetable v = factory.createVegetable(type, "Test", 100, 10, 5);
        assertEquals(v.getClass(), expectedClass);
    }
}