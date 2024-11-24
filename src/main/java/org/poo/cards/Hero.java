package org.poo.cards;

import lombok.Getter;
import lombok.Setter;
import org.poo.fileio.CardInput;

@Getter
@Setter
public final class Hero extends GeneralCard {
    private final int startingHealth = 30;

    public Hero() {
        super();
    }

    public Hero(final CardInput playerHero) {
        health = startingHealth;
        mana = playerHero.getMana();
        name = playerHero.getName();
        colors = playerHero.getColors();
        description = playerHero.getDescription();
    }
}
