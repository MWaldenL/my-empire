package MyEmpire.Model.Chance.ReceiveMoneyChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;

/**
 * The TaxRefundChance allows the player to gain $100
 */
public class TaxRefundChance extends ChanceCard implements AddCashChanceType {

    public TaxRefundChance() {
        super(false);
    }

    @Override
    public void collectMoney(Bank bank, Player player) {
        bank.updateBank(-100);
        player.updateCash(100);
    }
}