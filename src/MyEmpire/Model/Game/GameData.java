package MyEmpire.Model.Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Singleton class containing the Game Data.
 * The Game contains the following attributes:
 * Number of players, list of players, board object,
 * chance deck, bank.
 */
public class GameData {
    private int nPlayerCount;
    private ArrayList<Player> playerList;
    private Board board;
    private ChanceDeck chanceDeck;
    private Bank nBank;

    // DEMO
    private static int turnCount;

    private static GameData gameDataObj = null;

    /**
     * Initializes a game.
     * The player list, board, chance deck, and
     * bank are initialized. The bank's initial cash is
     * number of players * 2500.
     */
    private GameData(int nPlayers) {
        nPlayerCount = nPlayers;
        playerList = new ArrayList<Player>();
        board = new Board();
        chanceDeck = new ChanceDeck();
        nBank = new Bank(nPlayers);
        turnCount = 1;
    }

    /**
     * Initializes the GameData instance to be obtained in the
     * future.
     * @param nPlayers the number of players in the game
     */
    public static void initInstance(int nPlayers) {
        gameDataObj = new GameData(nPlayers);
    }

    /**
     * Gets the GameData instance
     * @return the GameData instance to be used
     */
    public static GameData getInstance() {
        return gameDataObj;
    }


    /**
     * Adds a player to the player list of this class
     */
    public void addPlayer(Player player) {
        playerList.add(player);
    }


    /**
     * Checks if the game has ended. The game ends if either
     * a player is bankrupt, bank is bankrupt, or a player has
     * two full sets
     * @return if the game has ended
     */
    public boolean isEndGame() {
        boolean isPlayerDown = false;
        boolean isPlayerWin = false;

        for (Player player : playerList) {
            if (player.isBankrupt())
                isPlayerDown = true;

            if (player.getNumFullSets() == 2)
                isPlayerWin = true;
        }

        return isPlayerDown || nBank.getCash() <= 0 || isPlayerWin;
    }


    /**
     * Returns the number of players in this game state.
     * @return the number of players in this game state.
     */
    public int getPlayerCount() {
        return nPlayerCount;
    }


    /**
     * Returns the list of players in this game
     * @return the list of players in this game
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }


    /**
     * Returns the board state of this game
     * @return the board state of this game
     */
    public Board getBoard() {
        return board;
    }


    /**
     * Returns the chance deck of this game
     * @return the chance deck of this game
     */
    public ChanceDeck getChanceDeck() {
        return chanceDeck;
    }


    /**
     * Returns the bank state of this game
     * @return the bank state of this game
     */
    public Bank getBank() {
        return nBank;
    }


    /**
     * Places a space object in the given board position.
     * Used when prompting players to set up the board
     */
    public void setBoard(Space space, int pos) {
        space.setBoardPos(pos % 32);
        board.setSpace(pos, space);
    }

    /**
     * Simulates a dice roll.
     * Returns a random value from 2 to 12.
     * @return a random value from 2 to 12.
     */
    // DEMO VERSION
    public static int rollDice() {
        return new Random().nextInt(5)+1;
//        if (turnCount == 1)
//            return 1;
//        else if (turnCount == 2)
//            return 2;
//        else if (turnCount == 3)
//            return 3;
//        else return 4;
    }

    // DEMO VERSION
    public static void increaseTurnCount() {
        turnCount++;
    }
}
