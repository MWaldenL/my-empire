package MyEmpire.Model.Chance.ProceedToOwnableChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Board;
import MyEmpire.Model.Game.Player;
import MyEmpire.Model.Ownable.Ownable;
import MyEmpire.Model.Ownable.Property;

public class ProceedToProperty extends ChanceCard implements ProceedChanceType {

    public ProceedToProperty() {
        super(false);
    }

    @Override
    public void proceedToOwnable(Board board, Player player, Ownable ownable) {
        if (ownable instanceof Property)
            player.jumpToPos(board, ownable.getBoardPos());
    }
}