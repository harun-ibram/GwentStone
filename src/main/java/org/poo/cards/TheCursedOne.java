package org.poo.cards;

import org.poo.fileio.CardInput;

public final class TheCursedOne extends Minion implements SpecialAbility {
    public TheCursedOne(final CardInput card) {
        super(card);
    }

    @Override
    public void useAbility(final Minion target) {
        //  "Shapeshift"
        int aux = target.getHealth();
        target.setHealth(target.getAttackDamage());
        target.setAttackDamage(aux);
    }
}
