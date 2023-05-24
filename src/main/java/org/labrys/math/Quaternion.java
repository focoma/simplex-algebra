package org.labrys.math;

import java.text.MessageFormat;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Quaternion extends Number {
    protected double r;
    protected double i;
    protected double j;
    protected double k;

    public Quaternion() {
    }

    public Quaternion(double r, double i, double j, double k) {
        this.r = r;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Quaternion add(double w, double x, double y, double z) {
        return add(new Quaternion(w, x, y, z));
    }

    public Quaternion add(Quaternion other) {
        return new Quaternion(r + other.r, i + other.i, j + other.j, k + other.k);
    }

    public Quaternion multiply(double w, double x, double y, double z) {
        return multiply(new Quaternion(w, x, y, z));
    }

    public Quaternion multiply(double scalar) {
        return multiply(scalar, 0, 0, 0);
    }

    public <T extends Quaternion> Quaternion multiply(T other) {
        /**
         * (w + xi + yj + zk)(s + ci + qj + hk)
         * Quaternion:
         * = ws - xc - yq - zh + (wc + xs + yh - zq)i + (wq + ys - xh + zc)j + (wh + zs + xq - yc)k
         */
        Quaternion product = new Quaternion(r*other.r - i*other.i - j*other.j - k*other.k,
                r * other.i + i * other.r + j * other.k - k*other.j,
                r * other.j + j * other.r - i * other.k + k*other.i,
                r * other.k + k * other.r + i * other.j - j*other.i);

        return product;
    }

    public double euclideanNorm() {
        return sqrt(pow(r,2) + pow(i,2) + pow(j,2) + pow(k,2));
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

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000} + {1,number,0.0000}i + {2,number,0.0000}j + {3,number,0.0000}k", r, i, j, k);
    }
}
