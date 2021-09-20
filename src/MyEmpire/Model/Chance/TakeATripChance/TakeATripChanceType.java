package MyEmpire.Model.Chance.TakeATripChance;

import MyEmpire.Model.Game.Board;
import MyEmpire.Model.Game.Player;

/**
 * TakeATripChanceType is an interface that lets the player move to a given position in
 * the board, either a property or JAIL.
 */
public interface TakeATripChanceType {

    /**
     * Take a trip to a given position on the board.
     * @param player the player who drew this card
     * @param board the board of the game
     * @param pos the position of the square on the board.
     */
    public void takeATrip(Player player, Board board, int pos);
}
