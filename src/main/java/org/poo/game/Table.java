package org.poo.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Getter;
import lombok.Setter;
import org.poo.cards.Minion;

import java.util.ArrayList;

@Getter
@Setter
public class Table {
    private static Table instance = null;
    private static final int cols = 5;
    private static final int rows = 4;
    private static ArrayList<ArrayList<Minion>> table;

    private Table() {

    }

    public static Table getInstance() {
        if (instance == null) {
            instance = new Table();
            table = new ArrayList<>(4);
            for (int i = 0; i < 4; i++) {
                table.add(new ArrayList<>(5));
            }
        }
        return instance;
    }
    /**
     * Get the card at the given coordinates.
     * @param x Index on the row of cards.
     * @param y Index of the row.
     * @return Minion card at the requested position.
     */
    public Minion getCardAt(final int x, final int y) {
        return table.get(y).get(x);
    }

    /**
     *
     * @param minion
     * @param row
     */
    public void placeMinion(final Minion minion, final int row) {
        table.get(row).add(minion);
    }

    public boolean isRowFull(final int row) {
        return table.get(row).size() == 5;
    }

    public ArrayNode getCardsOnTable() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode res = mapper.createArrayNode();
        for (ArrayList<Minion> row : table) {

            ArrayNode rowOutput = mapper.createArrayNode();
            if (row.isEmpty()) {
                res.add(rowOutput);
                continue;
            }

            for (Minion minion : row) {
                rowOutput.add(minion.prepareOutput());
            }
            res.add(rowOutput);
        }
        return res;
    }

    public static void resetTable() {
        if (instance == null) {
            instance = new Table();
            table = new ArrayList<>(4);
            for (int i = 0; i < 4; i++) {
                table.add(new ArrayList<>(5));
            }
        } else {
            for (ArrayList<Minion> row : table) {
                row.clear();
            }
        }
    }
}
