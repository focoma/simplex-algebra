package org.labrys.math;

import java.text.MessageFormat;

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

    public Quaternion multiply(Quaternion other) {
        /**
         * (w + xi + yj + zk)(s + ci + qj + hk)
         * = ws + wcb + wqp + whg + xsb + xcbb + xqbp + xhbg + ysp + ycpb + yqpp + yhpg + zsg + zcgb + zqgp + zhgg
         * = ws + (wc + xs)b + (wq + ys)p + (wh + zs)g + xcbb + xqbp + xhbg + ycpb + yqpp + yhpg + zcgb + zqgp + zhgg
         *
         * Commutative:
         * = ws + (wc + xs)b + (wq + ys)p + (wh + zs)g + xcbb + (xq + yc)bp + (xh + zc)bg + yqpp + (yh + zq)pg + zhgg
         *
         * Non-Commutative:
         * = ws + (wc + xs)b + (wq + ys)p + (wh + zs)g + xcbb + (xq - yc)bp + (-xh + zc)bg + yqpp + (yh - zq)pg + zhgg
         *
         * Quaternion:
         * = ws - xc - yq - zh + (wc + xs + yh - zq)i + (wq + ys - xh + zc)j + (wh + zs + xq - yc)k
         */
        Quaternion product = new Quaternion(r*other.r - i*other.i - j*other.j - k*other.k,
                r * other.i + i * other.r + j * other.k - k*other.j,
                r * other.j + j * other.r - i * other.k + k*other.i,
                r * other.k + k * other.r + i * other.j - j*other.i);

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

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000} + {1,number,0.0000}i + {2,number,0.0000}j + {3,number,0.0000}k", r, i, j, k);
    }
}
