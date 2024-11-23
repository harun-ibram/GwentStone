package org.poo.cards;

import org.poo.fileio.CardInput;

public final class Disciple extends Minion implements SpecialAbility {

    public Disciple(final CardInput card) {
        super(card);
    }

    @Override
    public void useAbility(final Minion target) {
        target.setHealth(target.getHealth() + 2);
    }
}
