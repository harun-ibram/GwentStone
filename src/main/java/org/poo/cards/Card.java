package org.poo.cards;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Card {
    private int mana;
    private String name;
    private int health;
    private String[] colors;
    private String description;
    private boolean isTank;
    private boolean isFrozen;
}
