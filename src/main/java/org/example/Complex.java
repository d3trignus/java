package org.example;

public class Complex {
    private final Rational realPart, imPart;

    public Complex(Rational realPart, Rational imPart) {
        this.realPart = realPart;
        this.imPart = imPart;
    }

    public Complex(int realPart, int imPart) {
        this.realPart = new Rational(realPart, 1);
        this.imPart = new Rational(imPart, 1);
    }

    public Complex add(Complex num) {
        return new Complex(this.realPart.add(num.realPart), this.imPart.add(num.imPart));
    }

    public Complex multiply(Complex num) {
        Rational ac = this.realPart.multiply(num.realPart);
        Rational bd = this.imPart.multiply(num.imPart);
        Rational ad = this.realPart.multiply(num.imPart);
        Rational bc = this.imPart.multiply(num.realPart);

        return new Complex(ac.subtract(bd), ad.add(bc));
    }

    public Complex addAll(Complex[] numbers) {
        Complex sum = new Complex(0, 0);
        for (Complex number : numbers) {
            sum = sum.add(number);
        }

        return sum;
    }

    public Complex multiplyAll(Complex[] numbers) {
        Complex product = new Complex(1, 0);
        for (Complex number : numbers) {
            product = product.multiply(number);
        }
        return product;
    }

    public void printComplexNumber() {
        System.out.printf(
                "%s/%s + %s/%si",
                realPart.getNumerator(),
                realPart.getDenominator(),
                imPart.getNumerator(),
                imPart.getDenominator()
        );
    }




}
