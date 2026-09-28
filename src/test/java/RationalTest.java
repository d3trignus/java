import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.example.Complex;
import org.example.Rational;

import static org.testng.Assert.assertEquals;

public class RationalTest {

    @DataProvider(name = "addData")
    public Object[][] addData() {
        return new Object[][]{
                {new Rational(1, 2), new Rational(1, 3), new Rational(5, 6)},
                {new Rational(1, 4), new Rational(1, 4), new Rational(8, 16)}
        };
    }

    @Test(dataProvider = "addData")
    public void testAdd(Rational a, Rational b, Rational expected) {
        Rational result = a.add(b);

        assertEquals(result.getNumerator(), expected.getNumerator(), "numerator");
        assertEquals(result.getDenominator(), expected.getDenominator(), "denominator");
    }

    @DataProvider(name = "subtractData")
    public Object[][] subtractData() {
        return new Object[][]{
                {new Rational(1, 2), new Rational(1, 3), new Rational(1, 6)},
                {new Rational(3, 4), new Rational(1, 4), new Rational(8, 16)}
        };
    }

    @Test(dataProvider = "subtractData")
    public void testSubtract(Rational a, Rational b, Rational expected) {
        Rational result = a.subtract(b);

        assertEquals(result.getNumerator(), expected.getNumerator(), "numerator");
        assertEquals(result.getDenominator(), expected.getDenominator(), "denominator");
    }

    @Test
    public void testMultiply() {
        Rational result = new Rational(1, 2).multiply(new Rational(2, 3));

        assertEquals(result.getNumerator(), 2, "numerator");
        assertEquals(result.getDenominator(), 6, "denominator");
    }

    @Test
    public void testGetters() {
        Rational r = new Rational(3, 5);

        assertEquals(r.getNumerator(), 3);
        assertEquals(r.getDenominator(), 5);
    }
}