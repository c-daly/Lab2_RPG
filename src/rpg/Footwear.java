package rpg;

import rpg.base.AbstractGear;
import rpg.interfaces.IGear;

public class Footwear extends AbstractGear {

    public Footwear(int attack, int defense, String adjective, String noun) {
        super(attack, defense, adjective, noun);
    }

    @Override
    public IGear combine(IGear otherGear) throws Exception {
        return new Footwear(getAttackModifier(),
                getDefenseModifier(),
                otherGear.getAdjective(),
                getNoun());
    }
}
