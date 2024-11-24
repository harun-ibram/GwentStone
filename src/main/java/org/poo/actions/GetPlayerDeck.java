package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.players.Deck;

public class GetPlayerDeck extends AbstractAction {

    public GetPlayerDeck(final ActionsInput actionsInput, final Game g, final ArrayNode out) {
        super(actionsInput, g, out);
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode comm = mapper.createObjectNode();
        comm.put("command", getCommand());
        comm.put("playerIdx", getPlayerIdx());
        comm.set("output", getState().getPlayerDeck(getPlayerIdx()).prepareOutput());
        outputArray.add(comm);
    }
}
