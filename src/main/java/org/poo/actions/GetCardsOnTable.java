package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.game.Table;

public class GetCardsOnTable extends AbstractAction {
    Table table = Table.getInstance();

    public GetCardsOnTable(ActionsInput actionsInput, final Game game, ArrayNode output) {
        super(actionsInput, game, output);
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode comm = mapper.createObjectNode();
        comm.put("command", getCommand());
        comm.set("output", table.getCardsOnTable());
        outputArray.add(comm);
    }
}
