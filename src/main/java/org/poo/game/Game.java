package org.poo.game;

import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Getter;
import lombok.Setter;
import org.poo.actions.AbstractAction;
import org.poo.actions.GetPlayerHero;
import org.poo.actions.GetPlayerTurn;
import org.poo.cards.Hero;
import org.poo.cards.Minion;
import org.poo.fileio.ActionsInput;
import org.poo.fileio.StartGameInput;
import org.poo.players.Deck;
import org.poo.players.Player;
import org.poo.actions.GetPlayerDeck;
import java.util.ArrayList;

@Getter
@Setter
public final class Game {
    private Player p1;
    private Player p2;
    private Deck p1Deck;
    private Deck p2Deck;
    private Hero p1Hero;
    private Hero p2Hero;
    private int playerTurn;
    private int shuffleSeed;
    private ArrayList<AbstractAction> actions;
    private ArrayNode outputArray;

    /**
     * Switches the turn of the players.
     */
    public void endPlayerTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
    }

    /**
     * Initializes the parameters of the game and picks each player's deck
     *
     * @param sgi The initial parameters used to start the game.
     */
    public void initGame(final StartGameInput sgi, ArrayNode out) {
        playerTurn = sgi.getStartingPlayer();
        shuffleSeed = sgi.getShuffleSeed();
        p1Deck = new Deck(p1.getDecks().get(sgi.getPlayerOneDeckIdx()));
        p2Deck = p2.getDecks().get(sgi.getPlayerTwoDeckIdx());
        p1Hero = new Hero(sgi.getPlayerOneHero());
        p2Hero = new Hero(sgi.getPlayerTwoHero());
        p1Deck.shuffleDeck(shuffleSeed);
        p2Deck.shuffleDeck(shuffleSeed);
        p1.draw(p1Deck);
        p2.draw(p2Deck);
        outputArray = out;
    }

    public void setActions(ArrayList<ActionsInput> al) {
        ArrayList<AbstractAction> res = new ArrayList<>();
        for (ActionsInput a : al) {
            switch (a.getCommand()) {
                case "getPlayerDeck" -> res.add(new GetPlayerDeck(a, getPlayerDeck(a.getPlayerIdx()), outputArray));
                case "getPlayerTurn" -> res.add(new GetPlayerTurn(a, playerTurn, outputArray));
                case "getPlayerHero" -> res.add(new GetPlayerHero(a, getPlayerHero(a.getPlayerIdx()), outputArray));
                default -> {
                    continue;
                }
            }
        }
        actions = res;
    }

    public void executeActions() {
        for (AbstractAction action : actions) {
            action.executeAction();
        }
    }

    /**
     * Returns the deck in play for the requested player.
     * @param playerIdx Numerical identifier for the requested player's deck. -- 1 or 2
     * @return The deck the player is using.
     */
    public Deck getPlayerDeck(final int playerIdx) {
        return playerIdx == 1 ? p1Deck : p2Deck;
    }

    /**
     * Places the specified card from the hand of the current player.
     * @param handIdx The index of the card that the player wants to place on the table.
     */
    public void placeCard(final int handIdx) {
        switch (getPlayerTurn()) {
            case 1 -> {
                Minion newFighter = p1.getCardsInHand().get(handIdx);
                p1Deck.removeCard(handIdx);

            }
            case 2 -> {}
            default -> {}
        }
    }

    public Hero getPlayerHero(final int idx) {
        return idx == 1 ? p1Hero : p2Hero;
    }

    //TODO: Some sort of nextTurn() function to get to the next turn
    //TODO: Add the rest of the possible actions
}
