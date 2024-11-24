package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.cards.Minion;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.game.Table;

import java.util.ArrayList;

public final class PlaceCard extends AbstractAction {
    private int y;

    public PlaceCard(final ActionsInput actionsInput, final Game game, final ArrayNode out) {
        super(actionsInput, game, out);
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode comm = mapper.createObjectNode();
        comm.put("command", getCommand());
        comm.put("handIdx", getHandIdx());

        ArrayList<Minion> hand = state.getCurrentPlayer().getCardsInHand();
        if (hand.isEmpty()) {
            return;
        }
        Minion theFighter = hand.get(getHandIdx());

        if (theFighter.getMana() > state.getCurrentPlayer().getMana()) {
            comm.put("error", "Not enough mana to place card on table.");
            outputArray.add(comm);
            return;
        }
        Table table = Table.getInstance();
        if (state.getCurrentPlayer().getSelfIdx() == 1) {
            switch (theFighter.getName()) {
                case "Goliath", "Warden", "The Ripper", "Miraj" -> y = 2;
                default -> y = 3;
            }
        } else {
            switch (theFighter.getName()) {
                case "Goliath", "Warden", "The Ripper", "Miraj" -> y = 1;
                default -> y = 0;
            }
        }
        if (table.isRowFull(y)) {
            comm.put("error", "Cannot place card on table since row is full.");
            outputArray.add(comm);
            return;
        }

        table.placeMinion(theFighter, y);
        state.getCurrentPlayer().setMana(state.getCurrentPlayer().getMana() - theFighter.getMana());
        hand.remove(handIdx);
    }
}
