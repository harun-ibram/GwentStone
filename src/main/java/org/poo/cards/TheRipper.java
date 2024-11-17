package org.poo.cards;

import org.poo.fileio.CardInput;

public class TheRipper extends Minion implements SpecialAbility{

    public TheRipper(CardInput card) {
        super(card);
    }

    @Override
    public void useAbility(Minion target) {
        //  Weak Knees
        target.setAttackDamage(Math.max(target.getAttackDamage() - 2, 0));
    }
}
