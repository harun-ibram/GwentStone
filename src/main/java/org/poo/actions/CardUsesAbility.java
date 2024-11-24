package org.poo.actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.jdi.VoidType;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;

public class CardUsesAbility extends AbstractAction {
    public CardUsesAbility(ActionsInput actionsInput, Game game, ArrayNode out) {
        super(actionsInput, game, out);
    }

    @Override
    public void executeAction() {

    }
}
