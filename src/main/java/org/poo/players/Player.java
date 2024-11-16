package org.poo.players;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private int nrDecks;
    private int nrCardsInDeck;
    private Deck[] decks;
}
