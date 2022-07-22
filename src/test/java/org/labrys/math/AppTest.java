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
    public void testEqualityWithComplexUnits()
    {
        double radians = 4*PI/3;

        Simplex2D unitVector = Simplex2D.generateUnit(radians);
        System.out.println(unitVector);
        System.out.println(unitVector.normalize());

        Complex complexUnitVector = Complex.generateUnit(radians);
        assertEquals(complexUnitVector.r,
                unitVector.complexValue().r,
                0.000000000000001);

        assertEquals(complexUnitVector.i,
                unitVector.complexValue().i,
                0.000000000000001);

        double scalar = 3.0;
        Quaternion scaledUnit = complexUnitVector.multiply(scalar);
        assertEquals(scaledUnit.r,
                unitVector.multiply(scalar).complexValue().r,
                0.000000000000001);

        assertEquals(scaledUnit.i,
                unitVector.multiply(scalar).complexValue().i,
                0.000000000000001);
    }
}
