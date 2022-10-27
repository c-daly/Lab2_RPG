package rpg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg.interfaces.IGear;

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
        Assert.assertEquals(3, combinedGear.getAttackModifier());
        Assert.assertEquals(3, combinedGear.getDefenseModifier());
        Assert.assertEquals(combinedGear.getNoun(), headGear1.getName());
        Assert.assertEquals(combinedGear.getAdjective(), headGear2.getAdjective());
    }
}