package MyEmpire.Model.Chance.TakeATripChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Board;
import MyEmpire.Model.Game.Player;

/**
 * TripToPropertyChance lets the player move to a random property. Collect money when
 * passing START.
 */
public class TripToPropertyChance extends ChanceCard implements TakeATripChanceType {

    public TripToPropertyChance() {
        super(true);
    }

    @Override
    public void takeATrip(Player player, Board board, int pos) {
        player.jumpToPos(board, pos);
        player.setJailValue(true);
    }
}
