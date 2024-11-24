package org.poo.cards;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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


    /**
     * Creates the JSON Object from the card's properties.
     * @return The current card in JSON form.
     */
    public ObjectNode prepareOutput() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("mana", mana);
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
