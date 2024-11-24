package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.game.Table;


public final class GetCardAtPosition extends AbstractAction {
    public GetCardAtPosition(final ActionsInput act, final Game game, final ArrayNode out) {
        super(act, game, out);
    }

    @Override
    public void executeAction() {
        Table table = Table.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("command", command);
        if (table.getRow(x).isEmpty() || table.getRow(x).size() - 1 < y) {
            res.put("output", "No card available at that position.");
            res.put("x", x);
            res.put("y", y);
            outputArray.add(res);
            return;
        }
        res.set("output", table.getCardAt(x, y).prepareOutput());
        res.put("x", x);
        res.put("y", y);
        outputArray.add(res);

    }
}
