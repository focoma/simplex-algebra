package org.labrys.math;

public class Ternion extends Number {
    public final double r;
    public final double i;
    public final double j;

    public Ternion() {
        r = 0;
        i = 0;
        j = 0;
    }

    public Ternion(double r, double i, double j) {
        this.r = r;
        this.i = i;
        this.j = j;
    }

    public Ternion add(double x, double y, double z) {
        return add(new Ternion(x, y, z));
    }

    public Ternion add(Ternion other) {
        return new Ternion(r + other.r, i + other.i, j + other.j);
    }

    public Ternion add(double other) {
        return add(new Ternion(other, 0, 0));
    }

    public Ternion multiply(double x, double y) {
        return multiply(x, y, 0);
    }

    public Ternion multiply(double x, double y, double z) {
        return multiply(new Ternion(x, y, z));
    }

    public Ternion multiply(Ternion other) {
        /*
         * (x + yi + zj)(s + ci + hj)
         * = xs + xci + xhj + ysi + ycii + yhij + zsj + zcji + zhjj
         *
         * Commutative:
         * = xs - yc + (xc + ys - zh)i + (xh + yh + zs + zc)j
         *
         * Non-Commutative:
         * = xs - yc + xci + ysi + xhj + yhij + zsj + zcji + zhjj
         *
         */

        return new Ternion(
                r * other.r - i * other.i,
                r * other.i + i * other.r - j * other.j,
                r * other.j + j * other.r + i * other.j + j * other.i);
    }

    public Ternion conjugate() {
        return new Ternion(r, -i, -j);
    }

    public Ternion conjugate2() {
        return new Ternion(r, i, -j);
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
        if (r != 0 || (i == 0 && j == 0)) {
            builder.append(r);
        }
        if (i != 0) {
            if (r != 0) {
                if (i > 0) {
                    builder.append(" + ").append(i);
                } else {
                    builder.append(" - ").append(-i);
                }
            } else {
                builder.append(i);
            }
            builder.append("i");
        }
        if (j != 0) {
            if (r != 0 || i != 0) {
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

        return builder.toString();
    }
}
