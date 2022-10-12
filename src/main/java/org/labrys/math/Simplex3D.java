package org.labrys.math;

import java.text.MessageFormat;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

public class Simplex3D extends Number {
    public double r;
    public double b;
    public double p;
    public double g;

    public Simplex3D() {
    }

    public Simplex3D(double r, double b, double p, double g) {
        this.r = r;
        this.b = b;
        this.p = p;
        this.g = g;
    }

    public Simplex3D normalize() {
        double min = min(r, min(b, min(p, g)));
        return new Simplex3D(r-min, b-min, p-min, g-min);
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

    public Simplex3D multiply(double scalar) {
        return multiply(scalar, 0, 0, 0);
    }

    public Simplex3D multiply(Simplex3D other) {
        /**
         * (w + xb + yp + zg)(s + cb + qp + hg)
         * = ws + wcb + wqp + whg + xsb + xcbb + xqbp + xhbg + ysp + ycpb + yqpp + yhpg + zsg + zcgb + zqgp + zhgg
         * = ws + (wc + xs)b + (wq + ys)p + (wh + zs)g + xcbb + xqbp + xhbg + ycpb + yqpp + yhpg + zcgb + zqgp + zhgg
         *
         * = ws + xh + zc + yq + (wc + xs + yh + zq)b + (wq + ys + xc + zh)p + (wh + zs + xq + yc)g
         */
        Simplex3D product = new Simplex3D(r * other.r + b * other.g + g * other.b + p * other.p,
                r * other.b + b * other.r + p * other.g + g * other.p,
                r * other.p + p * other.r + b * other.b + g * other.g,
                r * other.g + g * other.r + b * other.p + p * other.b);

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

    public Quaternion quaternionValue() {
        return triplexValue().quaternionValue();
    }

    public Triplex triplexValue() {
        Simplex3D normalized = normalize();
        Triplex c = basisTriplex();

        return new Triplex(normalized.r, 0, 0)
                .add(c.multiply(normalized.b))
                .add(c.multiply(c).multiply(normalized.p))
                .add(c.multiply(c).multiply(c).multiply(normalized.g));
    }

    private Triplex basisTriplex() {
        Triplex c = new Triplex(1, 1, 1)
                .multiply(-1.0 / 3.0)
                .add(0, -1.0 / sqrt(3.0), 1.0 / sqrt(3.0));
        return c;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000} + {1,number,0.0000}b + {2,number,0.0000}p + {3,number,0.0000}g", r, b, p, g);
    }
}
