package org.labrys.math;

import org.junit.Test;

import java.text.MessageFormat;

import static java.lang.Math.*;
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
        System.out.println(triplex.modulus());
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
    public void findQuadruplex() {
        caseloop: for (int x = 0; x < 1000000000; x++) {
            double r = random()*((int)(random()*100)%2==1?-1:1);
            double h = sqrt(1-r*r)*random()*((int)(random()*100)%2==1?-1:1);
            double h2 = sqrt(1-r*r-h*h)*random()*((int)(random()*100)%2==1?-1:1);
            double h3 = sqrt(1-r*r-h*h-h2*h2)*random()*((int)(random()*100)%2==1?-1:1);
            Quadruplex b = new Quadruplex(r, h, h2, h3);

            Quadruplex q = b;
            boolean e = false;
            Quadruplex s = new Quadruplex(0,0,0,0);
            for (int i = 0; i < 20; i++) {
                if (q.r > 1.5 || q.h > 1.5 || q.i > 1.5 || q.ih > 1.5 || q.r < -1.5 || q.h < -1.5 || q.i < -1.5 || q.ih < -1.5) {
                    continue caseloop;
                }
                s = s.add(q);
                if (i==15 && q.r > 0.999 && q.r < 1.001 && abs(q.h) < 0.01 && abs(q.i) < 0.01 && abs(q.ih) < 0.01) {
                    if (MessageFormat.format("{0,number,0.0000000}",abs(s.r)).contains("0.000")
                            && MessageFormat.format("{0,number,0.0000000}",abs(s.h)).contains("0.000")
                            && MessageFormat.format("{0,number,0.0000000}",abs(s.i)).contains("0.000")
                            && MessageFormat.format("{0,number,0.0000000}",abs(s.ih)).contains("0.000")) {
                        System.out.println((i + 1) + " x " + b + " = " + q);
                    }
                }
                q = q.multiply(b);
            }
//            System.out.println(b);
        }
    }

    @Test
    public void testQuadruplexFiveCell() {
        Quadruplex q = new Simplex4D(0,1,0,0,0).quadruplexValue();

        System.out.println(q.add(q.multiply(q)).add(q.multiply(q).multiply(q)).add(q.multiply(q).multiply(q).multiply(q)).add(1));

        Quadruplex x = q;
        for (int i = 0; i < 5; i++) {
            System.out.println(x.conjugate());
            x = x.multiply(q);
        }
    }

    @Test
    public void testQuadruplexConjugate() {
        for (int i=1; i<20; i*=2) {
            Quadruplex sum = new Quadruplex(0,0,0,0);
            for (int j=1; j<20; j*=2) {
                Quadruplex dividend = new Simplex4D(j&1, (j&2)/2.0, (j&4)/4.0, (j&8)/8.0, (j&16)/16.0).quadruplexValue();
                Quadruplex divisor = new Simplex4D(i&1, (i&2)/2.0, (i&4)/4.0, (i&8)/8.0, (i&16)/16.0).quadruplexValue();
                Quadruplex divisorConjugate = divisor.conjugate();
                Quadruplex quotient = dividend.multiply(divisorConjugate).multiply(1 / divisor.norm());
                System.out.println(dividend + "/" + divisor + "=" + quotient);
                if (i==j) {
                    assertEquals(1, quotient.r, 0.001);
                    assertEquals(0, quotient.h, 0.001);
                    assertEquals(0, quotient.i, 0.001);
                    assertEquals(0, quotient.ih, 0.001);
                } else {
                    assertEquals(-0.25, quotient.r, 0.001);
                }
                sum = sum.add(quotient);
            }
            assertEquals(0, sum.r, 0.001);
            assertEquals(0, sum.h, 0.001);
            assertEquals(0, sum.i, 0.001);
            assertEquals(0, sum.ih, 0.001);
            System.out.println();
        }
    }

    @Test
    public void testTriplexExp() {
//        for (int j=0; j<10000000; j++) {
        Triplex c = new Triplex(0,2*PI/(6*sqrt(3)), -2*PI/(6*sqrt(3)));
//        Simplex3D s = new Simplex3D(0, 0, -2 * PI * random(), 0);
        Triplex es = Triplex.exp(c);
        Triplex nes = es.multiply(1);
        Triplex x = nes;
        for (int i = 1; i < 9; i++) {
            System.out.println(x);
            x = x.multiply(nes);
        }
    }

    @Test
    public void testTrirational() {
        Trirational x = new Trirational(1,2,3);
        Trirational y = new Trirational(5,7,11);
        Trirational z = new Trirational(13,17,19);

        System.out.println(x.add(y).multiply(z));
        System.out.println(x.multiply(z).add(y.multiply(z)));
        System.out.println(z.multiply(x.add(y)));
        System.out.println(z.multiply(x).add(z.multiply(y)));
    }

    @Test
    public void test4Rationals() {
        System.out.println(Triplex.exp(new Simplex3D(log(2), 0, 0, 0).triplexValue())
                .multiply(Triplex.exp(new Simplex3D(0, log(5), 0, 0).triplexValue()))
                .multiply(Triplex.exp(new Simplex3D(0, 0, log(5), 0).triplexValue()))
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
        System.out.println(x2);
        System.out.println(x2sum);
        System.out.println(x2.add(x2));
        System.out.println(x2product);
        System.out.println(x2.multiply(x2));
    }
}
