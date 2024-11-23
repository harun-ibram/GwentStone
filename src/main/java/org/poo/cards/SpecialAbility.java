package org.poo.cards;

public interface SpecialAbility {
    /**
     * Use ability on the target card.
     * @param target The card to be affected by ability.
     */
    void useAbility(Minion target);
}
