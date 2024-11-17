package org.poo.cards;

import lombok.Getter;
import lombok.Setter;
import org.poo.fileio.CardInput;
import org.poo.fileio.Coordinates;

@Setter @Getter
public class Minion extends GeneralCard{
    private boolean isTank;
    private boolean isFrozen;
    private int attackDamage;
    private Coordinates coordinates;

    public Minion(CardInput card) {
        mana = card.getMana();
        health = card.getHealth();
        colors = card.getColors();
        description = card.getDescription();
        name = card.getName();
        attackDamage = card.getAttackDamage();
        isFrozen = false;
        isTank = false;
        coordinates = new Coordinates();
        coordinates.setX(-1);
        coordinates.setY(-1);
    }
}
