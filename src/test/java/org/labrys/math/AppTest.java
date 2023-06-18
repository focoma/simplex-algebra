package org.labrys.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.labrys.math.Triplex.TAU;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final double PHI = (1.0 + sqrt(5))/2.0;
    @Test
    public void testTriplex() {
        Triplex t1 = Triplex.exp(new Triplex(0,1/sqrt(3), -1/sqrt(3)).multiply(2*TAU/5));

        System.out.println(t1);
        System.out.println(t1.multiply(2));

        Triplex t2 = Triplex.exp(new Triplex(1/3.0, 1/3.0, 1/3.0).multiply(1.5));

        System.out.println(t2);

        System.out.println();
        System.out.println(t1.multiply(t2));
        System.out.println(t1.multiply(t2).multiply(2));
    }

    @Test
    public void testIcosians() {
        Quaternion a = new Quaternion(0.5,0.5,0.5,0.5);
        Quaternion b = new Quaternion(0,1,1/PHI,PHI);

        System.out.println(a);
        System.out.println(b);

        System.out.println(a.multiply(b));
        System.out.println(b.multiply(a));
    }

    @Test
    public void testTriplexDiagonalBasis() {
        Triplex d1 = new Triplex(2/3.0,-1/3.0, -1/3.0);
        Triplex d2 = new Triplex(0,1/sqrt(3), -1/sqrt(3));
        Triplex d3 = new Triplex(1/3.0,1/3.0, 1/3.0);

        System.out.println(new Triplex(11,7,17).multiply(d1));
    }

    @Test
    public void testSimplex3DInverse() {
        System.out.println(new Simplex3D(2, 3, 5, 0).triplexValue());

        double x = -2/3.0, y = 0.6012825257644563, z = 4.0653841409022125;
        System.out.println(new Simplex3D(x,0,0,0)
                .add(new Simplex3D(3*sqrt(3)*((sqrt(3)-3)/9)/4, -sqrt(3)/2, 3*sqrt(3)*((sqrt(3)-1)/3)/4, 0).multiply(y))
                .add(new Simplex3D(3*sqrt(3)*((sqrt(3)+3)/9)/4, sqrt(3)/2, 3*sqrt(3)*((sqrt(3)+1)/3)/4, 0).multiply(z)));
    }

    @Test
    public void testSimplex3D() {
        Simplex3D t = new Simplex3D(1, 0, 0, 0).add(0, 0, 1, 0).multiply(0.5);

        Simplex3D v = t;
        for (int i=0; i<8; i++) {
            System.out.println(v + " norm: " + v.triplexValue().euclideanNorm());

            v = v.multiply(t);
        }
    }

    @Test
    public void testTriplexTo4Rational() {
        Simplex3D s = new Simplex3D(log(2), log(3), log(5), 1);
        System.out.println(s.normalize());
        System.out.println(s.triplexValue());

        Triplex e = Triplex.exp(s.triplexValue());
        System.out.println(e);

        Triplex l = Triplex.ln(e);
        System.out.println(l);
        System.out.println(l.simplexify());
    }

    @Test
    public void testTessarine() {

        Quadruplex t1 = new Tessarine(0,sqrt(2)/2,sqrt(2)/2,0).quadruplexValue();
        Quadruplex t2 = t1.multiply(t1);
        Quadruplex t3 = t2.multiply(t1);

        System.out.println(t1.multiply(0.5).add(t2.multiply(1/sqrt(2))).add(t3.multiply(-0.5)));
    }

    @Test
    public void testTriplexLog() {
        Triplex t1 = Triplex.exp(new Triplex(2/3.0,-1/3.0,-1/3.0).multiply(1.2))
                .multiply(Triplex.exp(new Triplex(0,1/sqrt(3),-1/sqrt(3)).multiply(sin(2*PI/3))))
                .multiply(Triplex.exp(new Triplex(2/3.0,-1/3.0,-1/3.0).multiply(1.3)));

        Triplex t2 = Triplex.exp(new Triplex(2/3.0,-1/3.0,-1/3.0).multiply(1.2))
                .multiply(Triplex.exp(new Triplex(1/3.0,1/3.0,1/3.0).multiply(1.3)));

        Triplex t3 = Triplex.exp(new Triplex(1/3.0,1/3.0,1/3.0).multiply(1.3));

        System.out.println(new Triplex(2/3.0,-1/3.0,-1/3.0).add(0,1/sqrt(3),-1/sqrt(3)).add(1/3.0,1/3.0,1/3.0));
    }

    @Test
    public void testSimplex5D() {
        double c = sqrt(3*(5 + 2*sqrt(5)));
        Quintuplex s2 = new Quintuplex(-1/5.0,
                (3+c)/10.0,
                -3/5.0 + 3*pow(3+c,2)/20.0 - pow(3+c,3)/60.0,
                6/5.0 - 3*pow(3+c,2)/20.0 + pow(3+c,3)/60.0,
                3/5.0 + (-3-c)/10.0);

        System.out.println(s2);

        Quintuplex s3 = new Quintuplex(-1/5.0,
                (sqrt(5)-1)/5.0,
                -(sqrt(5)+1)/5.0,
                -(sqrt(5)+1)/5.0,
                (sqrt(5)-1)/5.0);

        System.out.println(s3);

        Quintuplex s1 = s3.multiply(s2).multiply(s2);

        List<Quintuplex> sList = new ArrayList<>();
        Quintuplex v = s1;
        for (int i=0; i < 6; i++) {
            System.out.println(v);
            sList.add(v);
            v = v.multiply(s1);
        }
        System.out.println();

        double cp = sqrt(3*(5 - 2*sqrt(5)));
        Quintuplex s2p = new Quintuplex(-1/5.0,
                (3+cp)/10.0,
                -3/5.0 + 3*pow(3+cp,2)/20.0 - pow(3+cp,3)/60.0,
                6/5.0 - 3*pow(3+cp,2)/20.0 + pow(3+cp,3)/60.0,
                3/5.0 + (-3-cp)/10.0);

        System.out.println(s2p);

        Quintuplex s3p = new Quintuplex(-1/5.0,
                -(sqrt(5)+1)/5.0,
                (sqrt(5)-1)/5.0,
                (sqrt(5)-1)/5.0,
                -(sqrt(5)+1)/5.0);

        System.out.println(s3p);

        Quintuplex s1p = s3p.multiply(s2p).multiply(s2p);

        List<Quintuplex> spList = new ArrayList<>();
        Quintuplex vp = s1p;
        for (int i=0; i < 6; i++) {
            System.out.println(vp);
            spList.add(vp);
            vp = vp.multiply(s1p);
        }
        System.out.println();

        List<Quintuplex> pList = new ArrayList<>();
        for (Quintuplex s : sList) {
            for (Quintuplex sp : spList) {
                Quintuplex p = s.multiply(sp);
                System.out.print("(" + s + ") * (" + sp + ") = " + p);
                if (sList.contains(p)) {
                    System.out.println(" exists in sList.");
                }
                if (spList.contains(p)) {
                    System.out.println(" exists in sList.");
                }
                if (pList.contains(p)) {
                    System.out.println(" exists in pList.");
                }
                System.out.println();
                pList.add(p);
            }
        }
    }

    @Test
    public void testTessarineTesseract() {
        System.out.println(new Tessarine(0, 0, 1, 0).quadruplexValue());
        System.out.println();
        System.out.println(new Tessarine(0, 0, -1, 0).quadruplexValue());
        System.out.println();

        Tessarine t = new Tessarine(0, 1, 0, 0);
        Tessarine v = t;
        for (int i=0; i<4; i++) {
            System.out.println(v.quadruplexValue());
            v = v.multiply(t);
        }
        System.out.println();

        t = new Tessarine(0, 0, 0, 1);
        v = t;
        for (int i=0; i<4; i++) {
            System.out.println(v.quadruplexValue());
            v = v.multiply(t);
        }
        System.out.println();

        t = new Tessarine(0.5, -0.5, 0.5, 0.5);
        v = t;
        for (int i=0; i<4; i++) {
            System.out.println(v.quadruplexValue());
            v = v.multiply(t);
        }
        System.out.println();

        t = new Tessarine(0.5, 0.5, -0.5, 0.5);
        v = t;
        for (int i=0; i<4; i++) {
            System.out.println(v.quadruplexValue());
            v = v.multiply(t);
        }
        System.out.println();

        t = new Tessarine(-0.5, 0.5, -0.5, -0.5);
        v = t;
        for (int i=0; i<4; i++) {
            System.out.println(v.quadruplexValue());
            v = v.multiply(t);
        }
        System.out.println();

        t = new Tessarine(-0.5, -0.5, 0.5, -0.5);
        v = t;
        for (int i=0; i<4; i++) {
            System.out.println(v.quadruplexValue());
            v = v.multiply(t);
        }
        System.out.println();
    }

    @Test
    public void testDiagonalInverses() {
        double theta = 2 * PI / 3;
        Triplex o = new Triplex(2,-1,-1).multiply(1/3.0).multiply(cos(theta))
                .add(new Triplex(0,1,-1).multiply(1/sqrt(3)).multiply(sin(theta)));

        System.out.println(Triplex.ln(o));

    }

    @Test
    public void testAlgebraicArray() {
        AlgebraicArray q = new AlgebraicArray(0.25, (sqrt(3)-3)/(4*sqrt(2)), sqrt(3)/4, (3+sqrt(3))/(4*sqrt(2)));
        AlgebraicArray q2 = q.multiply(q);
        Triplex s = new Simplex3D(0,1,0,0).triplexValue();
        AlgebraicArray s2 = new AlgebraicArray(s.r, 0, 0, 0).add(q.multiply(s.j)).add(q2.multiply(s.k));

        AlgebraicArray v = s2;
        AlgebraicArray sum = s2;
        for (int i=0; i<4; i++) {
            System.out.println(v + ", norm: " + v.euclideanNorm() + ", sum: " + sum + ", angle w/ s2: " + v.angleWith(s2));
            v = v.multiply(s2);
            sum = sum.add(v);
        }
    }

    @Test
    public void testQuintuplex() {
        for (long i = 0; i < 1000000000000000000L; i++) {
            double w=0, x=0, y=0, z=0;
            Quintuplex[] a = new Quintuplex[6];
            for (int k = 0; k < 10000; k++) {
                w = (-0.5094 - 0.0001*random());
                x = (-0.2977 - 0.0001*random());
                y = (-0.5494 - 0.0001*random());
                z = (0.5566 + 0.0001*random());

                a[0] = new Quintuplex(-0.2, w, x, y, z);
                if (a[0].euclideanNorm() < 1.000003) {
                    break;
                }
            }

            for (int j=0; j<5;) {
                a[j+1] = a[j].multiply(a[0]);

                if (abs(a[j+1].r+0.2) > 0.000003 || a[j+1].euclideanNorm() > 1.000003) {
                    break;
                }
                j++;
            }

            if (a[5] != null) {
                Arrays.stream(a).forEach(x1 -> System.out.println(x1 + " norm: "+x1.euclideanNorm()));
            }
        }
    }

    @Test
    public void testSimplex4D() {
        Quadruplex s1 = new Simplex4D(0,1,0,0,0).quadruplexValue();
        Complex root5 = getComplex(s1);

        Quadruplex qv = s1;
        Complex cv = root5;
        for (int i=0; i<5; i++) {
            System.out.println(qv + " = " + getComplex(qv) + " = " + cv);
            qv = qv.multiply(s1);
            cv = cv.multiply(root5);
        }
        System.out.println();

        s1 = new Simplex4D(0,1,0,0,0).altQuadruplexValue();
        root5 = getComplex(s1);

        qv = s1;
        cv = root5;
        for (int i=0; i<5; i++) {
            System.out.println(qv + " = " + getComplex(qv) + " = " + cv);
            qv = qv.multiply(s1);
            cv = cv.multiply(root5);
        }

        System.out.println();
    }

    private Complex getComplex(Quadruplex s1) {
        Complex sr1 = new Complex(s1.r, 0);
        Complex sh1 = new Complex(1/sqrt(2),1/sqrt(2)).multiply(s1.h);
        Complex si1 = new Complex(0, s1.i);
        Complex sih1 = new Complex(-1/sqrt(2),1/sqrt(2)).multiply(s1.ih);

        Complex root5 = sr1.add(sh1).add(si1).add(sih1);
        return root5;
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
        Triplex v = Triplex.exp(new Simplex3D(0, 0, 0, log(2/3.0)).triplexValue());
        System.out.println(v);

        Triplex t = new Triplex(3, 0, 0)
                .multiply(Triplex.exp(new Simplex3D(0, log(3), 0, 0).triplexValue()))
                .multiply(Triplex.exp(new Simplex3D(0, 0, log(3), 0).triplexValue()))
                .multiply(Triplex.exp(new Simplex3D(0, 0, 0, log(2)).triplexValue()));

        System.out.println(t);
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
    public void testQuadruplex() {
        Tessarine q = new Quadruplex(0.25, (sqrt(3)-3)/(4*sqrt(2)), sqrt(3)/4, (3+sqrt(3))/(4*sqrt(2))).tessarineValue();
        Tessarine q2 = q.multiply(q);
        Triplex s = new Simplex3D(0,1,0,0).triplexValue();
        Tessarine s2 = new Tessarine(s.r, 0, 0, 0).add(q.multiply(s.j)).add(q2.multiply(s.k));

        Tessarine v = s2;
        Tessarine sum = s2;
        for (int i=0; i<4; i++) {
            System.out.println(v + ", norm: " + v.euclideanNorm() + ", sum: " + sum);
            v = v.multiply(s2);
            sum = sum.add(v);
        }

//        Quadruplex nq = q.multiply(-1).quadruplexValue();
//        Quadruplex v = nq;
//        Quadruplex sum = nq;
//        for (int i=0; i<6; i++) {
//            System.out.println(v + ", norm: " + v.euclideanNorm() + ", sum: " + sum);
//            v = v.multiply(nq);
//            sum = sum.add(v);
//        }
    }

    @Test
    public void testTriplexUnitVectors() {
        Triplex q = Triplex.exp(Triplex.generateUnit(-TAU/4));

        System.out.println(q + " norm: " + q.euclideanNorm());
    }

    @Test
    public void testTrirationals() {
        Complex c = new Complex(7, -11);
        System.out.println(c.trirationalValue());
        System.out.println(c.trirationalValue().complexValue());
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
