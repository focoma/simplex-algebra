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

    public static Complex generateUnit(double radians) {
        return new Complex(Math.cos(radians), Math.sin(radians));
    }
}
