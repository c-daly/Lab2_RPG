package rpg;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import rpg.interfaces.IGear;

public class FootwearTest {
    IGear footwear1, footwear2;
    @Before
    public void setup() {
       footwear1 = new Footwear(1, 1, "Cool", "Footwear");
       footwear2 = new Footwear(2, 2, "Awesome", "Shoes");
    }
    @Test
    public void combine() {
       IGear combinedFootwear = footwear1.combine(footwear2);
       Assert.assertEquals(3, combinedFootwear.getAttackModifier());
       Assert.assertEquals(3, combinedFootwear.getDefenseModifier());
       Assert.assertEquals(combinedFootwear.getAdjective(), footwear2.getAdjective());
       Assert.assertEquals(combinedFootwear.getNoun(), footwear1.getName());
    }
}