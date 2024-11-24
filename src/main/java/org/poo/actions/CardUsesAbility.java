package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.poo.cards.Minion;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.game.Table;


public final class CardUsesAbility extends AbstractAction {
    public CardUsesAbility(final ActionsInput actionsInput, final Game game, final ArrayNode out) {
        super(actionsInput, game, out);
    }

    @Override
    public void executeAction() {
        Table table = Table.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("command", command);
        if (table.getRow(attackerX).isEmpty() || table.getRow(attackerX).size() - 1 < attackerY) {
            return;
        }
        Minion attacker = table.getCardAt(attackerX, attackerY);
        int attackerOwner, attackedOwner;
        if (attackerX == 0 || attackerX == 1) {
            attackerOwner = 2;
        } else {
            attackerOwner = 1;
        }

        if (attackedX == 2 || attackedX == 3) {
            attackedOwner = 1;
        } else {
            attackedOwner = 2;
        }
        if (table.getRow(attackedX).isEmpty() || table.getRow(attackedX).size() - 1 < attackedY) {
            return;
        }
        Minion attacked = table.getCardAt(attackedX, attackedY);

        //  Validate for frozen minion
        if (attacker.isFrozen()) {
            res.set("cardAttacker", cardCoordsOutput(attackerX, attackerY));
            res.set("cardAttacked", cardCoordsOutput(attackedX, attackedY));
            res.put("error", "Attacker card is frozen.");
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

        if (attacker.getName().equals("Disciple")) {
            if (attackedOwner != attackerOwner) {
                res.set("cardAttacker", cardCoordsOutput(attackerX, attackerY));
                res.set("cardAttacked", cardCoordsOutput(attackedX, attackedY));
                res.put("error", "Attacked card does not belong to the current player.");
                outputArray.add(res);
                return;
            }
        }

        if (attacker.getName().equals("The Ripper")
                || attacker.getName().equals("Miraj")
                || attacker.getName().equals("The Cursed One")) {
            if (attackedOwner == attackerOwner) {
                res.set("cardAttacker", cardCoordsOutput(attackerX, attackerY));
                res.set("cardAttacked", cardCoordsOutput(attackedX, attackedY));
                res.put("error", "Attacked card does not belong to the enemy.");
                outputArray.add(res);
                return;
            }

            if (table.gotTanks(attackedOwner) && !attacked.isTank()) {
                res.set("cardAttacker", cardCoordsOutput(attackerX, attackerY));
                res.set("cardAttacked", cardCoordsOutput(attackedX, attackedY));
                res.put("error", "Attacked card is not of type 'Tank'.");
                outputArray.add(res);
                return;
            }
        }
        attacker.setActed(true);
        switch (attacker.getName()) {
            case "Disciple" -> attacked.setHealth(attacked.getHealth() + 2);
            case "Miraj" -> {
                int aux = attacker.getHealth();
                attacker.setHealth(attacked.getHealth());
                attacked.setHealth(aux);
            }
            case "The Cursed One" -> {
                int aux = attacked.getHealth();
                attacked.setHealth(attacked.getAttackDamage());
                attacked.setAttackDamage(aux);
                if (attacked.getHealth() <= 0) {
                    table.getRow(attackedX).remove(attackedY);
                }
            }
            default -> attacked.setAttackDamage(Math.max(0, attacked.getAttackDamage() - 2));
        }
    }
}
