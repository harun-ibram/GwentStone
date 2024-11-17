package org.poo.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.cards.Minion;
import org.poo.checker.Checker;
import org.poo.checker.CheckerConstants;
import org.poo.fileio.Input;
import org.poo.players.Deck;
import org.poo.players.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();
        Player p1 = new Player(inputData.getPlayerOneDecks(), 1);
        Player p2 = new Player(inputData.getPlayerTwoDecks(), 2);

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode player1Data = mapper.createObjectNode();
        player1Data.put("playerIdx", p1.getSelfIdx());
        player1Data.put("nrDecks", p1.getNrDecks());
        player1Data.put("nrCardsInDeck", p1.getNrCardsInDeck());
        ArrayNode p1decks = mapper.createArrayNode();
        for (Deck deck : p1.getDecks()) {
            ArrayNode currentDeck = mapper.createArrayNode();
            for (Minion card : deck.getMinions()) {
                ObjectNode node = mapper.createObjectNode();
                node.put("mana", card.getMana());
                node.put("attackDamage", card.getAttackDamage());
                node.put("health", card.getHealth());
                node.put("description", card.getDescription());
                ArrayNode colorArray = mapper.createArrayNode();
                for (String color : card.getColors()) {
                    colorArray.add(color);
                }
                node.put("colors", colorArray);
                node.put("name", card.getName());
                currentDeck.add(node);
            }

            p1decks.add(currentDeck);
        }
        player1Data.put("decks", p1decks);
        /*
         * TODO Implement your function here
         *
         * How to add output to the output array?
         * There are multiple ways to do this, here is one example:
         *
         * ObjectMapper mapper = new ObjectMapper();
         *
         * ObjectNode objectNode = mapper.createObjectNode();
         * objectNode.put("field_name", "field_value");
         *
         * ArrayNode arrayNode = mapper.createArrayNode();
         * arrayNode.add(objectNode);
         *
         * output.add(arrayNode);
         * output.add(objectNode);
         *
         */
        output.add(player1Data);


        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
