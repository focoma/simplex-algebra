package org.labrys.math;

import org.junit.Test;

import java.text.MessageFormat;

import static java.lang.Math.*;
import static java.lang.Math.PI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final double PHI = (1.0 + sqrt(5))/2.0;
    @Test
    public void testTriplex() {

//        Triplex t = Triplex.exp(new Triplex(0, 2*PI/(3*sqrt(3)), -2*PI/(3*sqrt(3))));
        Triplex t = Triplex.exp(new Simplex2D(log(5), 0, 0).triplexValue())
                .multiply(Triplex.exp(new Simplex2D(0, log(5), 0).triplexValue()))
                .multiply(Triplex.exp(new Simplex2D(0, 0, log(5)).triplexValue()));
        Triplex v = t;
        for (int i=0; i<10; i++) {
            System.out.println(v + " norm:" + v.euclideanNorm());
            v = v.multiply(t);
        }
    }

    @Test
    public void testTessarine() {
        Tessarine t = new Tessarine(0.5, -0.5, 0.5, 0.5);
//        t = Tessarine.exp(t);

        Tessarine v = t;
        for (int i=0; i<5; i++) {
            System.out.println(v);
            v = v.multiply(t);
        }

        Quadruplex q = t.quadruplexValue();
        Quadruplex v2 = q;
        for (int i=0; i<5; i++) {
            System.out.println(v2);
            v2 = v2.multiply(q);
        }

        v = q.tessarineValue();
        for (int i=0; i<5; i++) {
            System.out.println(v);
            v = v.multiply(t);
        }
    }

    @Test
    public void testTriplexDivision() {
        Triplex t = new Triplex(-sqrt(2), 1.0 + sqrt(2), sqrt(2) - 1.0);
        System.out.println(t);
        System.out.println(t.divide(new Triplex(2,2,0)));
    }

    @Test
    public void testConjugate() {
        Triplex triplex = new Triplex(0,0.5,0.5);
        System.out.println(triplex);
        System.out.println(triplex.conjugate());
        System.out.println(triplex.conjugate().conjugate());
        System.out.println(triplex.conjugate().conjugate().conjugate());
        System.out.println(triplex.conjugate().conjugate().conjugate().conjugate());
        System.out.println(triplex.multiply(triplex.conjugate()));
        System.out.println(triplex.modulus());
    }

    @Test
    public void testTriplexWithTetragonalAntiprism() {
        Triplex s = new Triplex(0.804, (-2.0 + sqrt(2) + sqrt(6)) / 6.0, -(2.0 - sqrt(2) + sqrt(6)) / 6.0);
//        Triplex s = new Triplex(1.0/3.0,1.0/3.0,1.0/3.0);

        Triplex t = s;
        for (int i = 0; i < 8; i++) {
            System.out.println(t);
            t = t.multiply(s);
        }
        System.out.println();

        Triplex c = new Simplex3D(0,-1,0,0).triplexValue();
        t = c;
        for (int i = 0; i < 4; i++) {
            System.out.println(t);
            t = t.multiply(c);
        }
    }

    @Test
    public void test3DMultiplication() {
        Simplex3D b = new Simplex3D(0, 1, 0, 0);
        Simplex3D p = new Simplex3D(0, 0, 1, 0);
        Simplex3D z = new Simplex3D(0, 0, 0.45849664917601007033647, -0.3408767092225170);
        Simplex3D y = new Simplex3D(sqrt(2)*2.0, 0, 3, 0);
        Simplex3D h = new Simplex3D(sqrt(2.0)/2.0, sqrt(2.0)/2.0, 0, 0);
        Simplex3D s = new Simplex3D(0, 0, 0, -1);
        Simplex3D n = new Simplex3D(-1, 0, 0, 0);
        Simplex3D np = new Simplex3D(0, 0, -1, 0);
        Simplex3D v = p;
        System.out.println(b.multiply(-1).multiply(v));
        System.out.println(b.multiply(-1).multiply(v).multiply(v).normalize());
        System.out.println(b.multiply(-1).multiply(v).multiply(v).multiply(v).normalize());
        System.out.println(b.multiply(-1).multiply(v).multiply(v).multiply(v).multiply(v).normalize());
        System.out.println(b.multiply(-1).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).normalize());
        System.out.println(b.multiply(-1).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).normalize());
        System.out.println(b.multiply(-1).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).normalize());
        System.out.println(b.multiply(-1).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).multiply(v).normalize());
    }

    @Test
    public void testConjugation() {
        Quadruplex q = new Quadruplex(1,2,3,5);
        System.out.println(q.multiply(1,-2,-3,-5));
    }

    @Test
    public void test3DAddition() {
        Simplex3D r = new Simplex3D(1, 1, 1, 1);

        System.out.println(r.normalize());
    }

    @Test
    public void testEqualityWithComplexUnits()
    {
        double radians = 1;

        Simplex2D unitVector = Simplex2D.generateUnit(radians);
        System.out.println(unitVector);
        System.out.println(unitVector.multiply(3));
        System.out.println(unitVector.normalize());

        Complex complexUnitVector = Complex.generateUnit(radians);
        assertEquals(complexUnitVector.r,
                unitVector.complexValue().r,
                0.000000000000001);

        assertEquals(complexUnitVector.i,
                unitVector.complexValue().i,
                0.000000000000001);

        double scalar = 3.0;
        Quaternion scaledComplexUnit = complexUnitVector.multiply(scalar);
        assertEquals(scaledComplexUnit.r,
                unitVector.multiply(scalar).complexValue().r,
                0.00000000000001);

        assertEquals(scaledComplexUnit.i,
                unitVector.multiply(scalar).complexValue().i,
                0.00000000000001);
    }

    @Test
    public void test4Rational() {
        Triplex t = new Triplex(PI/sqrt(3), 0, 0)
                .multiply(Triplex.exp(new Simplex3D(0, log(2*PI/sqrt(3)), 0, 0).triplexValue()))
                .multiply(Triplex.exp(new Simplex3D(0, 0, log(PI/sqrt(3)), 0).triplexValue()))
                .multiply(Triplex.exp(new Simplex3D(0, 0, 0, log(2*PI/sqrt(3))).triplexValue()));

        Triplex v = t;
        for (int i=0; i<20; i++) {
            System.out.println(v);
            v = v.multiply(t);
        }
    }

    @Test
    public void testQuadruplexConjugate() {
        Quadruplex q1 = new Quadruplex(2, 0, 3, 0);
        Complex q2 = q1.complexValue();
        System.out.println(q1.conjugate());
        System.out.println(q1.conjugate().conjugate());
        System.out.println(q2.conjugate());
        System.out.println(q2.conjugate().conjugate());
        System.out.println(q1.multiply(q1.conjugate()));
        System.out.println(q2.multiply(q2.conjugate()));
    }

    @Test
    public void testGroups() {
        Quadruplex s = new Quadruplex(0.5,0, 0.5, sqrt(2.0)/2.0);

        Quadruplex c = s;
        Quadruplex sum = new Quadruplex(0,0,0,0);
        for (int i=0; i<4; i++) {
            System.out.println(c + " norm:" + c.euclideanNorm());
            sum = sum.add(c);
            System.out.println("sum:" + sum + ", norm:" + sum.euclideanNorm());
            c = c.multiply(s);
        }
        Quadruplex center = sum.multiply(0.25);
        System.out.println("center:" + center + ", norm:" + center.euclideanNorm());
    }

    @Test
    public void testQuadruplex() {
        Quadruplex q = new Quadruplex(0, 1/sqrt(2), 0, -1/sqrt(2));
        System.out.println(q + " " + q.euclideanNorm());
//        q = Quadruplex.exp(q);
        Quadruplex c = q;
        for (int i = 0; i < 8; i++) {
            System.out.println(c + " " + c.euclideanNorm());
            c = c.multiply(q);
        }
    }

    @Test
    public void testTriplexUnitVectors() {
        Triplex q = new Triplex(0, 1.0/sqrt(3), -1.0/sqrt(3));
        System.out.println(q.euclideanNorm());
        Triplex c = q;
        for (int i = 0; i < 10; i++) {
            System.out.println(c);
            c = c.multiply(q);
        }
    }

    @Test
    public void testTrirationals() {
        Trirational tc1 = new Trirational(2,3,4).add(3,4,2);

        System.out.println(tc1);
        System.out.println(tc1.complexValue().trirationalValue());
    }

    @Test
    public void test4Rationals() {
        System.out.println(Triplex.exp(new Simplex3D(log(2), 0, 0, 0).triplexValue())
                .multiply(Triplex.exp(new Simplex3D(0, log(5), 0, 0).triplexValue()))
                .multiply(Triplex.exp(new Simplex3D(0, 0, log(2), 0).triplexValue()))
                .multiply(Triplex.exp(new Simplex3D(0, 0, 0, log(5)).triplexValue())));
    }

    @Test
    public void test5Rationals() {
        Quadruplex numerator = new Quadruplex(13, 0, 0, 0);
        Quadruplex x1 = numerator
                .multiply(Quadruplex.exp(new Simplex4D(0, log(3), 0, 0, 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, log(5), 0, 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, log(7), 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, 0, log(11)).quadruplexValue()));
        Quadruplex x1sum = numerator.multiply(2)
                .multiply(Quadruplex.exp(new Simplex4D(0, log(3), 0, 0, 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, log(5), 0, 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, log(7), 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, 0, log(11)).quadruplexValue()));
        Quadruplex x1inverse = new Quadruplex(1.0/13.0, 0, 0, 0)
                .multiply(Quadruplex.exp(new Simplex4D(0, log(1.0/3.0), 0, 0, 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, log(1.0/5.0), 0, 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, log(1.0/7.0), 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, 0, log(1.0/11.0)).quadruplexValue()));
        Quadruplex x1product = numerator.multiply(numerator)
                .multiply(Quadruplex.exp(new Simplex4D(0, log(9), 0, 0, 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, log(25), 0, 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, log(49), 0).quadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, 0, log(121)).quadruplexValue()));
        Quadruplex x2 = numerator
                .multiply(Quadruplex.exp(new Simplex4D(0, log(3), 0, 0, 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, log(5), 0, 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, log(7), 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, 0, log(11)).altQuadruplexValue()));
        Quadruplex x2sum = numerator.multiply(2)
                .multiply(Quadruplex.exp(new Simplex4D(0, log(3), 0, 0, 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, log(5), 0, 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, log(7), 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, 0, log(11)).altQuadruplexValue()));
        Quadruplex x2inverse = new Quadruplex(1.0/13.0, 0, 0, 0)
                .multiply(Quadruplex.exp(new Simplex4D(0, log(1.0/3.0), 0, 0, 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, log(1.0/5.0), 0, 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, log(1.0/7.0), 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, 0, log(1.0/11.0)).altQuadruplexValue()));
        Quadruplex x2product = numerator.multiply(numerator)
                .multiply(Quadruplex.exp(new Simplex4D(0, log(9), 0, 0, 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, log(25), 0, 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, log(49), 0).altQuadruplexValue()))
                .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, 0, log(121)).altQuadruplexValue()));


        System.out.println(x1);
        System.out.println(x1sum);
        System.out.println(x1.add(x1));
        System.out.println(x1product);
        System.out.println(x1.multiply(x1));
        System.out.println(x1inverse);
        System.out.println(x1.conjugate().multiply(1/x1.norm()));

        System.out.println();

        System.out.println(x2);
        System.out.println(x2sum);
        System.out.println(x2.add(x2));
        System.out.println(x2product);
        System.out.println(x2.multiply(x2));
        System.out.println(x2inverse);
        System.out.println(x2.conjugate().multiply(1/x2.norm()));
    }
}
