package org.labrys.math;

public abstract class SqrtNegOne {
    private final double[] e;


    public SqrtNegOne(double... e) {
        this.e = e;

        double s = 0;
        for (double im : e) {
            s += im*im;
        }
        if (Math.sqrt(s) != 1) {
            throw new IllegalArgumentException("Not a unit.");
        }
    }
}
