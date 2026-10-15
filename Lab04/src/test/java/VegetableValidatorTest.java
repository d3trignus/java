import org.example.validator.VegetableValidator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VegetableValidatorTest {

    private VegetableValidator validator;

    @BeforeMethod
    public void setUp() {
        validator = new VegetableValidator();
    }

    @Test
    public void testIsValidLine_ValidLeaf() {
        assertTrue(validator.isValidLine("leaf;Шпинат;200;23;90"));
    }

    @Test
    public void testIsValidLine_ValidRoot() {
        assertTrue(validator.isValidLine("root;Морковь;150;41;2.8"));
    }

    @Test
    public void testIsValidLine_Null() {
        assertFalse(validator.isValidLine(null));
    }

    @Test
    public void testIsValidLine_Empty() {
        assertFalse(validator.isValidLine(""));
    }

    @Test
    public void testIsValidLine_Blank() {
        assertFalse(validator.isValidLine("   "));
    }

    @Test
    public void testIsValidLine_WrongArgsCount() {
        assertFalse(validator.isValidLine("leaf;Шпинат;200;23"));
        assertFalse(validator.isValidLine("leaf;Шпинат;200;23;90;extra"));
    }

    @Test
    public void testIsValidLine_InvalidNumber() {
        assertFalse(validator.isValidLine("leaf;Шпинат;abc;23;90"));
    }

    @Test
    public void testIsValidLine_NegativeWeight() {
        assertFalse(validator.isValidLine("leaf;Шпинат;-5;23;90"));
    }

    @Test
    public void testIsValidLine_ZeroWeight() {
        assertFalse(validator.isValidLine("leaf;Шпинат;0;23;90"));
    }

    @Test
    public void testIsValidLine_NegativeCalories() {
        assertFalse(validator.isValidLine("leaf;Шпинат;200;-23;90"));
    }

    @Test
    public void testIsValidLine_NegativeParam() {
        assertFalse(validator.isValidLine("leaf;Шпинат;200;23;-90"));
    }

    @Test
    public void testIsValidLine_UnknownType() {
        // Валидатор не отклоняет строку с неизвестным типом, только логирует предупреждение
        assertTrue(validator.isValidLine("unknown;Тест;100;10;5"));
    }

    @Test
    public void testIsValidWeight() {
        assertTrue(validator.isValidWeight(1));
        assertTrue(validator.isValidWeight(0.5));
        assertFalse(validator.isValidWeight(0));
        assertFalse(validator.isValidWeight(-1));
    }

    @Test
    public void testIsValidCalories() {
        assertTrue(validator.isValidCalories(0));
        assertTrue(validator.isValidCalories(100));
        assertFalse(validator.isValidCalories(-1));
    }

    @Test
    public void testIsValidParam() {
        assertTrue(validator.isValidParam(0));
        assertTrue(validator.isValidParam(50));
        assertFalse(validator.isValidParam(-1));
    }

    @DataProvider(name = "validLines")
    public Object[][] validLines() {
        return new Object[][]{
                {"leaf;Шпинат;200;23;90"},
                {"root;Морковь;150;41;2.8"},
                {"leaf;Салат;100;15;95"},
                {"root;Огурец;1;1;1"},
                {"LEAF;Test;100;10;50"},
                {"ROOT;Test;100;10;1"}
        };
    }

    @Test(dataProvider = "validLines")
    public void testIsValidLine_ParametrizedValid(String line) {
        assertTrue(validator.isValidLine(line));
    }

    @DataProvider(name = "invalidLines")
    public Object[][] invalidLines() {
        return new Object[][]{
                {null},
                {""},
                {"   "},
                {"leaf;Шпинат;200;23"},
                {"leaf;Шпинат;abc;23;90"},
                {"leaf;Шпинат;-1;23;90"},
                {"leaf;Шпинат;200;-23;90"},
                {"leaf;Шпинат;200;23;-90"}
        };
    }

    @Test(dataProvider = "invalidLines")
    public void testIsValidLine_ParametrizedInvalid(String line) {
        assertFalse(validator.isValidLine(line));
    }
}