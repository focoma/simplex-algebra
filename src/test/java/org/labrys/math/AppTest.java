package org.labrys.math;

import org.junit.Test;

import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testTriplex() {

        Triplex e1 = new Triplex(1,1,1).multiply(1.0/3.0);
        System.out.println(e1);
        System.out.println(e1.multiply(e1));
        Triplex e2 = new Triplex(2, -1, -1).multiply(1.0/3.0);
        Triplex e3 = new Triplex(0, 1, -1).multiply(1.0 / sqrt(3));
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e2.multiply(e3));
        System.out.println(e3.multiply(e3));
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
    public void testTriplexWithDodecahedron() {
        Triplex s = new Triplex(0.206011329583297, -0.263647691154906, -0.942363638428394);
        Triplex s2 = new Simplex3D(0,0,0,-1).triplexValue();
        Triplex s3 = new Triplex(-0.804737854124384, 0.3106172175, -0.5058793634);
        Triplex s4 = new Simplex3D(0,0,1,0).triplexValue();
        Triplex s5 = new Triplex(-0.804737854124384, 0.3106172175, -0.5058793634);
        Triplex s6 = new Simplex3D(0,-1,0,0).triplexValue();
        Triplex s7 = new Triplex(-0.804737854124384, 0.3106172175, -0.5058793634);

        System.out.println(s);
        System.out.println(s.multiply(s));
        System.out.println(s.multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
        System.out.println(s.multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s).multiply(s));
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
}
