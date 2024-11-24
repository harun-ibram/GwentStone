package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.cards.Minion;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.game.Table;

import javax.swing.*;
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
        Minion attacker = table.getCardAt(attackerY, attackerX);
        int attackerOwner, attackedOwner;
        if (attackerX == 0 || attackerX == 1) {
            attackerOwner = 2;
        } else attackerOwner = 1;

        if (attackedX == 2 || attackedX == 3) {
            attackedOwner = 1;
        } else attackedOwner = 2;
        Minion attacked = table.getCardAt(attackedY, attackedX);

        //  Validate for team damage
        if (attackedOwner == attackerOwner) {
            res.put("error", "Attacked card does not belong to the enemy.");
            outputArray.add(res);
            return;
        }

        //  Validate for action in current round
        if (attacker.isActed()) {
            res.put("error", "Attacker card has already attacked this turn.");
            outputArray.add(res);
            return;
        }

        //  Validate for frozen minion
        if (attacker.isFrozen()) {
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
        if (!tanks.isEmpty() && !tanks.contains(attacked)) {
            res.put("error", "Attacked card is not of type 'Tank’.");
            outputArray.add(res);
            return;
        }

        attacker.setActed(true);
        attacked.setHealth(attacked.getHealth() - attacker.getAttackDamage());
        if (attacked.getHealth() <= 0) {
            table.getRow(attackedX).remove(attackedY);
        }
    }
}
