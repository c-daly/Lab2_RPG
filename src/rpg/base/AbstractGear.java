package rpg.base;

import rpg.interfaces.IGear;

/**
 *
 */
public abstract class AbstractGear implements IGear {

    /**
     * Determines the attack bonus this piece of gear gives.
     */
    private final int attackModifier;
    /**
     * Determines the defensive bonus this piece of gear gives.
     */
    private final int defenseModifier;
    /**
     * Determines the adjective portion of this piece of gear's name.
     */
    private final String adjective;
    /**
     * Determines the noun portion of this piece of gear's name.
     */
    private final String noun;

    /**
     * @param attack integer value greater than zero that determines the gear's attack bonus.
     * @param defense integer value greater than zero that determines the gear's defense bonus.
     * @param adjective Non-null, non-empty string that determines the adjective portion of the gear's name.
     * @param noun Non-null, non-empty string that determines the noun portion of the gear's name.
     */
    public AbstractGear(int attack, int defense, String adjective, String noun) throws IllegalArgumentException
    {
        // attack and defense must be positive integers
        if (attack < 0 || defense < 0) {
            throw new IllegalArgumentException("Attack and defense values must be positive integers.");
        }

        // noun and adjective can't be null or empty strings
        if (noun == null || noun.isEmpty() || adjective == null || adjective.isEmpty()) {
            throw new IllegalArgumentException("Adjective and noun arguments for AbstractGear must be non-null, nonempty strings.");
        }

        this.attackModifier = attack;
        this.defenseModifier = defense;
        this.noun = noun;
        this.adjective = adjective;
    }

    /**
     * @param otherGear instance of IGear with which this gear is to be combined. Types must match.
     * @return returns an instance of IGear matching the concrete type of both combined pieces of gear.
     * @throws IllegalArgumentException if otherGear is null.
     */
    public abstract IGear combine(IGear otherGear) throws IllegalArgumentException;

   /**
     * @return gets the attack bonus for this piece of gear.
     */
    public int getAttackModifier() {
        return attackModifier;
    }

    /**
     * @return gets the defense bonus for this piece of gear.
     */
    public int getDefenseModifier() {
        return defenseModifier;
    }

    /**
     * @return gets the full name of this piece of gear (adjective + noun).
     */
    public String getName() {
        return adjective + " " + noun;
    }

    /**
     * @return gets the adjective portion of the name of this piece of gear.
     */
    public String getAdjective() {
        return adjective;
    }

    /**
     * @return gets the noun portion of the name of this piece of gear.
     */
    public String getNoun() {
        return noun;
    }

    /**
     * @param otherGear an instance of IGear whose type we are checking
     * @return boolean indicating whether the param type matches 'this'.
     * @throws IllegalArgumentException if otherGear is null.
     */
    protected final boolean sameTypeAs(IGear otherGear) throws IllegalArgumentException {
        if (otherGear == null) {
            throw new IllegalArgumentException("otherGear cannot be null.");
        }
        return this.getClass() == otherGear.getClass();
    }

    @Override
    public final String toString() {
        return String.format("%s: Attack Bonus: %d, Defense Bonus: %d\n", getName(), getAttackModifier(), getDefenseModifier());
    }
}
