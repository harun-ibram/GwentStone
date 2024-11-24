package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.players.Deck;
import org.poo.players.Player;

public class EndTurn extends AbstractAction {
    public EndTurn(final ActionsInput actionsInput, final Game g, final ArrayNode output) {
        super(actionsInput, g, output);
    }

    @Override
    public void executeAction() {
        getState().nextTurn();
    }
}
