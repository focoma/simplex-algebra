package org.labrys.math;

import static java.lang.Math.*;

public class Triplex extends Number {
    public static final double TAU = 2 * PI;
    public final double r;
    public final double y;
    public final double z;

    public Triplex(double r, double y, double z) {
        this.r = r;
        this.y = y;
        this.z = z;
    }

    public Triplex add(double x, double y, double z) {
        return add(new Triplex(x, y, z));
    }

    public Triplex add(Triplex other) {
        return new Triplex(r + other.r, y + other.y, z + other.z);
    }

    public Triplex multiply(double x, double y, double z) {
        return multiply(new Triplex(x, y, z));
    }

    public Triplex multiply(double scalar) {
        return multiply(scalar, 0, 0);
    }

    public Triplex multiply(Triplex other) {
        /*
         * (a + by + cz)(d + ey + fz)
         * = ad + bf + ce + aey + bdy + cfy + afz + bez + cdz
         */
        return new Triplex(r * other.r + y * other.z + z * other.y,
                r * other.y + y * other.r + z * other.z,
                r * other.z + z * other.r + y * other.y);
    }

    public Triplex conjugate() {
        return new Triplex(r*r - y*z, z*z - r*y, y*y - r*z);
    }

    public double norm() {
        return abs(cbrt(multiply(conjugate()).r));
    }

    public Quaternion quaternionValue() {
        return new Quaternion(0, r, y, z);
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
        if (r != 0 || (y == 0 && z == 0)) {
            builder.append(r);
        }
        if (y != 0) {
            if (r != 0) {
                if (y > 0) {
                    builder.append(" + ").append(y);
                } else {
                    builder.append(" - ").append(-y);
                }
            } else {
                builder.append(y);
            }
            builder.append("x");
        }
        if (z != 0) {
            if (r != 0 || y != 0) {
                if (z > 0) {
                    builder.append(" + ").append(z);
                } else {
                    builder.append(" - ").append(-z);
                }
            } else {
                builder.append(z);
            }
            builder.append("y");
        }

        return builder.toString();
    }
}
