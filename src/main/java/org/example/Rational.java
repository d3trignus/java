package org.example;

public class Rational {
    public int numerator, denominator;

    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Rational add(Rational num) {
        int newNumerator = this.numerator * num.denominator + num.numerator * this.denominator;
        int newDenominator = this.denominator * num.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    public Rational subtract(Rational num) {
        int newNumerator = this.numerator * num.denominator - num.numerator * this.denominator;
        int newDenominator = this.denominator * num.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    public Rational multiply(Rational num) {
        int newNumerator = this.numerator * num.numerator;
        int newDenominator = this.denominator * num.denominator;
        return new Rational(newNumerator, newDenominator);
    }

//    public Rational divide(Rational num) {
//        int newNumerator = this.numerator * num.denominator;
//        int newDenominator = this.denominator * num.numerator;
//        return new Rational(newNumerator, newDenominator);
//    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
}
