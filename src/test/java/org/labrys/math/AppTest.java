package org.labrys.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static java.lang.Math.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test()
    {
        double radians = PI/3;

        Simplex2D unitVector = Simplex2D.simplexp(radians);
        System.out.println(unitVector);
        System.out.println(unitVector.normalize());

        Complex complexUnitVector = new Complex(cos(radians), sin(radians));
        assertEquals(complexUnitVector.r,
                unitVector.complexValue().r,
                0.000000000000001);

        assertEquals(complexUnitVector.i,
                unitVector.complexValue().i,
                0.000000000000001);
    }
}
