package org.labrys.math;

import org.junit.Test;

import java.text.MessageFormat;

import static java.lang.Math.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final double PHI = (1.0 + sqrt(5))/2.0;
    @Test
    public void testTriplex() {

        System.out.println(new Triplex(2,2,0).multiply(new Simplex3D(0,0,0,-1).triplexValue()));
    }

    @Test
    public void testTriplexDivision() {
        Triplex t = new Triplex(-sqrt(2), 1.0 + sqrt(2), sqrt(2) - 1.0);
        System.out.println(t);
        System.out.println(t.divide(new Triplex(2,2,0)));
    }

    @Test
    public void testConjugate() {
        Triplex triplex = new Simplex3D(0,1,0,0).triplexValue();
        System.out.println(triplex);
        System.out.println(triplex.conjugate());
        System.out.println(triplex.multiply(triplex.conjugate()));
        System.out.println(triplex.norm());
    }

    @Test
    public void testTriplexWithTetragonalAntiprism() {
        Triplex s = new Triplex(-(sqrt(2) + 1.0) / 3.0, (-2.0 + sqrt(2) + sqrt(6)) / 6.0, -(2.0 - sqrt(2) + sqrt(6)) / 6.0);
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
        Triplex triplex = new Simplex3D(0,0,0,0).triplexValue();
        System.out.println(triplex.multiply(triplex.conjugate()));
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
    public void findQuadruplex() {
        caseloop: for (int x = 0; x < 1000000000; x++) {
            double r = -0.25;
            double h = -0.939 - 0.001*random();
            double h2 = 2.0*sqrt(PI)/3.0-1.0;
            double h3 = -sqrt(1-r*r-h*h-h2*h2);
            Quadruplex b = new Quadruplex(r, h, h2, h3);

            Quadruplex q = b;
            boolean e = false;
            Quadruplex s = new Quadruplex(0,0,0,0);
            for (int i = 0; i < 12; i++) {
                if (q.r > 1.5 || q.j > 1.5 || q.k > 1.5 || q.l > 1.5 || q.r < -1.5 || q.j < -1.5 || q.k < -1.5 || q.l < -1.5) {
                    continue caseloop;
                }
                s = s.add(q);
                if (i==4 && q.r > 0.99 && q.r < 1.01 && abs(q.j) < 0.01 && abs(q.k) < 0.01 && abs(q.l) < 0.01) {
                    e = true;
                }
                if (e && i==9 && q.r > 0.999 && q.r < 1.001 && abs(q.j) < 0.01 && abs(q.k) < 0.01 && abs(q.l) < 0.01) {
                    if (MessageFormat.format("{0,number,0.0000000}",abs(s.r)).contains("0.00000")
                            && MessageFormat.format("{0,number,0.0000000}",abs(s.j)).contains("0.00000")
                            && MessageFormat.format("{0,number,0.0000000}",abs(s.k)).contains("0.00000")
                            && MessageFormat.format("{0,number,0.0000000}",abs(s.l)).contains("0.00000")) {
                        System.out.println((i + 1) + " x " + b + " = " + q);
                    }
                }
                q = q.multiply(b);
            }
//            System.out.println(b);
        }
    }

    @Test
    public void testQuadruplexTetrahedron() {
        Quadruplex q = new Quadruplex(0.5, 0, 0.5, -sqrt(2)/2.0);

        System.out.println(q.add(q.multiply(q)).add(q.multiply(q).multiply(q)).add(q.multiply(q).multiply(q).multiply(q)).add(1));

        Quadruplex x = q;
        for (int i = 0; i < 20; i++) {
            System.out.println(x);
            x = x.multiply(q);
        }
    }

    @Test
    public void testQuadruplex5Cell() {
        Quadruplex q = new Quadruplex(-0.25, -0.9393473, 2.0*sqrt(PI)/3.0-1.0, 2.0/PI-PI/4.0);

        System.out.println(q.add(q.multiply(q)).add(q.multiply(q).multiply(q)).add(q.multiply(q).multiply(q).multiply(q)).add(1));

        Quadruplex x = q;
        for (int i = 0; i < 20; i++) {
            System.out.println(x);
            x = x.multiply(q);
        }
    }
}
