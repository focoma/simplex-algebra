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

    public Triplex add(double scalar) {
        return new Triplex(r + scalar, j, k);
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
        return new Triplex(r*r - j*k, k*k - r*j, j*j - r*k);
    }

    public double modulus() {
        return abs(cbrt(multiply(conjugate()).r));
    }

    public double norm() {
        return multiply(conjugate()).r;
    }

    public double euclideanNorm() {
        return sqrt(pow(r,2) + pow(j,2) + pow(k,2));
    }

    public Simplex3D simplexify() {
        return new Simplex3D(r,0,0,0)
                .add(new Simplex3D(3*sqrt(3)*((sqrt(3)-3)/9)/4, -sqrt(3)/2, 3*sqrt(3)*((sqrt(3)-1)/3)/4, 0).multiply(j))
                .add(new Simplex3D(3*sqrt(3)*((sqrt(3)+3)/9)/4, sqrt(3)/2, 3*sqrt(3)*((sqrt(3)+1)/3)/4, 0).multiply(k))
                .normalize();
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

    public static Triplex exp(Triplex t) {
        return new Triplex(A(t.k), C(t.k), B(t.k)).multiply(A(t.j), B(t.j), C(t.j)).multiply(Math.exp(t.r));
    }

    public static Triplex ln(Triplex t) {
        double a1 = log((t.r + t.j + t.k) / sqrt(t.r*t.r + t.j*t.j + t.k*t.k - t.r*t.j - t.r*t.k - t.j*t.k));
        double a2 = sqrt(3) * atan(sqrt(3) * (t.j - t.k) / (2*t.r - t.j - t.k));

        return new Triplex(log(pow(t.r,3) + pow(t.j,3) + pow(t.k,3) - 3*t.r*t.j*t.k),
                (a1 + a2),
                (a1 - a2)).multiply(1/3.0);
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
