import org.example.reader.VegetableReader;
import org.example.entity.LeafVegetable;
import org.example.entity.RootVegetable;
import org.example.entity.Vegetable;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VegetableReaderTest {

    private VegetableReader reader;

    @BeforeMethod
    public void setUp() {
        reader = new VegetableReader();
    }

    @Test
    public void testReadFromResources_ValidFile() {
        Vegetable[] vegetables = reader.readFromResources("input.txt");

        // В файле 12 строк, одна из них "эщкере" — невалидная, должна быть пропущена
        assertEquals(vegetables.length, 11);

        // Проверяем первый овощ
        assertEquals(vegetables[0].getName(), "Шпинат");
        assertTrue(vegetables[0] instanceof LeafVegetable);

        // Проверяем корнеплод
        assertEquals(vegetables[1].getName(), "Морковь");
        assertTrue(vegetables[1] instanceof RootVegetable);
    }

    @Test
    public void testReadFromResources_EmptyFile() {
        Vegetable[] vegetables = reader.readFromResources("empty.txt");
        assertEquals(vegetables.length, 0);
    }

    @Test
    public void testReadFromResources_FileNotFound() {
        try {
            reader.readFromResources("nonexistent.txt");
            fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Файл не найден"));
        }
    }

    @Test
    public void testReadFromResources_InvalidLinesSkipped() {
        Vegetable[] vegetables = reader.readFromResources("invalid.txt");
        // Все строки в invalid.txt невалидны (кроме, возможно, одной с unknown — но и она не пройдёт
        // по числам... проверим: unknown;Тест;100;10;5 — числа валидны, но тип неизвестен,
        // factory кинет исключение → строка пропускается)
        assertEquals(vegetables.length, 0);
    }

    @Test
    public void testReadFromResources_AllLinesParsedCorrectly() {
        Vegetable[] vegetables = reader.readFromResources("input.txt");

        for (Vegetable v : vegetables) {
            assertNotNull(v.getName());
            assertTrue(v.getWeight() > 0);
            assertTrue(v.getCalories() >= 0);
        }
    }
}