package org.poo.players;

import lombok.Getter;
import lombok.Setter;
import org.poo.cards.*;
import org.poo.fileio.CardInput;

import java.util.ArrayList;

@Getter
@Setter
public class Deck {
    private ArrayList<Minion> minions;
    private int nrCardsInDeck;
    private int ownerIdx;

    public Deck(ArrayList<CardInput> cards, int nrCardsInDeck, int ownerIdx) {
        this.nrCardsInDeck = nrCardsInDeck;
        this.minions = new ArrayList<>();
        this.ownerIdx = ownerIdx;
        for (CardInput card : cards) {
            Minion currentMinion = switch (card.getName()) {
                case "Berserker" -> new Berserker(card);
                case "Goliath" -> new Goliath(card);
                case "Sentinel" -> new Sentinel(card);
                case "Warden" -> new Warden(card);
                case "The Ripper" -> new TheRipper(card);
                case "Miraj" -> new Miraj(card);
                case "The Cursed One" -> new TheCursedOne(card);
                case "Disciple" -> new Disciple(card);
                default -> new Minion(card);
            };
            this.minions.add(currentMinion);
        }
    }
}
