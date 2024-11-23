package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import org.poo.fileio.ActionsInput;

@Getter
@Setter
public abstract class AbstractAction implements ExecuteAction {
    private String command;
    private int handIdx;
    private int attackerX;
    private int attackerY;
    private int attackedX;
    private int attackedY;
    private int affectedRow;
    private int playerIdx;
    private int x;
    private int y;
    protected ArrayNode outputArray;

    public AbstractAction(final ActionsInput actionsInput) {
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
    }
}
