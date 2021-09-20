package MyEmpire.Model.Chance.ProceedToOwnableChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Board;
import MyEmpire.Model.Game.Player;
import MyEmpire.Model.Ownable.Ownable;
import MyEmpire.Model.Ownable.Railroad;

public class ProceedToRailroad extends ChanceCard implements ProceedChanceType {

    public ProceedToRailroad() {
        super(false);
    }

    @Override
    public void proceedToOwnable(Board board, Player player, Ownable ownable) {
        Player owner;

        if (ownable instanceof Railroad) {
            player.jumpToPos(board, ownable.getBoardPos());
            owner = ownable.getOwner();

            if (owner != null)
                if (owner != player)
                    player.payRent(owner, ownable);
        }
    }
}