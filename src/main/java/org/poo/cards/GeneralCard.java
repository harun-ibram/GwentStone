package org.poo.cards;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class GeneralCard {
    protected int health;
    protected int mana;
    protected String name;
    protected ArrayList<String> colors;
    protected String description;
}
