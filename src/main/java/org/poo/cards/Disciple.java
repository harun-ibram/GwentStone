package org.poo.cards;

import org.poo.fileio.CardInput;

public class Disciple extends Minion implements SpecialAbility{

    public Disciple(CardInput card) {
        super(card);
    }

    @Override
    public void useAbility(Minion target) {
        target.setHealth(target.getHealth() + 2);
    }
}
