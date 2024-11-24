package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.cards.Minion;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.game.Table;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CardUsesAttack extends AbstractAction {
    public CardUsesAttack(ActionsInput actionsInput, Game game, ArrayNode output) {
        super(actionsInput, game, output);
    }

    @Override
    public void executeAction() {
        Table table = Table.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("command", command);
        if (table.getRow(attackerX).isEmpty() || table.getRow(attackerX).size() - 1 < attackerY)
            return;
        Minion attacker = table.getCardAt(attackerX, attackerY);
        int attackerOwner, attackedOwner;
        if (attackerX == 0 || attackerX == 1) {
            attackerOwner = 2;
        } else attackerOwner = 1;

        if (attackedX == 2 || attackedX == 3) {
            attackedOwner = 1;
        } else attackedOwner = 2;
        if (table.getRow(attackedX).isEmpty() || table.getRow(attackedX).size() - 1 < attackedY)
            return;
        Minion attacked = table.getCardAt(attackedX, attackedY);

        //  Validate for team damage
        if (attackedOwner == attackerOwner) {
            res.set("cardAttacker", cardCoordsOutput(attackerX, attackerY));
            res.set("cardAttacked", cardCoordsOutput(attackedX, attackedY));
            res.put("error", "Attacked card does not belong to the enemy.");
            outputArray.add(res);
            return;
        }

        //  Validate for action in current round
        if (attacker.isActed()) {
            res.set("cardAttacker", cardCoordsOutput(attackerX, attackerY));
            res.set("cardAttacked", cardCoordsOutput(attackedX, attackedY));
            res.put("error", "Attacker card has already attacked this turn.");
            outputArray.add(res);
            return;
        }

        //  Validate for frozen minion
        if (attacker.isFrozen()) {
            res.set("cardAttacker", cardCoordsOutput(attackerX, attackerY));
            res.set("cardAttacked", cardCoordsOutput(attackedX, attackedY));
            res.put("error", "Attacker card is frozen.");
            outputArray.add(res);
            return;
        }

        //  Validate for tank in front line
        ArrayList<Minion> tanks = new ArrayList<>();
        int frontLines = attackedOwner == 1 ? 2 : 1;
        for (Minion minion : table.getRow(frontLines)) {
            if (minion.isTank()) {
                tanks.add(minion);
            }
        }
        if (!tanks.isEmpty() && !attacked.isTank()) {
            res.set("cardAttacker", cardCoordsOutput(attackerX, attackerY));
            res.set("cardAttacked", cardCoordsOutput(attackedX, attackedY));
            res.put("error", "Attacked card is not of type 'Tank'.");
            outputArray.add(res);
            return;
        }

        attacker.setActed(true);
        attacked.setHealth(attacked.getHealth() - attacker.getAttackDamage());
        if (attacked.getHealth() <= 0) {
            table.getRow(attackedX).remove(attackedY);
        }
    }

    private ObjectNode cardCoordsOutput(final int x, final int y) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("x", x);
        res.put("y", y);
        return res;
    }
}
