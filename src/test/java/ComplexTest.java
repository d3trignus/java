import org.example.Complex;
import org.example.Rational;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.testng.Assert.assertEquals;

public class ComplexTest {

    private Rational getReal(Complex c) throws Exception {
        Field field = Complex.class.getDeclaredField("realPart");
        field.setAccessible(true);
        return (Rational) field.get(c);
    }

    private Rational getIm(Complex c) throws Exception {
        Field field = Complex.class.getDeclaredField("imPart");
        field.setAccessible(true);
        return (Rational) field.get(c);
    }

    @Test
    public void testAdd() throws Exception {
        Complex a = new Complex(1, 2);
        Complex b = new Complex(3, 4);

        Complex result = a.add(b);

        assertEquals(getReal(result).getNumerator(), 4);
        assertEquals(getReal(result).getDenominator(), 1);

        assertEquals(getIm(result).getNumerator(), 6);
        assertEquals(getIm(result).getDenominator(), 1);
    }

    @Test
    public void testMultiply() throws Exception {
        Complex a = new Complex(1, 2); // 1 + 2i
        Complex b = new Complex(3, 4); // 3 + 4i

        Complex result = a.multiply(b);

        // (1 + 2i)(3 + 4i) = 3 + 4i + 6i + 8i^2 = 3 + 10i - 8 = -5 + 10i
        assertEquals(getReal(result).getNumerator(), -5);
        assertEquals(getReal(result).getDenominator(), 1);

        assertEquals(getIm(result).getNumerator(), 10);
        assertEquals(getIm(result).getDenominator(), 1);
    }

    @Test
    public void testAddAll() throws Exception {
        Complex[] numbers = new Complex[]{
                new Complex(1, 2),
                new Complex(3, 4),
                new Complex(5, 6)
        };

        Complex result = new Complex(0, 0).addAll(numbers);

        assertEquals(getReal(result).getNumerator(), 9);
        assertEquals(getReal(result).getDenominator(), 1);

        assertEquals(getIm(result).getNumerator(), 12);
        assertEquals(getIm(result).getDenominator(), 1);
    }

    @Test
    public void testPrintComplexNumber() {
        Complex c = new Complex(new Rational(1, 2), new Rational(3, 4));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(out));
            c.printComplexNumber();
        } finally {
            System.setOut(originalOut);
        }

        assertEquals(out.toString().trim(), "1/2 + 3/4i");
    }
}