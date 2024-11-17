package org.poo.cards;

import org.poo.fileio.CardInput;

public class TheCursedOne extends Minion implements SpecialAbility{
    public TheCursedOne(CardInput card) {
        super(card);
    }

    @Override
    public void useAbility(Minion target) {
        //  "Shapeshift"
        int aux = target.getHealth();
        target.setHealth(target.getAttackDamage());
        target.setAttackDamage(aux);
    }
}
