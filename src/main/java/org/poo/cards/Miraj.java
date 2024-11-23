package org.poo.cards;

import org.poo.fileio.CardInput;

public final class Miraj extends Minion implements SpecialAbility {

    public Miraj(final CardInput card) {
        super(card);
    }

    @Override
    public void useAbility(final Minion target) {
        //  "Skyjack"
        int aux = this.getHealth();
        this.setHealth(target.getHealth());
        target.setHealth(aux);
    }
}
