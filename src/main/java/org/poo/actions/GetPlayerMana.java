package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;

public final class GetPlayerMana extends AbstractAction {
    public GetPlayerMana(final ActionsInput actionsInput, final Game game, final ArrayNode out) {
        super(actionsInput, game, out);
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("command", command);
        res.put("playerIdx", playerIdx);
        res.put("output", state.getPlayer(playerIdx).getMana());
        outputArray.add(res);
    }
}
