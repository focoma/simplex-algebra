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
    public void testQuadruplexTetrahedron() {
        Quadruplex q = new Simplex4D(0,1,0,0,0).quadruplexValue();

        System.out.println(q.add(q.multiply(q)).add(q.multiply(q).multiply(q)).add(q.multiply(q).multiply(q).multiply(q)).add(1));

        Quadruplex x = q;
        for (int i = 0; i < 10; i++) {
            System.out.println(x);
            x = x.multiply(q);
        }
    }

    @Test
    public void testQuadruplexExp() {
        Quadruplex q = new Quadruplex(0, -PI/2, PI/2, -PI/2);
//        Quadruplex q = new Quadruplex(-0.25, -0.14877801734965796128, -0.1816356320013402215, -0.939347432391752794);
//        Quadruplex q = new Quadruplex(-0.25, 0.52372049461429936568, 0.769420884293813351, -0.266848920427795467);

//        System.out.println(q.add(q.multiply(q)).add(q.multiply(q).multiply(q)).add(q.multiply(q).multiply(q).multiply(q)).add(1));

        Quadruplex exp = Quadruplex.exp(q);
        Quadruplex x = exp;
        for (int i = 0; i < 40; i++) {
            System.out.println(x);
            x = x.multiply(exp);
        }
    }

    @Test
    public void testTriplexExp() {
//        Triplex s = new Triplex(0,2*PI/(4*sqrt(3)), -2*PI/(4*sqrt(3)));
        Triplex s = new Simplex3D(0,1, 0,0).triplexValue();
        System.out.println(s);

        Triplex es = Triplex.exp(s);
        Triplex nes = es.multiply(1);
        Triplex x = nes;
        for (int i=0; i<100; i++) {
            System.out.println(x);
            x = x.multiply(nes);
        }

//        System.out.println(Triplex.exp(s));
//        for (int z=0; z<10000000; z++) {
//            try {
//                double r = random() * ((int) (random() * 100) % 2 == 1 ? -1 : 1) * ((int) (random() * 100) % 3 == 1 ? 0 : 1);
//                double h = (r < 0.5 && (int) (random() * 100) % 4 == 1 ? r : sqrt(1 - r * r) * random()) * ((int) (random() * 100) % 2 == 1 ? -1 : 1) * ((int) (random() * 100) % 3 == 1 ? 0 : 1);
////            double h2 = sqrt(1 - r * r - h * h);
//                double h2 = (h < 0.5 && (int) (random() * 100) % 4 == 1 ? h : sqrt(1 - r * r - h * h) * random()) * ((int) (random() * 100) % 2 == 1 ? -1 : 1) * ((int) (random() * 100) % 3 == 1 ? 0 : 1);
//                double h3 = sqrt(1 - r * r - h * h - h2 * h2);
//
//                Simplex3D cp = new Simplex3D((2 * PI) * r, (2 * PI) * h, (2 * PI) * h3, (2 * PI) * h2);
//                Triplex c = cp.triplexValue();
////            Triplex c = new Triplex(r, h, h2);
//
//                Triplex x = Triplex.exp(c);
//                if (x.r > -0.3345 && x.r < -0.3312 && x.j < 1 && x.k < 1) {
//                    Triplex t = x;
//                    for (int i = 1; i < 11; i++) {
//                        if (i > 2 && abs(t.r) > 0.99 && abs(t.j) < 0.1 && abs(t.k) < 0.1) {
////                        System.out.println("Simplex: " + cp);
//                            System.out.println("Triplex: " + c);
//                            System.out.println("Exponent: " + Triplex.exp(c));
//                            System.out.println(i + ": " + t);
//                            t = t.multiply(x);
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
    }

    @Test
    public void testComplexExp() {
        Complex s = Complex.exp(new Simplex2D(0, 1/sqrt(3), -1/sqrt(3)).complexValue());

        Complex t = s;
        for (int i = 0; i < 20; i++) {
            System.out.println(t);
            t = t.multiply(s).complexValue();
        }
        System.out.println();
    }

    @Test
    public void testTrirational() {
        Simplex2D x = new Simplex2D(0, 1, 0);
        Complex c = x.complexValue();
        Quadruplex q = x.quadruplexValue();
        System.out.println(x.multiply(x.multiply(x)));
        System.out.println(c.multiply(c.multiply(c)));
        System.out.println(q.multiply(q.multiply(q)));
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
        for (int i=0; i<10000000; i++) {
            double a = random()*((int)(random()*100)%3==1?-1:1)*((int)(random()*100)%9==1?0:1);
            double b = random()*((int)(random()*100)%9==1?0:1);
            double c = random()*((int)(random()*100)%9==1?0:1);
            double d = random()*((int)(random()*100)%9==1?0:1);
            double e = random()*((int)(random()*100)%9==1?0:1);
            Quadruplex x1 = new Quadruplex(a, 0, 0, 0)
                    .multiply(Quadruplex.exp(new Simplex4D(0, log(b), 0, 0, 0).quadruplexValue()))
                    .multiply(Quadruplex.exp(new Simplex4D(0, 0, log(c), 0, 0).quadruplexValue()))
                    .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, log(d), 0).quadruplexValue()))
                    .multiply(Quadruplex.exp(new Simplex4D(0, 0, 0, 0, log(e)).quadruplexValue()));
            if (abs(x1.h) < 0.0001 && abs(x1.ih) < 0.0001 && abs(x1.r) > 0.1 && abs(x1.i) > 0.1) {
                System.out.println(x1 + ": " + a + "\u25B6" + b + "\u25B6" + c + "\u25B6" + d + "\u25B6" + e);
            }
        }

//        System.out.println(new Trirational(2,5, 3).add(2,5,5).quadruplexValue());
    }
}
