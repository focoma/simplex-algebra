package org.labrys.math;

public class SplitComplex extends Number {
    private double r;
    private double j;

    public SplitComplex() {
    }

    public SplitComplex(double r, double j) {
        this.r = r;
        this.j = j;
    }

    public SplitComplex add(double x, double y) {
        return add(new SplitComplex(x, y));
    }

    public SplitComplex add(SplitComplex other) {
        return new SplitComplex(r + other.r, j + other.j);
    }

    public SplitComplex multiply(double x, double y) {
        return multiply(new SplitComplex(x, y));
    }

    public SplitComplex multiply(SplitComplex other) {
        SplitComplex product = new SplitComplex(r * other.r + j * other.j, r * other.j + j * other.r);

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (r != 0 || j == 0) {
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

        return builder.toString();
    }
}
