package org.labrys.math;

import static java.lang.Math.*;

public class Simplex2D extends Number {
    public final double r;
    public final double q;
    public final double d;

    public Simplex2D(double r, double q, double d) {
        this.r = r;
        this.q = q;
        this.d = d;
    }

    public Simplex2D normalize() {
        double min = min(r, min(q, d));
        return new Simplex2D(r-min, q-min, d-min);
    }

    public Simplex2D add(double x, double y, double z) {
        return add(new Simplex2D(x, y, z));
    }

    public Simplex2D add(Simplex2D other) {
        return new Simplex2D(r + other.r, q + other.q, d + other.d).normalize();
    }

    public Simplex2D multiply(double x, double y, double z) {
        return multiply(new Simplex2D(x, y, z));
    }

    public Simplex2D multiply(double scalar) {
        return multiply(scalar, 0, 0);
    }

    public Simplex2D multiply(Simplex2D other) {
        /*
         * (x + yq + zd)(s + cq + hd)
         * = xs + xcq + xhd + ysq + ycd + yh + zsd + zc + zhq
         */
        return new Simplex2D(r * other.r + q * other.d + d * other.q,
                r * other.q + q * other.r + d * other.d,
                r * other.d + d * other.r + q * other.q).normalize();
    }

    public static Simplex2D generateUnit(double radians) {
        return new Simplex2D(2*cos(radians)/3.0, 2*cos(radians - 2*PI/3)/3.0, 2*cos(radians + 2*PI/3)/3.0);
    }

    public Quaternion complexValue() {
        return new Quaternion(r, 0, 0, 0)
                .add(-q/2, q * Math.sqrt(3)/2, 0, 0)
                .add(-d/2, -d * Math.sqrt(3)/2, 0, 0);
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
        StringBuilder builder = new StringBuilder();
        if (r != 0 || (q == 0 && d == 0)) {
            builder.append(r);
        }
        if (q != 0) {
            if (r != 0) {
                if (q > 0) {
                    builder.append(" + ").append(q);
                } else {
                    builder.append(" - ").append(-q);
                }
            } else {
                builder.append(q);
            }
            builder.append("q");
        }
        if (d != 0) {
            if (r != 0 || q != 0) {
                if (d > 0) {
                    builder.append(" + ").append(d);
                } else {
                    builder.append(" - ").append(-d);
                }
            } else {
                builder.append(d);
            }
            builder.append("d");
        }

        return builder.toString();
    }
}
