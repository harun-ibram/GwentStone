package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;
import org.poo.players.Deck;

public class GetPlayerDeck extends AbstractAction {
    private Deck deck;
    public GetPlayerDeck(final ActionsInput actionsInput) {
        super(actionsInput);
    }

    public GetPlayerDeck(final ActionsInput actionsInput, final Deck d, final ArrayNode out) {
        super(actionsInput);
        deck = d;
        outputArray = out;
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode comm = mapper.createObjectNode();
        comm.put("command", getCommand());
        comm.put("playerIdx", deck.getOwnerIdx());
        comm.set("output", deck.prepareOutput());
        outputArray.add(comm);
    }
}
