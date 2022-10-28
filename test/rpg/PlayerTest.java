package rpg;

import org.junit.Before;
import org.junit.Test;
import rpg.interfaces.IGear;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;
    List<IGear> gear;
    IGear footGear, handGear;
    @Before
    public void setup() {
        player = new Player(1, 1);
        gear = new ArrayList<>();
        footGear = new Footwear(3, 3, "test1", "test2");
        handGear = new HandGear(2, 2, "Awesome", "handgear");
        gear.add(footGear);
        gear.add(handGear);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionsForNegativeAttack() {
        Player player1 = new Player(-1, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionsForNegativeDefense() {
        Player player1 = new Player(1, -1);
    }

    @Test
    public void chooseGear() {
        IGear chosenItem = player.chooseGear(gear);
        assertEquals(footGear, chosenItem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void chooseGearInvokedWithNullGearResultsInException() {
        player.chooseGear(null);
    }

    @Test
    public void getAttackValue() {
        assertEquals(1, player.getAttackValue());
    }

    @Test
    public void getDefenseValue() {
        assertEquals(1, player.getDefenseValue());
    }

    @Test
    public void getAttackModifier() {
        player.chooseGear(gear);
        assertEquals(3, player.getAttackModifier());
    }

    @Test
    public void getDefenseModifier() {
        player.chooseGear(gear);
        assertEquals(3, player.getDefenseModifier());
    }
}