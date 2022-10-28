package rpg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg.interfaces.IGear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeadGearTest {
    IGear headGear1, headGear2;

    @Before
    public void Setup() {
        headGear1 = new HeadGear(1, 1, "Awesome", "Headgear");
        headGear2 = new HeadGear(2, 2, "Excellent", "Shades");
    }

    @Test
    public void combine() {
        IGear combinedGear = headGear1.combine(headGear2);
        assertEquals(3, combinedGear.getAttackModifier());
        assertEquals(3, combinedGear.getDefenseModifier());
        assertEquals(combinedGear.getNoun(), headGear1.getName());
        assertEquals(combinedGear.getAdjective(), headGear2.getAdjective());
        assertTrue(combinedGear instanceof HeadGear);
    }
    @Test(expected = IllegalArgumentException.class)
    public void combineInvokedWithNullGearResultsInException() {
        headGear1.combine(null);
    }
}