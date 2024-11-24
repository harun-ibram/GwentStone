package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.cards.Minion;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;

import java.util.ArrayList;

public final class GetCardsInHand extends AbstractAction {

    public GetCardsInHand(final ActionsInput actionsInput, final Game game, final ArrayNode out) {
        super(actionsInput, game, out);
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("command", command);
        res.put("playerIdx", playerIdx);
        ArrayNode output = mapper.createArrayNode();
        ArrayList<Minion> hand = state.getPlayer(playerIdx).getCardsInHand();
        for (Minion minion : hand) {
            output.add(minion.prepareOutput());
        }
        res.set("output", output);
        outputArray.add(res);
    }
}
