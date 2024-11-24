package org.poo.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import org.poo.actions.*;
import org.poo.actions.AbstractAction;
import org.poo.cards.Hero;
import org.poo.cards.Minion;
import org.poo.fileio.ActionsInput;
import org.poo.fileio.StartGameInput;
import org.poo.players.Deck;
import org.poo.players.Player;

import javax.swing.*;
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
    private int manaGain = 1;
    private int rounds = 0;
    private int turnCount = 0;

    /**
     * Switches the turn of the players.
     */
    public void switchPlayerTurn() {
        if (playerTurn == 1)
            playerTurn = 2;
        else playerTurn = 1;
        turnCount = turnCount + 1;
    }

    /**
     * Initializes the parameters of the game and picks each player's deck
     *
     * @param sgi The initial parameters used to start the game.
     */
    public void initGame(final StartGameInput sgi, ArrayNode out) {
        Table t = Table.getInstance();
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
        p1.setMana(manaGain);
        p2.setMana(manaGain);
        rounds = 1;
        outputArray = out;
    }

    public Player getCurrentPlayer() {
        return playerTurn == 1 ? p1 : p2;
    }

    public void setActions(ArrayList<ActionsInput> al) {
        ArrayList<AbstractAction> res = new ArrayList<>();
        for (ActionsInput a : al) {
            switch (a.getCommand()) {
                case "getPlayerDeck" -> res.add(new GetPlayerDeck(a, this, outputArray));
                case "getPlayerTurn" -> res.add(new GetPlayerTurn(a, this, outputArray));
                case "getPlayerHero" -> res.add(new GetPlayerHero(a, this, outputArray));
                case "endPlayerTurn" -> res.add(new EndTurn(a, this, outputArray));
                case "getCardsOnTable" -> res.add(new GetCardsOnTable(a, this, outputArray));
                case "placeCard" -> res.add(new PlaceCard(a, this, outputArray));
                case "getCardsInHand" -> res.add(new GetCardsInHand(a, this, outputArray));
                case "getPlayerMana" -> res.add(new GetPlayerMana(a, this, outputArray));
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


    public Hero getPlayerHero(final int idx) {
        return idx == 1 ? p1Hero : p2Hero;
    }


    public void nextTurn() {
        switchPlayerTurn();
        if (turnCount >= 2) {
            turnCount = 0;
            rounds = rounds + 1;
            p1.draw(p1Deck);
            p2.draw(p2Deck);
            manaGain = Math.min(10, getManaGain() + 1);
            p1.setMana(p1.getMana() + manaGain);
            p2.setMana(p2.getMana() + manaGain);
            Table.newRound();
        }
    }

    public Player getPlayer(final int idx) {
        return idx == 1 ? p1 : p2;
    }
}
