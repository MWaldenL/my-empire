package MyEmpire.Model.Game;


/**
 * Spaces are the life of the game. These make up the board,
 * and have their own effects on the players and the overall game.
 * Each space can be one of four (4) types, namely, Ownables, Corners,
 * Tax, and Chance. Each space also has a list to contain the players
 * currently sitting on it. At the start of the game, the players are
 * given the freedom to choose the individual positions of each space.
 */

import java.util.ArrayList;

public abstract class Space {
    protected ArrayList<Player> playerList;
    protected int boardPos;
    protected String imgUrl;

    public Space(String imgUrl) {
        playerList = new ArrayList<Player>();
        this.imgUrl = imgUrl;
    }

    public String getType() {
        return getClass().getSimpleName();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Returns the list of players currently on this space
     * @return the list of players currently on this space
     */
    public ArrayList<Player> getPlayers() {
        return playerList;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public void removePlayer(Player player) {
        playerList.remove(player);
    }

    /**
     * Returns the last player that landed on the space
     * @return the last player that landed on the space
     */
    public Player getLastPlayerLanded() {
        return playerList.get(playerList.size() - 1);
    }


    /**
     * Returns the position of this space on the board
     * @return the board position of this space
     */
    public int getBoardPos() {
        return boardPos;
    }

    public void setBoardPos(int pos) {
        this.boardPos = pos;
    }
}