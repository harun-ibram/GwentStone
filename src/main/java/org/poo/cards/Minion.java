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

    public void attack(GeneralCard target) {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (String color : getColors()) {
            sb.append(color + ",\n");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("\n]");
        return "{\n" +
                "\"mana\":" + getMana() + ",\n" +
                "\"attackDamage\":" + getAttackDamage() + ",\n" +
                "\"health\":" + getHealth() + ",\n" +
                "\"description\":" + getDescription() + ",\n" +
                "\"colors\":" + sb.toString() + ",\n" +
                "\"name\": " + getName() +
                "\n}";
    }
}
