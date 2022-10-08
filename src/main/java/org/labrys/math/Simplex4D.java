package org.labrys.math;

import java.text.MessageFormat;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

public class Simplex4D extends Number {
    public double r;
    public double f;
    public double f2;
    public double f3;
    public double f4;

    public Simplex4D() {
    }

    public Simplex4D(double r, double f, double f2, double f3, double f4) {
        this.r = r;
        this.f = f;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
    }

    public Simplex4D add(double v, double w, double x, double y, double z) {
        return add(new Simplex4D(v, w, x, y, z));
    }

    public Simplex4D add(Simplex4D other) {
        return new Simplex4D(r + other.r, f + other.f, f2 + other.f2, f3 + other.f3, f4 + other.f4);
    }

    public Simplex4D multiply(double v, double w, double x, double y, double z) {
        return multiply(new Simplex4D(v, w, x, y, z));
    }

    public Simplex4D multiply(double scalar) {
        return multiply(scalar, 0, 0, 0, 0);
    }

    public Simplex4D multiply(Simplex4D other) {
        //TODO
        Simplex4D product = null;
        return product;
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

    public Quadruplex quadruplexValue() {
        // TODO
        return null;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000} + {1,number,0.0000}b + {2,number,0.0000}p + {3,number,0.0000}g", r, f, f2, f3);
    }
}
