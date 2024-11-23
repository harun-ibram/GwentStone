package org.poo.game;

import lombok.Getter;
import lombok.Setter;
import org.poo.cards.Minion;

@Getter
@Setter
public class Table {
    private static final int FIVE = 5;
    private static final int FOUR = 4;
    private static Minion[][] table = new Minion[FIVE][FOUR];

    /**
     * Get the card at the given coordinates.
     * @param x Index on the row of cards.
     * @param y Index of the row.
     * @return Minion card at the requested position.
     */
    public Minion getCardAt(final int x, final int y) {
        return table[y][x];
    }

    /**
     * Place Minion card at the given coordinates.
     * @param minion The card to be placed.
     * @param x Index on the row of cards.
     * @param y Index of the row.
     */
    public void placeMinion(final Minion minion, final int x, final int y) {
        table[y][x] = minion;
    }

}
