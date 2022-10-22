package org.labrys.math;

import java.text.MessageFormat;

import static java.lang.Math.*;

public class Quadruplex extends Number {
    public static final double TAU = 2 * PI;
    public final double r;
    public final double h;
    public final double i;

    public final double ih;

    public Quadruplex(double r, double x, double y, double z) {
        this.r = r;
        this.h = x;
        this.i = y;
        this.ih = z;
    }

    public Quadruplex add(double r, double x, double y, double z) {
        return add(new Quadruplex(r, x, y, z));
    }

    public Quadruplex add(double other) {
        return add(other,0,0,0);
    }

    public Quadruplex add(Quadruplex other) {
        return new Quadruplex(r + other.r, h + other.h, i + other.i, ih + other.ih);
    }

    public Quadruplex multiply(double r, double x, double y, double z) {
        return multiply(new Quadruplex(r, x, y, z));
    }

    public Quadruplex multiply(double scalar) {
        return multiply(scalar, 0, 0, 0);
    }

    public Quadruplex multiply(Quadruplex other) {
        /*
         * (a + bj + ck + dl)(e + fj + gk + hl)
         * = ae + afj + agk + ahl + bej + bfk + bgl - bh + cek + cfl - cg - chj + del - df - dgj - dhk
         * = ae - bh - cg - df + (af + be - ch - dg)j + (ag + bf + ce - dh)k + (ah + bg + cf + de)l
         */
        return new Quadruplex(r * other.r - h * other.ih - i * other.i - ih * other.h,
                r * other.h + h * other.r - i * other.ih - ih * other.i,
                r * other.i + i * other.r + h * other.h - ih * other.ih,
                r * other.ih + ih * other.r + h * other.i + i * other.h);
    }

    public static Quadruplex exp(Quadruplex q) {
        return new Quadruplex(A(q.ih), D(q.ih), -C(q.ih), B(q.ih))
                .multiply(cos(q.i), 0, sin(q.i), 0)
                .multiply(A(q.h), B(q.h), C(q.h), D(q.h))
                .multiply(Math.exp(q.r));
    }

    public static double A(double a) {
        return cos(a/sqrt(2))*cosh(a/sqrt(2));
    }

    public static double B(double b) {
        return (cos(b/sqrt(2))*sinh(b/sqrt(2)) + sin(b/sqrt(2))*cosh(b/sqrt(2)))/sqrt(2);
    }

    public static double C(double c) {
        return sin(c/sqrt(2))*sinh(c/sqrt(2));
    }

    public static double D(double d) {
        return (sin(d/sqrt(2))*cosh(d/sqrt(2)) - cos(d/sqrt(2))*sinh(d/sqrt(2)))/sqrt(2);
    }

    public Quadruplex conjugate() {
        return new Quadruplex(pow(r,3)+r*(2*h*ih+pow(i,2))+i*(pow(ih,2)-pow(h,2)),
                -(pow(r,2)*h+2*r*i*ih+pow(h,2)*ih-h*pow(i,2)+pow(ih,3)),
                -pow(r,2)*i+r*(pow(h,2)-pow(ih,2))+2*h*i*ih-pow(i,3),
                -(ih*(pow(r,2)-pow(i,2))+h*(pow(ih,2)-2*r*i)+pow(h,3)));
    }

    public double norm() {
        return multiply(conjugate()).r;
    }

    public double modulus() {
        return pow(abs(norm()),0.25);
    }

    public Quaternion quaternionValue() {
        return new Quaternion(r, h, i, ih);
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

    public Complex complexValue() {
        return new Complex(r, i);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,0.0000000} + {1,number,0.0000000}h + {2,number,0.0000000}i + {3,number,0.0000000}ih", r, h, i, ih);
    }
}
