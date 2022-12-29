package org.labrys.math;

import java.text.MessageFormat;

import static java.lang.Math.*;

public class Complex extends Quaternion {
    public Complex(double r, double i) {
        super(r, i, 0, 0);
    }

    public Complex(double r, double i, Quaternion b) {
        super(r + i*b.r, i*b.i, i*b.j, i*b.k);
    }

    public Complex(double r, Quaternion i) {
        super(r, i.i, i.j, i.k);
        if (i.r != 0) {
            throw new IllegalArgumentException("The imaginary quaternion should not have a real part.");
        }
    }

    public Complex add(Complex other) {
        return super.add(other).complexValue();
    }

    public Complex add(double x, double y) {
        return add(x, y, 0, 0).complexValue();
    }

    public Complex multiply(double scalar) {
        return super.multiply(scalar).complexValue();
    }

    public Complex multiply(Complex other) {
        return super.multiply(other).complexValue();
    }

    public static Complex exp(Complex c) {
        return new Complex(cos(c.i), sin(c.i)).multiply(Math.exp(c.r));
    }

    public double modulus() {
        return sqrt(norm());
    }

    public double norm() {
        return multiply(conjugate()).r;
    }

    public Complex conjugate() {
        return new Complex(r, -i);
    }

    public static Complex generateUnit(double radians) {
        return new Complex(cos(radians), sin(radians));
    }

    public Quadruplex quadruplexValue() {
        return new Quadruplex(r, 0, i, 0);
    }

    public Trirational trirationalValue() {
        return new Trirational(modulus(),Math.exp(atan2(i,r)/sqrt(3)),Math.exp(-atan2(i,r)/sqrt(3)));
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000} + {1,number,0.0000}i", r, i);
    }
}
