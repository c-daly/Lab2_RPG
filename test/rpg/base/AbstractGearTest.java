package rpg.base;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractGearTest {

    @Test
    public void combine() {
        // have to test whether the default combine case works - verify numbers sum correctly and name updated properly
        // have to test if it throws exceptions when one of the args is null
        // have to test if it throws exceptions when the other arg is a different sort of gear
    }

    @Test
    public void getAttackModifier() {
        // simply have to verify that the value returned is what was given to the constructor
    }

    @Test
    public void getDefenseModifier() {
        // simply have to verify that the value returned is what was given to the constructor
    }

    @Test
    public void getName() {
        // simply verify that the value returned is equal to adjective + " " + noun
    }

    @Test
    public void getAdjective() {
        // simply have to verify that the value returned is what was given to the constructor
    }

    @Test
    public void getNoun() {
        // simply have to verify that the value returned is what was given to the constructor
    }

    @Test
    public void sameTypeAs() {
        // test that it works in the general case
        // test that it returns false when appropriate
        // test that exception is thrown when it's given null
    }
}