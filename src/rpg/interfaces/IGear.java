package rpg.interfaces;

import java.util.Comparator;

public interface IGear {
    Comparator<IGear> attackComparator = Comparator.comparingInt(IGear::getAttackModifier).reversed();
    Comparator<IGear> defenseComparator = Comparator.comparingInt(IGear::getDefenseModifier).reversed();

    int getAttackModifier();
    int getDefenseModifier();
    String getName();
    String getAdjective();
    String getNoun();

    IGear combine(IGear other) throws IllegalArgumentException;
}
