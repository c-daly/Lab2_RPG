package rpg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg.interfaces.IGear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HandGearTest {
    private IGear handGear1, handGear2;

    @Before
    public void Setup() {
        handGear1 = new HandGear(1, 1, "Awesome", "Handgear");
        handGear2 = new HandGear(2, 2, "Neato", "Gloves");
    }

    @Test
    public void combine() {
       IGear combinedHandGear = handGear1.combine(handGear2);
       assertEquals(3, combinedHandGear.getAttackModifier());
       assertEquals(3, combinedHandGear.getDefenseModifier());
       assertEquals(combinedHandGear.getAdjective(), handGear2.getAdjective());
       assertEquals(combinedHandGear.getNoun(), handGear1.getName());
       assertTrue(combinedHandGear instanceof HandGear);
    }
    @Test(expected = IllegalArgumentException.class)
    public void combineInvokedWithNullGearResultsInException() {
        handGear1.combine(null);
    }
}