package org.labrys.math;

import java.text.MessageFormat;

import static java.lang.Math.*;

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

    public Simplex4D normalize() {
        double min = min(r, min(f, min(f2, min(f3, f4))));
        return new Simplex4D(r-min, f-min, f2-min, f3-min, f4-min);
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
        /**
         * (v + wf + xf2 + yf3 + zf4)(a + bf + cf2 + df3 + ef4)
         * = va + we + xd + yc + zb + (vb + wa + xe + yd + zc)f + (vc + wb + xa + ye + zd)f2 + (vd + wc + xb + ya + ze)f3 + (ve + wd + xc + yb + za)f4
         */
        return new Simplex4D(r*other.r + f*other.f4 + f2*other.f3 + f3*other.f2 + f4*other.f,
                r*other.f + f*other.r + f2*other.f4 + f3*other.f3 + f4*other.f2,
                r*other.f2 + f*other.f + f2*other.r + f3*other.f4 + f4*other.f3,
                r*other.f3 + f*other.f2 + f2*other.f + f3*other.r + f4*other.f4,
                r*other.f4 + f*other.f3 + f2*other.f2 + f3*other.f + f4*other.r);
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
        Simplex4D normalized = normalize();
        Quadruplex q = basisQuadruplex();

        return new Quadruplex(normalized.r, 0, 0, 0)
                .add(q.multiply(normalized.f))
                .add(q.multiply(q).multiply(normalized.f2))
                .add(q.multiply(q).multiply(q).multiply(normalized.f3))
                .add(q.multiply(q).multiply(q).multiply(q).multiply(normalized.f4));
    }

    public static Quadruplex basisQuadruplex() {
        double sr = sqrt(5 + sqrt(5) - sqrt(5*(5 + 2*sqrt(5))));
        Quadruplex q = new Quadruplex(-0.25,
                -0.25*sr,
                0.25 - (11.0/8.0)*pow(sr,2) + (9.0/20.0)*pow(sr,4) - (1.0/40.0)*pow(sr,6),
                (-23.0/8.0)*sr + 4*pow(sr,3) - (39.0/40.0)*pow(sr,5) + (1.0/20.0)*pow(sr,7));
        return q;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000} + {1,number,0.0000}f + {2,number,0.0000}f\u00b2 + {3,number,0.0000}f\u00b3 + {4,number,0.0000}f\u2074", r, f, f2, f3, f4);
    }
}
