package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.cards.Hero;
import org.poo.fileio.ActionsInput;

public class GetPlayerHero extends AbstractAction {
    private Hero hero;
    private int ownerIdx;

    public GetPlayerHero(ActionsInput actionsInput, Hero inputHero, ArrayNode output) {
        super(actionsInput);
        hero = inputHero;
        outputArray = output;
        ownerIdx = actionsInput.getPlayerIdx();
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode comm = mapper.createObjectNode();
        comm.put("command", getCommand());
        comm.put("playerIdx", ownerIdx);
        comm.put("output", hero.prepareOutput());
        outputArray.add(comm);
    }
}
