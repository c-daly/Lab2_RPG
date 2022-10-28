package rpg.base;

import org.junit.Before;
import org.junit.Test;
import rpg.Footwear;
import rpg.HandGear;
import rpg.HeadGear;
import rpg.interfaces.IGear;

import static org.junit.Assert.*;

public class AbstractGearTest {
    IGear footwear, headgear, handgear;
    @Before
    public void setup() {
       footwear = new Footwear(1, 1, "Awesome", "Footwear");
       handgear = new HandGear(2, 2, "Neato", "Handgear");
       headgear = new HeadGear(3, 3, "Supreme", "Headgear");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvokedWithNegativeAttackBonusResultsInException() {
        footwear = new Footwear(-1, 1, "adjective", "noun");
    }
    @Test(expected = IllegalArgumentException.class)
    public void constructorInvokedWithNegativeDefenseBonusResultsInException() {
        footwear = new Footwear(1, -1, "adjective", "noun");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvokedWithEmptyAdjectiveResultsInException() {
        footwear = new Footwear(1, 1, "", "noun");
    }
    @Test(expected = IllegalArgumentException.class)
    public void constructorInvokedWithEmptyNounResultsInException() {
        footwear = new Footwear(1, 1, "adjective", "");
    }
    @Test
    public void getAttackModifier() {
        // simply have to verify that the value returned is what was given to the constructor
        assertEquals(1, footwear.getAttackModifier());
        assertEquals(2, handgear.getAttackModifier());
        assertEquals(3, headgear.getAttackModifier());
    }

    @Test
    public void getDefenseModifier() {
        // simply have to verify that the value returned is what was given to the constructor
        assertEquals(1, footwear.getDefenseModifier());
        assertEquals(2, handgear.getDefenseModifier());
        assertEquals(3, headgear.getDefenseModifier());
    }

    @Test
    public void getName() {
        // simply verify that the value returned is equal to adjective + " " + noun
        String fullName1 = "Awesome Footwear";
        String fullName2 = "Neato Handgear";
        String fullName3 = "Supreme Headgear";

        assertEquals(fullName1, footwear.getName());
        assertEquals(fullName2, handgear.getName());
        assertEquals(fullName3, headgear.getName());
    }

    @Test
    public void getAdjective() {
        // simply have to verify that the value returned is what was given to the constructor
        String adj1 = "Awesome";
        String adj2 = "Neato";
        String adj3 = "Supreme";

        assertEquals(adj1, footwear.getAdjective());
        assertEquals(adj2, handgear.getAdjective());
        assertEquals(adj3, headgear.getAdjective());
    }

    @Test
    public void getNoun() {
        // simply have to verify that the value returned is what was given to the constructor
        String noun1 = "Footwear";
        String noun2 = "Handgear";
        String noun3 = "Headgear";

        assertEquals(noun1, footwear.getNoun());
        assertEquals(noun2, handgear.getNoun());
        assertEquals(noun3, headgear.getNoun());
    }
}