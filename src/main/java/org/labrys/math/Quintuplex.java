package org.labrys.math;

import java.text.MessageFormat;
import java.util.Objects;

import static java.lang.Math.*;

public class Quintuplex extends Number {
    public static final double TAU = 2 * PI;
    public final double r;
    public final double w;
    public final double x;
    public final double y;
    public final double z;

    public Quintuplex(double r, double w, double x, double y, double z) {
        this.r = r;
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quintuplex add(double r, double w, double x, double y, double z) {
        return add(new Quintuplex(r, w, x, y, z));
    }

    public Quintuplex add(double other) {
        return add(other,0,0,0,0);
    }

    public Quintuplex add(Quintuplex other) {
        return new Quintuplex(r + other.r, w + other.w, x + other.x, y + other.y, z + other.z);
    }

    public Quintuplex multiply(double r, double w, double x, double y, double z) {
        return multiply(new Quintuplex(r, w, x, y, z));
    }

    public Quintuplex multiply(double scalar) {
        return multiply(scalar, 0, 0, 0, 0);
    }

    public Quintuplex multiply(Quintuplex other) {
        /*
         * (a + bw + cx + dy + ez)(f + gw + hx + iy + jz)
         * = (af + bj + ci + dh + eg) + (ag + bf + cj + di + eh)w + (ah + bg + cf + dj + ei)x + (ai + bh + cg + df + ej)y + (aj + bi + ch + dg + ef)z
         */
        return new Quintuplex(r*other.r + w*other.z + x*other.y + y*other.x + z*other.w,
                r*other.w + w*other.r + x*other.z + y*other.y + z*other.x,
                r*other.x + w*other.w + x*other.r + y*other.z + z*other.y,
                r*other.y + w*other.x + x*other.w + y*other.r + z*other.z,
                r*other.z + w*other.y + x*other.x + y*other.w + z*other.r);
    }

    public double euclideanNorm() {
        return sqrt(pow(r,2) + pow(w,2) + pow(x,2) + pow(y,2) + pow(z,2));
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
        return MessageFormat.format("{0,number,0.0000000} + {1,number,0.0000000}w + {2,number,0.0000000}x + {3,number,0.0000000}y + {4,number,0.0000000}z", r, w, x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quintuplex that = (Quintuplex) o;
        return Double.compare(that.r, r) == 0 && Double.compare(that.w, w) == 0 && Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, w, x, y, z);
    }
}
