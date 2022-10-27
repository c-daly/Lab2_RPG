package rpg;

import rpg.base.AbstractGear;
import rpg.interfaces.IGear;

public class HeadGear extends AbstractGear {
    /***
     * See AbstractGear constructor for more information
     *
     * @throws IllegalArgumentException
     */
    public  HeadGear(int attack, int defense, String adjective, String noun) throws IllegalArgumentException {
        super(attack, defense, adjective, noun);
    }

    /***
     * See AbstractGear.combine() for more information about this method
     *
     * @throws IllegalArgumentException
     */
    @Override
    public IGear combine(IGear otherGear) throws IllegalArgumentException {
        if (!sameTypeAs(otherGear)) {
            throw new IllegalArgumentException("Gear must be of same type to combine");
        }
        return new HeadGear(getAttackModifier(), getDefenseModifier(), otherGear.getAdjective(), getNoun());
    }
}
