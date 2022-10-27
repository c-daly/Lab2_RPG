package rpg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg.interfaces.IGear;

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
       Assert.assertEquals(3, combinedHandGear.getAttackModifier());
       Assert.assertEquals(3, combinedHandGear.getDefenseModifier());
       Assert.assertEquals(combinedHandGear.getAdjective(), handGear2.getAdjective());
       Assert.assertEquals(combinedHandGear.getNoun(), handGear1.getName());

    }
}