package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.game.Table;

public final class GetCardsOnTable extends AbstractAction {
    private Table table = Table.getInstance();

    public GetCardsOnTable(final ActionsInput actionsInput, final Game game, final ArrayNode out) {
        super(actionsInput, game, out);
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
