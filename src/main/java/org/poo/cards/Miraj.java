package org.poo.cards;

import org.poo.fileio.CardInput;

public class Miraj extends Minion implements SpecialAbility{

    public Miraj(CardInput card) {
        super(card);
    }

    @Override
    public void useAbility(Minion target) {
        //  "Skyjack"
        int aux = this.getHealth();
        this.setHealth(target.getHealth());
        target.setHealth(aux);
    }
}
