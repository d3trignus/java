import org.example.Lab07;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.Function;
import java.util.function.Predicate;

public class Lab07Test {

    @DataProvider(name = "div13")
    public Object[][] div13() {
        return new Object[][]{
                {26, true},
                {39, true},
                {0, true},
                {-13, true},
                {27, false},
                {1, false},
                {-14, false}
        };
    }

    @Test(dataProvider = "div13")
    public void testCheckCustom(int n, boolean expected) {
        Lab07.Check check = x -> x % 13 == 0;
        Assert.assertEquals(check.test(n), expected);
    }

    @Test(dataProvider = "div13")
    public void testCheckPredicate(int n, boolean expected) {
        Predicate<Integer> check = x -> x % 13 == 0;
        Assert.assertEquals(check.test(n), expected);
    }

    private double sumOf(Object... a) {
        double sum = 0;
        for (Object o : a) {
            if (o instanceof Number) {
                sum += ((Number) o).doubleValue();
            }
        }
        return sum;
    }

    @Test
    public void testSummatorCustom() {
        Lab07.Summator s = this::sumOf;
        Assert.assertEquals(s.sum(1, 2.5, 3L, "abc"), 6.5);
    }

    @Test
    public void testSummatorEmpty() {
        Lab07.Summator s = this::sumOf;
        Assert.assertEquals(s.sum(), 0.0);
    }

    @Test
    public void testSummatorIgnoresNotNumbers() {
        Lab07.Summator s = this::sumOf;
        Assert.assertEquals(s.sum("a", 'b', true, null), 0.0);
    }

    @Test
    public void testSummatorFunction() {
        Function<Object[], Double> f = a -> sumOf(a);
        Assert.assertEquals(f.apply(new Object[]{1, 2.5f, 3L}), 6.5);
    }

    @DataProvider(name = "times")
    public Object[][] times() {
        return new Object[][]{
                {"07:05:45PM", "19:05:45"},
                {"12:00:00AM", "00:00:00"},
                {"12:00:00PM", "12:00:00"},
                {"12:30:15AM", "00:30:15"},
                {"01:00:00AM", "01:00:00"},
                {"11:59:59PM", "23:59:59"},
                {"11:59:59AM", "11:59:59"}
        };
    }

    @Test(dataProvider = "times")
    public void testConvert(String input, String expected) {
        Assert.assertEquals(Lab07.convert(input), expected);
    }

    @Test(dataProvider = "times")
    public void testTimeConverterCustom(String input, String expected) {
        Lab07.TimeConverter t = s -> Lab07.convert(s);
        Assert.assertEquals(t.convert(input), expected);
    }

    @Test(dataProvider = "times")
    public void testTimeConverterFunction(String input, String expected) {
        Function<String, String> t = s -> Lab07.convert(s);
        Assert.assertEquals(t.apply(input), expected);
    }
}