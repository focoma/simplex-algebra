package org.labrys.math;

import static java.lang.Math.*;

public class Triplex extends Number {
    public static final double TAU = 2 * PI;
    public final double r;
    public final double j;
    public final double k;

    public Triplex(double r, double y, double z) {
        this.r = r;
        this.j = y;
        this.k = z;
    }

    public Triplex add(double x, double y, double z) {
        return add(new Triplex(x, y, z));
    }

    public Triplex add(Triplex other) {
        return new Triplex(r + other.r, j + other.j, k + other.k);
    }

    public Triplex multiply(double x, double y, double z) {
        return multiply(new Triplex(x, y, z));
    }

    public Triplex multiply(double scalar) {
        return multiply(scalar, 0, 0);
    }

    public Triplex multiply(Triplex other) {
        /*
         * (a + bj + ck)(d + ej + fk)
         * = ad + bf + ce + aej + bdj + cfj + afk + bek + cdk
         */
        return new Triplex(r * other.r + j * other.k + k * other.j,
                r * other.j + j * other.r + k * other.k,
                r * other.k + k * other.r + j * other.j);
    }

    public Triplex divide(Triplex t) {
        return divide(t.r, t.j, t.k);
    }

    public Triplex divide(double a, double b, double c) {
        return multiply(a*a - b*c, c*c - a*b, b*b - a*c).multiply(1.0/(a*a*a + b*b*b + c*c*c - 3*a*b*c));
    }

    public Triplex conjugate() {
        return new Triplex(r*r - j * k, k * k - r* j, j * j - r* k);
    }

    public double norm() {
        return abs(cbrt(multiply(conjugate()).r));
    }

    public Quaternion quaternionValue() {
        return new Quaternion(0, r, j, k);
    }
    
    public Quadruplex quadruplexValue() {
        return new Quadruplex(r, j, k,0);
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
        if (r != 0 || (j == 0 && k == 0)) {
            builder.append(r);
        }
        if (j != 0) {
            if (r != 0) {
                if (j > 0) {
                    builder.append(" + ").append(j);
                } else {
                    builder.append(" - ").append(-j);
                }
            } else {
                builder.append(j);
            }
            builder.append("j");
        }
        if (k != 0) {
            if (r != 0 || j != 0) {
                if (k > 0) {
                    builder.append(" + ").append(k);
                } else {
                    builder.append(" - ").append(-k);
                }
            } else {
                builder.append(k);
            }
            builder.append("k");
        }

        return builder.toString();
    }
}
