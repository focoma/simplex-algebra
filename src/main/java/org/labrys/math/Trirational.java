package org.labrys.math;

import java.text.MessageFormat;

import static java.lang.Math.*;

public class Trirational {
    public double r;
    public double d1;
    public double d2;

    public Trirational(double r, double d1, double d2) {
        this.r = r;
        this.d1 = d1;
        this.d2 = d2;
    }

    public Trirational multiply(double a, double b, double c) {
        return multiply(new Trirational(a,b,c));
    }

    public Trirational multiply(Trirational other) {
        return new Trirational(r*other.r,d1*other.d1,d2*other.d2);
    }

    public Trirational multiply(double scalar) {
        return new Trirational(r*scalar,d1,d2);
    }

    public Trirational divide(Trirational other) {
        return new Trirational(r/other.r,d1/other.d1,d2/other.d2);
    }

    public Trirational add(double a, double b, double c) {
        return add(new Trirational(a,b,c));
    }

    public Trirational add(Trirational other) {
        double newR = sqrt(r*r + other.r*other.r*d1*d2/(other.d1*other.d2) + (2*r*other.r*sqrt(d1*d2/(other.d1*other.d2))) * cos(log(other.d1*d2/(other.d2*d1))*sqrt(3)/2));
        double newTheta = atan2((other.r/sqrt(other.d1*other.d2))*sin(log(other.d1*d2/(other.d2*d1))*sqrt(3)/2), r/sqrt(d1*d2) + (other.r/sqrt(other.d1*other.d2))*cos(log(other.d1*d2/(other.d2*d1))*sqrt(3)/2));

        return new Trirational(newR, d1*Math.exp(newTheta/sqrt(3)), d2*Math.exp(-newTheta/sqrt(3)));

//        return complexValue().add(other.complexValue()).trirationalValue();
    }

    public Complex complexValue() {
        return new Complex(r,0)
                .multiply(Complex.exp(new Simplex2D(0,log(d1),0).complexValue()))
                .multiply(Complex.exp(new Simplex2D(0,0,log(d2)).complexValue()));
    }

    public Triplex triplexValue() {
        return Triplex.exp(new Simplex2D(log(r),0,0).triplexValue())
                .multiply(Triplex.exp(new Simplex2D(0,log(d1),0).triplexValue()))
                .multiply(Triplex.exp(new Simplex2D(0,0,log(d2)).triplexValue()));
    }

    public Quadruplex quadruplexValue() {
        return new Quadruplex(r,0,0,0)
                .multiply(Quadruplex.exp(new Simplex2D(0,log(d1),0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex2D(0,0,log(d2)).quadruplexValue()));
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000000}\u25B6{1,number,0.0000000}\u25B6{2,number,0.0000000}", r, d1, d2);
    }
}
