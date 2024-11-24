package org.poo.cards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import org.poo.fileio.CardInput;
import org.poo.fileio.Coordinates;

@Setter @Getter
public class Minion extends GeneralCard {
    private boolean isTank;
    private boolean isFrozen;
    private int attackDamage;
    private int x;
    private int y;
    private boolean acted;
    public Minion(final CardInput card) {
        mana = card.getMana();
        health = card.getHealth();
        colors = card.getColors();
        description = card.getDescription();
        name = card.getName();
        attackDamage = card.getAttackDamage();
        isFrozen = false;
        switch (name) {
            case "Goliath", "Warden" -> isTank = true;
            default -> isTank = false;
        }
        x = -1;
        y = -1;
        acted = false;
    }

    public Minion(final Minion original) {
        mana = original.getMana();
        health = original.getHealth();
        colors = original.getColors();
        description = original.getDescription();
        name = original.getName();
        attackDamage = original.getAttackDamage();
        isFrozen = false;
        isTank = false;
        x = -1;
        y = -1;
        acted = false;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (String color : getColors()) {
            sb.append(color + ",\n");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("\n]");
        return "\n{\n"
                + "\"mana\":" + getMana() + ",\n"
                + "\"attackDamage\":" + getAttackDamage() + ",\n"
                + "\"health\":" + getHealth() + ",\n"
                + "\"description\":" + getDescription() + ",\n"
                + "\"colors\":" + sb.toString() + ",\n"
                + "\"name\": " + getName()
                + "\n}";
    }


    @Override
    public ObjectNode prepareOutput() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("mana", mana);
        res.put("attackDamage", attackDamage);
        res.put("health", health);
        res.put("description", description);
        ArrayNode colorArray = mapper.createArrayNode();
        for (String color : colors) {
            colorArray.add(color);
        }
        res.set("colors", colorArray);
        res.put("name", name);
        return res;
    }
}
