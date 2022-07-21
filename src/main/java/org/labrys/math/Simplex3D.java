package org.labrys.math;

import java.text.MessageFormat;

public class Simplex3D extends Number {
    private double r;
    private double b;
    private double p;
    private double g;

    public Simplex3D() {
    }

    public Simplex3D(double r, double b, double p, double g) {
        this.r = r;
        this.b = b;
        this.p = p;
        this.g = g;
    }

    public Simplex3D add(double w, double x, double y, double z) {
        return add(new Simplex3D(w, x, y, z));
    }

    public Simplex3D add(Simplex3D other) {
        return new Simplex3D(r + other.r, b + other.b, p + other.p, g + other.g);
    }

    public Simplex3D multiply(double w, double x, double y, double z) {
        return multiply(new Simplex3D(w, x, y, z));
    }

    public Simplex3D multiply(Simplex3D other) {
        /**
         * (w + xb + yp + zg)(s + cb + qp + hg)
         * = ws + wcb + wqp + whg + xsb + xcbb + xqbp + xhbg + ysp + ycpb + yqpp + yhpg + zsg + zcgb + zqgp + zhgg
         * = ws + (wc + xs)b + (wq + ys)p + (wh + zs)g + xcbb + xqbp + xhbg + ycpb + yqpp + yhpg + zcgb + zqgp + zhgg
         *
         * Commutative:
         * = ws + (wc + xs)b + (wq + ys)p + (wh + zs)g + xcbb + (xq + yc)bp + (xh + zc)bg + yqpp + (yh + zq)pg + zhgg
         *
         * Non-Commutative:
         * = ws + (wc + xs)b + (wq + ys)p + (wh + zs)g + xcbb + (xq - yc)bp + (-xh + zc)bg + yqpp + (yh - zq)pg + zhgg
         *
         * Simplectic:
         * = ws + (wc + xs + yq + yh + zq)b + (wq + ys + xq + yc + zh)p + (wh + zs + xh + zc)g + xcbb
         */
        Simplex3D product = new Simplex3D(r * other.r,
                r * other.b + b * other.r + p * other.p + p * other.g + g * other.p,
                r * other.p + p * other.r + b * other.p + p * other.b + g * other.g,
                r * other.g + g * other.r + b * other.g + g * other.b);

        product = product.add(new Simplex3D(0, b*other.b/3, b*other.b, b*other.b));

        return product;
    }

    private Simplex3D raise(double power) {
        return new Simplex3D();
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
        return MessageFormat.format("{0,number,0.0000} + {1,number,0.0000}b + {2,number,0.0000}p + {3,number,0.0000}g", r, b, p, g);
    }
}
