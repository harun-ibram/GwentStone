package org.poo.players;

import lombok.Getter;
import lombok.Setter;
import org.poo.cards.Card;

@Getter
@Setter
public class Deck {
    private Card[] cards;
    private int nrCardsInDeck;
}
