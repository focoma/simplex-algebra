package org.labrys.math;

import java.util.Arrays;

public class AlgebraicArray {
    private final double[] terms;

    public AlgebraicArray(double... terms) {
        this.terms = terms;
    }

    public AlgebraicArray add(AlgebraicArray e) {
        return add(e.terms);
    }

    public AlgebraicArray add(double... otherTerms) {
        int resultLength = lcm(terms.length, otherTerms.length);
        double[] sumTerms = new double[resultLength];

        copyOrAdd(terms, sumTerms);
        copyOrAdd(otherTerms, sumTerms);

        return new AlgebraicArray(sumTerms);
    }

    public AlgebraicArray multiply(AlgebraicArray e) {
        return multiply(e.terms);
    }

    public AlgebraicArray multiply(double... otherTerms) {
        int resultLength = lcm(terms.length, otherTerms.length);
        double[] multiplicandTerms = new double[resultLength];
        double[] multiplierTerms = new double[resultLength];
        double[] productTerms = new double[resultLength];

        copyOrAdd(terms, multiplicandTerms);
        copyOrAdd(otherTerms, multiplierTerms);

        for (int i=0; i<resultLength; i++) {
            for (int j=0; j<resultLength; j++) {
                productTerms[(i+j)%resultLength] += Math.pow(-1, (resultLength+1)*((i+j)/resultLength)) * multiplicandTerms[i]*multiplierTerms[j];
            }
        }

        return new AlgebraicArray(productTerms);
    }

    private void copyOrAdd(double[] arr, double[] target) {
        int targetLength = target.length;
        int arrLength = arr.length;

        int b = targetLength / arrLength;
        for (int i = 0; i < arrLength; i++) {
            target[i*b] += Math.pow(-1, (arrLength + targetLength)*i) * arr[i];
        }
    }

    private static int lcm(long a, long b) {
        return (int)(a * (b / gcd(a, b)));
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public String toString() {
        return Arrays.toString(terms);
    }
}
