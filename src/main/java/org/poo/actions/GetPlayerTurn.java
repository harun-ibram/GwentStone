package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;

public class GetPlayerTurn extends AbstractAction {

    public GetPlayerTurn(ActionsInput actionsInput, final Game g, final ArrayNode output) {
        super(actionsInput, g, output);
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode comm = mapper.createObjectNode();
        comm.put("command", getCommand());
        comm.put("output", getState().getPlayerTurn());
        outputArray.add(comm);
    }
}
