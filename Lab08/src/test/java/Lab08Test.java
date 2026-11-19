import org.example.Lab08;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Lab08Test {

    @DataProvider(name = "insertAfterKData")
    public Object[][] insertAfterKData() {
        return new Object[][]{
                {"Hello world", 5, " Java", "Hello Java world"},
                {"abcdef", 1, "-", "a-bcdef"},
                {"abcdef", 6, "!", "abcdef!"},
                {"abcdef", 10, "!", "abcdef"},
                {"", 1, "abc", ""},
                {"Привет, мир!", 7, " прекрасный", "Привет, прекрасный мир!"},
                {"12345", 3, "", "12345"},
                {"Цена: 100", 6, "$", "Цена: $100"},
                {"Путь: C:", 8, "\\folder", "Путь: C:\\folder"}
        };
    }

    @Test(dataProvider = "insertAfterKData")
    public void testInsertAfterKString(String text, int k, String sub, String expected) {
        assertEquals(Lab08.insertAfterKString(text, k, sub), expected);
    }

    @Test(dataProvider = "insertAfterKData")
    public void testInsertAfterKStream(String text, int k, String sub, String expected) {
        assertEquals(Lab08.insertAfterKStream(text, k, sub), expected);
    }

    @DataProvider(name = "insertAfterKInvalidData")
    public Object[][] insertAfterKInvalidData() {
        return new Object[][]{
                {null, 1, "sub"},
                {"text", 1, null},
                {"text", 0, "sub"},
                {"text", -5, "sub"}
        };
    }

    @Test(dataProvider = "insertAfterKInvalidData", expectedExceptions = IllegalArgumentException.class)
    public void testInsertAfterKStringExceptions(String text, int k, String sub) {
        Lab08.insertAfterKString(text, k, sub);
    }

    @Test(dataProvider = "insertAfterKInvalidData", expectedExceptions = IllegalArgumentException.class)
    public void testInsertAfterKStreamExceptions(String text, int k, String sub) {
        Lab08.insertAfterKStream(text, k, sub);
    }

    @DataProvider(name = "consonantsData")
    public Object[][] consonantsData() {
        return new Object[][]{
                {"abc def", "b c d f"},
                {"a b", "b"},
                {"a b c", "b c"},
                {"Hello world", "d h l r w"},
                {"", ""},
                {"   ", ""},
                {"!!!", ""},
                {"a", ""},
                {"ab", "b"},
                {"abc", "b c"},
                {"cat dog", "c d g t"},
                {"Cat cat CAT", ""},
                {"xyz", "x y z"},
                {"Мама мыла раму", "л р"},
                {"Кот кит крот", "р"}
        };
    }

    @Test(dataProvider = "consonantsData")
    public void testFindConsonantsString(String text, String expected) {
        assertEquals(Lab08.findConsonantsString(text), expected);
    }

    @Test(dataProvider = "consonantsData")
    public void testFindConsonantsStream(String text, String expected) {
        assertEquals(Lab08.findConsonantsStream(text), expected);
    }

    @Test
    public void testConsonantsCaseInsensitive() {
        assertEquals(Lab08.findConsonantsString("AbC aBc"), "b c");
        assertEquals(Lab08.findConsonantsStream("AbC aBc"), "b c");

        assertEquals(Lab08.findConsonantsString("AbC aBc ABC"), "");
        assertEquals(Lab08.findConsonantsStream("AbC aBc ABC"), "");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindConsonantsStringNull() {
        Lab08.findConsonantsString(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindConsonantsStreamNull() {
        Lab08.findConsonantsStream(null);
    }

    @DataProvider(name = "removeSentencesData")
    public Object[][] removeSentencesData() {
        return new Object[][]{
                {"", ""},
                {"   ", ""},
                {"Одно предложение.", "Одно предложение."},
                {
                        "Кот спит на диване. Собака гуляет во дворе. Рыжий кот любит молоко.",
                        "Кот спит на диване. Рыжий кот любит молоко."
                },
                {
                        "Java это язык. Python тоже хорош. Я изучаю java каждый день!",
                        "Java это язык. Я изучаю java каждый день!"
                },
                {
                        "Светит яркое солнце. Солнце греет землю! Дети любят солнце?",
                        "Светит яркое солнце. Солнце греет землю! Дети любят солнце?"
                },
                {
                        "Первый текст. Второй абзац! Третья фраза?",
                        "Первый текст."
                }
        };
    }

    @Test(dataProvider = "removeSentencesData")
    public void testRemoveSentencesString(String text, String expected) {
        assertEquals(Lab08.removeSentencesString(text), expected);
    }

    @Test(dataProvider = "removeSentencesData")
    public void testRemoveSentencesStream(String text, String expected) {
        assertEquals(Lab08.removeSentencesStream(text), expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveSentencesStringNull() {
        Lab08.removeSentencesString(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveSentencesStreamNull() {
        Lab08.removeSentencesStream(null);
    }
}
