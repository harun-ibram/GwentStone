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
public class Player {
    private int nrDecks;
    private int nrCardsInDeck;
    private ArrayList<Deck> decks;
    private int selfIdx;
    private Hero hero;
    private ArrayList<Minion> cardsInHand;
    private int mana;

    public Player(final DecksInput din, final int selfIdx) {
        nrDecks = din.getNrDecks();
        nrCardsInDeck = din.getNrCardsInDeck();
        this.selfIdx = selfIdx;
        decks = new ArrayList<>();
        for (ArrayList<CardInput> cardList : din.getDecks()) {
            Deck currentDeck = new Deck(cardList, nrCardsInDeck, this.selfIdx);
            decks.add(currentDeck);
        }
        cardsInHand = new ArrayList<>();
        mana = 0;
    }

    public void draw(Deck gameDeck) {
        if (gameDeck.getMinions().isEmpty())
            return;
        cardsInHand.addLast(gameDeck.drawCard());
    }
}
