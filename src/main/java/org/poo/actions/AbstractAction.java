package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;

@Getter
@Setter
public abstract class AbstractAction implements ExecuteAction {
    protected String command;
    protected int handIdx;
    protected int attackerX;
    protected int attackerY;
    protected int attackedX;
    protected int attackedY;
    protected int affectedRow;
    protected int playerIdx;
    protected int x;
    protected int y;
    protected ArrayNode outputArray;
    protected Game state;

    public AbstractAction(final ActionsInput actionsInput, final Game game, final ArrayNode output) {
        command = actionsInput.getCommand();
        handIdx = actionsInput.getHandIdx();
        if (actionsInput.getCardAttacker() != null) {
            attackerX = actionsInput.getCardAttacker().getX();
            attackerY = actionsInput.getCardAttacker().getY();
        }
        if (actionsInput.getCardAttacked() != null) {
            attackedX = actionsInput.getCardAttacked().getX();
            attackedY = actionsInput.getCardAttacked().getY();
        }
        affectedRow = actionsInput.getAffectedRow();
        playerIdx = actionsInput.getPlayerIdx();
        x = actionsInput.getX();
        y = actionsInput.getY();
        state = game;
        outputArray = output;
    }
}
