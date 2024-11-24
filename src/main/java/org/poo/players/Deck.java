package org.poo.players;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Getter;
import lombok.Setter;
import org.poo.cards.Minion;
import org.poo.cards.Goliath;
import org.poo.cards.Berserker;
import org.poo.cards.Disciple;
import org.poo.cards.Miraj;
import org.poo.cards.Sentinel;
import org.poo.cards.TheCursedOne;
import org.poo.cards.TheRipper;
import org.poo.cards.Warden;
import org.poo.fileio.CardInput;

import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

@Getter
@Setter
public final class Deck {
    private ArrayList<Minion> minions;
    private int nrCardsInDeck;
    private int ownerIdx;

    public Deck(final ArrayList<CardInput> cards, final int nrCardsInDeck, final int ownerIdx) {
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

    public Deck(final Deck original) {
        nrCardsInDeck = original.getNrCardsInDeck();
        ownerIdx = original.getOwnerIdx();
        minions = new ArrayList<>();
        for (Minion m : original.getMinions()) {
            Minion copy = new Minion(m);
            minions.add(copy);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Minion minion : minions) {
            sb.append(minion);
            sb.append(",\n");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    /**
    * Shuffles the deck using the given shuffleSeed.
    * @param shuffleSeed The seed used to shuffle the deck.
    */
    public void shuffleDeck(final int shuffleSeed) {
        Collections.shuffle(minions, new Random(shuffleSeed));
    }

    /**
     * Removes the requested card from the deck.
     * @param idx The card to be removed.
     */
    public void removeCard(final int idx) {
        minions.remove(idx);
    }

    /**
     * Draws the first card from the deck.
     * This operation removes the card from the deck.
     * @return The Minion card at the top of the deck.
     */
    public Minion drawCard() {
        return minions.removeFirst();
    }

    public ArrayNode prepareOutput() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode res = mapper.createArrayNode();
        for (Minion minion : minions) {
            res.add(minion.prepareOutput());
        }

        return res;
    }
}
