import org.junit.Before;
import org.junit.Test;
import rpg.Battle;
import rpg.Footwear;
import rpg.HandGear;
import rpg.Player;
import rpg.interfaces.IGear;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BattleTest {
    List<IGear> gear = new ArrayList<>();
    Player player1, player2;
    Battle battle;

    @Before
    public void setUp() throws Exception {
        Player player1 = new Player(1, 1);
        Player player2 = new Player(1, 1);

        IGear footGear = new Footwear(3, 3, "test1", "test2");
        IGear handGear = new HandGear(2, 2, "Awesome", "handgear");
        gear.add(footGear);
        gear.add(handGear);
        battle = new Battle(player1, player2, gear);
    }

    @Test
    public void testConstructor() {
        // We just instantiate a battle without gear and then verify the type
        // to verify construction worked appropriately.
        Battle emptyGearBattle = new Battle(player1, player2, new ArrayList<IGear>());
        assertTrue(emptyGearBattle instanceof Battle);

        // test that exceptions are thrown for null params
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionsForNullPlayer1() {
        Battle nullBattle = new Battle(null, player2, gear);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionsForNullPlayer2() {
        Battle nullBattle = new Battle(player1, null, gear);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionsForNullGear() {
        Battle nullBattle = new Battle(player1, player2, null);
    }

    @Test
    public void runBattle() {
        // test that a battle result is what is expected based on params
        // test that a battle with no gear is decided on the characters stats alone
        String expectedResult = "Player1";
        assertEquals(expectedResult, battle.runBattle());
   }
}