package MyEmpire.Model.Chance.TakeATripChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Board;
import MyEmpire.Model.Game.Player;

/**
 * GoToJailChance lets the player go to JAIL.
 */
public class GoToJailChance extends ChanceCard implements TakeATripChanceType {

    public GoToJailChance() {
        super(true);
    }

    @Override
    public void takeATrip(Player player, Board board, int dest) {
        player.jumpToPos(board, 24); // 24 is the index of GoToJail
        player.setJailValue(true);
        player.endTurn();
    }
}
