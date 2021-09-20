package MyEmpire.Model.Chance.ProceedToOwnableChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Board;
import MyEmpire.Model.Game.GameData;
import MyEmpire.Model.Game.Player;
import MyEmpire.Model.Ownable.Ownable;
import MyEmpire.Model.Ownable.Utility;


/**
 * 
 */
public class ProceedToUtility extends ChanceCard implements ProceedChanceType {

    public ProceedToUtility() {
        super(false);
    }

    @Override
    public void proceedToOwnable(Board board, Player player, Ownable ownable) {
        Player owner;
        int toPay;

        if (ownable instanceof Utility) {
            player.jumpToPos(board, ownable.getBoardPos());

            owner = ownable.getOwner();

            if (owner != null) { // if has owner
                if (owner != player) {
                    toPay = GameData.rollDice() * 10;
                    owner.updateCash(toPay);
                    player.updateCash(-toPay);
                }
            }
        }
    }
}