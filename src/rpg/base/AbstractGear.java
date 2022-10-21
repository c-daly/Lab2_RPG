package rpg.base;

import rpg.interfaces.IGear;

/**
 *
 */
public abstract class AbstractGear implements IGear {

    private final int attackModifier;
    private final int defenseModifier;
    private final String adjective;
    private final String noun;

    public AbstractGear(int attack, int defense, String adjective, String noun)
    {
        // attack and defense must be positive integers
        // nothing gets set, so only need to test for good values here
        this.attackModifier = attack;
        this.defenseModifier = defense;
        // can't be null or empty strings
        this.adjective = adjective;
        this.noun = noun;
    }

    /**
     * @param otherGear
     * @return
     * @throws Exception
     */
    public abstract IGear combine(IGear otherGear) throws Exception;

    /**
     * @return
     */
    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public String getName() {
        return adjective + " " + noun;
    }

    public String getAdjective() {
        return adjective;
    }

    public String getNoun() {
        return noun;
    }

}
