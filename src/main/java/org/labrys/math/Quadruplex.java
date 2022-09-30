package org.labrys.math;

import java.text.MessageFormat;

import static java.lang.Math.*;

public class Quadruplex extends Number {
    public static final double TAU = 2 * PI;
    public final double r;
    public final double j;
    public final double k;

    public final double l;

    public Quadruplex(double r, double x, double y, double z) {
        this.r = r;
        this.j = x;
        this.k = y;
        this.l = z;
    }

    public Quadruplex add(double r, double x, double y, double z) {
        return add(new Quadruplex(r, x, y, z));
    }

    public Quadruplex add(double other) {
        return add(other,0,0,0);
    }

    public Quadruplex add(Quadruplex other) {
        return new Quadruplex(r + other.r, j + other.j, k + other.k, l + other.l);
    }

    public Quadruplex multiply(double r, double x, double y, double z) {
        return multiply(new Quadruplex(r, x, y, z));
    }

    public Quadruplex multiply(double scalar) {
        return multiply(scalar, 0, 0, 0);
    }

    public Quadruplex multiply(Quadruplex other) {
        /*
         * (a + bj + ck + dl)(e + fj + gk + hl)
         * = ae + afj + agk + ahl + bej + bfk + bgl - bh + cek + cfl - cg - chj + del - df - dgj - dhk
         * = ae - bh - cg - df + (af + be - ch - dg)j + (ag + bf + ce - dh)k + (ah + bg + cf + de)l
         */
        return new Quadruplex(r * other.r - j * other.l - k * other.k - l * other.j,
                r * other.j + j * other.r - k * other.l - l * other.k,
                r * other.k + k * other.r + j * other.j - l * other.l,
                r * other.l + l * other.r + j * other.k + k * other.j);
    }

    public Quaternion quaternionValue() {
        return new Quaternion(r, j, k, l);
    }

    @Override
    public int intValue() {
        return (int)r;
    }

    @Override
    public long longValue() {
        return (long)r;
    }

    @Override
    public float floatValue() {
        return (float)r;
    }

    @Override
    public double doubleValue() {
        return r;
    }

    public Complex complexValue() {
        return new Complex(r, k);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000000} + {1,number,0.0000000}j + {2,number,0.0000000}k + {3,number,0.0000000}l", r, j, k, l);
    }
}
