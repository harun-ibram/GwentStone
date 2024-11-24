package org.poo.actions;


import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;

public final class EndTurn extends AbstractAction {
    public EndTurn(final ActionsInput actionsInput, final Game g, final ArrayNode output) {
        super(actionsInput, g, output);
    }

    @Override
    public void executeAction() {
        state.nextTurn();
    }
}
