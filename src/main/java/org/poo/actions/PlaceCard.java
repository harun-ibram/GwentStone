package org.poo.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.cards.Minion;
import org.poo.fileio.ActionsInput;
import org.poo.game.Game;
import org.poo.game.Table;
import org.poo.players.Player;

import java.util.ArrayList;

public class PlaceCard extends AbstractAction {
    private int y;

    public PlaceCard(ActionsInput actionsInput, final Game game, final ArrayNode output) {
        super(actionsInput, game, output);
    }

    @Override
    public void executeAction() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode comm = mapper.createObjectNode();
        comm.put("command", getCommand());
        comm.put("handIdx", getHandIdx());

        ArrayList<Minion> hand = getState().getCurrentPlayer().getCardsInHand();
        Minion theFighter = hand.get(getHandIdx());

        if (theFighter.getMana() > getState().getCurrentPlayer().getMana()) {
            comm.put("error", "Not enough mana to place card on table.");
            outputArray.add(comm);
            return;
        }
        Table table = Table.getInstance();
        if (table.isRowFull(y)) {
            comm.put("error","Cannot place card on table since row is full.");
            outputArray.add(comm);
            return;
        }
        if (getState().getCurrentPlayer().getSelfIdx() == 1) {
            switch (theFighter.getName()) {
                case "Sentinel", "Berserker" -> y = 3;
                case "Goliath", "Warden" -> y = 2;
            }
        } else {
            switch (theFighter.getName()) {
                case "Sentinel", "Berserker" -> y = 0;
                case "Goliath", "Warden" -> y = 1;
            }
        }

        table.placeMinion(theFighter, y);
        getState().getCurrentPlayer().setMana(getState().getCurrentPlayer().getMana() - theFighter.getMana());
    }
}
