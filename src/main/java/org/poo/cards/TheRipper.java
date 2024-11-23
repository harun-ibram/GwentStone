package org.poo.cards;

import org.poo.fileio.CardInput;

public final class TheRipper extends Minion implements SpecialAbility {

    public TheRipper(final CardInput card) {
        super(card);
    }

    @Override
    public void useAbility(final Minion target) {
        //  Weak Knees
        target.setAttackDamage(Math.max(target.getAttackDamage() - 2, 0));
    }
}
