

# GwentStone
##### Harun Ibram


## Project Structure


* `src/main/java`
  * `actions/` - contains classes for the possible actions in the game
  * `cards/` - contains classes for the different Card types in the game
  * `checker/` - checker files
  * `fileio/` - contains classes used to read data from the json files
  * `game/` - contains classes for the game logic and representation of the table
  * `main/`
      * `Main` - the Main class runs the checker on your implementation. Add the entry point to your implementation in it. Run Main to test your implementation from the IDE or from command line.
      * `Test` - run the main method from Test class with the name of the input file from the command line and the result will be written
        to the out.txt file. Thus, you can compare this result with ref.
* `input/` - contains the tests in JSON format
* `players/` - contains classes for the Player and the representation of the Deck of Cards
* `ref/` - contains all reference output for the tests in JSON format


## `players/`
This package represents a player and their deck of cards.
### `players/Player.java`:
  This class represents a player's information:
#### Fields:
* `int nrDecks` - the number of decks the player has
* `int nrCardsInDeck` - the number of cards each deck contains
* `int decks` - all the decks the player owns
* `int selfIdx` - the identity of the player (1 or 2)
* `Hero hero` - the player's hero card
* `ArrayList<Minion> cardsInHand` - the cards drawn from the deck and not placed yet
* `int mana` - the current amount of mana of the player

#### Methods:
* Getters and Setters for all the fields.
* `public Player(DecksInput, int)` - constructor from an input of decks for the current player 
and the player's identity
* `public void draw(Deck)` - draws the first card from the given deck and adds it to the player's hand



### `players/Deck.java`:
  This class represents a deck's information:
#### Fields:
* `ArrayList<Minion> minions` - the actual list of cards in the deck
* `int nrCardsInDeck` - the number of cards currently in the deck
* `int ownerIdx` - the identity of the player who owns the deck (1 or 2)

#### Methods:
* `public Deck(ArrayList<CardInput>, int, int)` - constructor from a list of `CardInput`s, the number of cards in the deck, and its owner's identity
* `public Deck(Deck)` - copy constructor
* `public void shuffleDeck(int)` - shuffles the cards in the deck based on a `Random` integer with the given seed
* `public Minion drawCard()` - draws a card from the present deck and returns it
* `public ArrayNode prepareOutput()` - creates the JSON array of the cards for the output


## `game/`
  This package represents the main logic of the game and its form as a table.
### `game/Game.java`:
  This class represents the main game logic:

#### Fields:
* `Player p1`, `Player p2` - the Players of the current game
* `Deck p1Deck`, `Deck p2Deck` - the decks the players are using in the current game
* `Hero p1Hero`, `Hero p2Hero` - the hero cards the players are using in the current game
* `int playerTurn` - the identity of the player that can act in the current turn
* `int shuffleSeed` - the seed of randomness used to shuffle the decks at the beginning of the game
* `ArrayList<AbstractAction> actions` - the list of actions that are done in the current game
* `ArrayNode outputArray` - a reference to the output array
* `int manaGain` - the amount of mana that is currently being gained per round
* `int rounds` - the number of rounds played at a given moment
* `int turnCount` - the number of turns that have taken place in the current round

#### Methods:
* `public void initGame(StartGameInput, ArrayNode)` - initializes all the properties of the current game and sets the output array
* `public void switchPlayerTurn()` - gives the next player the permission to act
* `public Player getCurrentPlayer()` - obtains the Player who is currently allowed to act
* `public void setActions(ArrayList<ActionsInput>)` - creates the queue of actions to be done in the current game using the Factory pattern
* `public void executeActions()` - executes the actions of the current game in the given order
* `public Deck getPlayerDeck(int)` - obtains the deck in play of the requested player
* `public Hero getPlayerHero(int)` - obtains the hero card in play for the requested player
* `public void nextTurn()` - prepares the next turn and, respectively, round of the game, by switching the turn of players, adding mana and drawing a card for each of them, if possible
* `public Player getPlayer(int)` - obtains the Player requested

### `game/Table.java`:
  This is a Singleton class representing the game table.
I have decided to make it a Singleton because there is only one table that the game is being played on, at any given moment.

#### Fields:
* `static Table instance` - the instance of the Singleton class Table
* `static final int ROWS` - the constant number of rows that the table has
* `static final int COLS` - the constant number of cards that can be placed on each row
* `static ArrayList<ArrayList<Minion>> table` - stores the Minions that are currently in play

#### Methods:
* `public static Table getInstance()` - obtains a reference to the table's instance
* `public Minion getCardAt(int, int)` - obtains the card that is placed at the given coordinates
* `public ArrayList<Minion> getRow(int)` - obtains the minions currently placed on the given row
* `public void placeMinion(Minion, int)` - adds the given Minion to the requested row
* `public boolean isRowFull(int)` - verifies the requested row for space available for another minion
* `public ArrayNode getCardsOnTable()` - creates the JSON output form for the table
* `public static void resetTable()` - resets all the table properties for a new game
* `public static void newRound()` - ensures that all minions on the table can attack again in the new round
* `public boolean gotTanks(int)` - checks the given player's rows for tanks


## `cards/`
  This package contains classes representing all types of cards available in the game, based on a GeneralCard.
There are Minion cards and Hero cards.
#### Possible Minion Card Types:
  * `Berserker`
  * `Disciple`
    * `Special Ability` - Adds +2 Health to an allied unit.
  * `Goliath`
    * Tank
  * `Miraj`
    * `Special Ability` - Swaps its own Health with an enemy's.
  * `Sentinel`
  * `The Cursed One` 
    * `Special Ability` - Swaps the Health and AttackDamage of an enemy.
  * `The Ripper`
    * `Special Ability` - Reduces an enemy's AttackDamage by -2.
  * `Warden`
    * Tank
#### Hero Cards
  - Heroes also have a special ability.
  - They are not Minions, so they cannot be placed on the game table.
  - Each Player only has 1 Hero card, given at the beginning of the game.


## `actions/`
  This package contains classes representing the possible actions that can take place in a game, based on an AbstractAction.
#### Possible actions:
  * `GetPlayerDeck` - prints a player's deck
  * `GetPlayerMana` - prints a player's mana
  * `GetPlayerHero` - prints a player's hero card details
  * `GetCardsInHand` - prints the details of all cards that the player holds
  * `GetCardsOnTable` - prints the details of all cards that are placed on the game table
  * `GetCardAtPosition` - prints the details of the card at the specified position
  * `PlaceCard` - places a Minion on the table for the current player
  * `EndTurn` - ends the turn of the current player
  * `CardUsesAttack` - the card uses its AttackDamage to harm an enemy unit
  * `CardUsesAbility` - the card uses its Special Ability on a unit
