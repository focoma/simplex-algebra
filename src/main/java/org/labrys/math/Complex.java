package org.labrys.math;

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

    public static Complex exp(Complex c) {
        return new Complex(Math.cos(c.i), Math.sin(c.i)).multiply(Math.exp(c.r)).complexValue();
    }

    public Complex add(double x, double y) {
        return add(x, y, 0, 0).complexValue();
    }

    public Complex add(Complex c) {
        return add(c.r, c.i, 0, 0).complexValue();
    }

    public static Complex generateUnit(double radians) {
        return new Complex(Math.cos(radians), Math.sin(radians));
    }

    public Quadruplex quadruplexValue() {
        return new Quadruplex(r, 0, i, 0);
    }
}
