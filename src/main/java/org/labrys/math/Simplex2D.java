package org.labrys.math;

import static java.lang.Math.*;

public class Simplex2D extends Number {
    public static final double TAU = 2 * PI;
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
                r * other.d + d * other.r + q * other.q);
    }

    public static Simplex2D generateUnit(double radians) {
        return new Simplex2D(2*cos(radians)/3.0, 2*cos(radians + 2*TAU/3)/3.0, 2*cos(radians + TAU/3)/3.0);
    }

    public Complex complexValue() {
        Complex o = basisComplex();
        return new Complex(r, 0)
                .add(o.multiply(q))
                .add(o.multiply(o).multiply(d));
    }

    public Triplex triplexValue() {
        Triplex o = basisTriplex();
        return new Triplex(2/3.0, -1/3.0, -1/3.0).multiply(r)
                .add(o.multiply(q))
                .add(o.multiply(o).multiply(d));
    }

    public Quadruplex quadruplexValue() {
        Quadruplex b = basisQuadruplex();
        return new Quadruplex(r,0,0,0)
                .add(b.multiply(this.q))
                .add(b.multiply(b).multiply(d));
    }

    private Complex basisComplex() {
        Complex o = new Complex(-0.5, Math.sqrt(3)/2.0);
        return o;
    }

    private Triplex basisTriplex() {
        Triplex o = new Triplex(-1/3.0, 2/3.0, -1/3.0);
        return o;
    }

    private Quadruplex basisQuadruplex() {
        Quadruplex q = new Quadruplex(-0.5, 0, Math.sqrt(3)/2.0, 0);
        return q;
    }

    public static Simplex2D exp(Simplex2D t) {
        return new Simplex2D(A(t.d), C(t.d), B(t.d)).multiply(A(t.q), B(t.q), C(t.q)).multiply(Math.exp(t.r)).normalize();
    }

    public static double A(double a) {
        return (2.0*Math.exp(-a/2.0) * cos(sqrt(3)*a/2.0) + Math.exp(a))/3.0;
    }

    public static double B(double b) {
        return (2.0*Math.exp(-b/2.0) * cos(sqrt(3)*b/2.0 - 2.0*PI/3.0) + Math.exp(b))/3.0;
    }

    public static double C(double c) {
        return (2.0*Math.exp(-c/2.0) * cos(sqrt(3)*c/2.0 + 2.0*PI/3.0) + Math.exp(c))/3.0;
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
