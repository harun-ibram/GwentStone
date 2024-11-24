package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;
import org.poo.fileio.GameInput;
import org.poo.game.Game;

public class GetPlayerMana extends AbstractAction {
    public GetPlayerMana(ActionsInput actionsInput, Game game, ArrayNode output) {
        super(actionsInput, game, output);
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
