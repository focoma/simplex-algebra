package org.labrys.math;

import java.text.MessageFormat;

import static java.lang.Math.*;

public class Tessarine extends Number {
    protected double r;
    protected double i;
    protected double j;
    protected double k;

    public Tessarine() {
    }

    public Tessarine(double r, double i, double j, double k) {
        this.r = r;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Tessarine add(double w, double x, double y, double z) {
        return add(new Tessarine(w, x, y, z));
    }

    public Tessarine add(Tessarine other) {
        return new Tessarine(r + other.r, i + other.i, j + other.j, k + other.k);
    }

    public Tessarine multiply(double w, double x, double y, double z) {
        return multiply(new Tessarine(w, x, y, z));
    }

    public Tessarine multiply(double scalar) {
        return multiply(scalar, 0, 0, 0);
    }

    public <T extends Tessarine> Tessarine multiply(T other) {
        /**
         * (w + xi + yj + zk)(s + ci + qj + hk)
         * Quaternion:
         * = ws - xc + yq - zh + (wc + xs + yh + zq)i + (wq + ys - xh - zc)j + (wh + zs + xq + yc)k
         */
        Tessarine product = new Tessarine(r*other.r - i*other.i + j*other.j - k*other.k,
                r * other.i + i * other.r + j * other.k + k*other.j,
                r * other.j + j * other.r - i * other.k - k*other.i,
                r * other.k + k * other.r + i * other.j + j*other.i);

        return product;
    }

    public static Tessarine exp(Tessarine c) {
        return new Tessarine(Math.exp(c.r),0,0,0)
                .multiply(cos(c.i), sin(c.i), 0, 0)
                .multiply(cosh(c.j), 0, sinh(c.j), 0)
                .multiply(cos(c.k), 0, 0, sin(c.k));
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
        return new Complex(r, i);
    }

    public SplitComplex splitComplexValue() {
        return new SplitComplex(r, j);
    }

    public Quadruplex quadruplexValue() {
        return new Quadruplex(r, (k+j)/sqrt(2), i, (k-j)/sqrt(2));
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000} + {1,number,0.0000}i + {2,number,0.0000}j + {3,number,0.0000}k", r, i, j, k);
    }
}
