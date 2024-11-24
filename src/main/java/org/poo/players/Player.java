package org.poo.players;


import lombok.Getter;
import lombok.Setter;
import org.poo.cards.Hero;
import org.poo.cards.Minion;
import org.poo.fileio.CardInput;
import org.poo.fileio.DecksInput;
import java.util.ArrayList;

@Getter
@Setter
public final class Player {
    private int nrDecks;
    private int nrCardsInDeck;
    private ArrayList<Deck> decks;
    private int selfIdx;
    private Hero hero;
    private ArrayList<Minion> cardsInHand;
    private int mana;

    public Player(final DecksInput din, final int idx) {
        nrDecks = din.getNrDecks();
        nrCardsInDeck = din.getNrCardsInDeck();
        selfIdx = idx;
        decks = new ArrayList<>();
        for (ArrayList<CardInput> cardList : din.getDecks()) {
            Deck currentDeck = new Deck(cardList, nrCardsInDeck, this.selfIdx);
            decks.add(currentDeck);
        }
        cardsInHand = new ArrayList<>();
        mana = 0;
    }

    /**\
     * Draws the first card from the deck and adds it to the player's hand.
     * @param gameDeck The deck from which the card is drawn.
     */
    public void draw(final Deck gameDeck) {
        if (gameDeck.getMinions().isEmpty()) {
            return;
        }
        cardsInHand.addLast(gameDeck.drawCard());
    }
}
