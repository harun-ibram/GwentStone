package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;

public class GetPlayerTurn extends AbstractAction {
    private int turn;

    public GetPlayerTurn(ActionsInput a) {
        super(a);
    }

    public GetPlayerTurn(ActionsInput actionsInput, final int currentTurn, final ArrayNode output) {
        super(actionsInput);
        turn = currentTurn;
        outputArray = output;
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode comm = mapper.createObjectNode();
        comm.put("command", getCommand());
        comm.put("output", turn);
        outputArray.add(comm);
    }
}
