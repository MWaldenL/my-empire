package MyEmpire.Model.Chance.ReceiveMoneyChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;

/**
 * The BankPaysPlayerChance allows the player to gain $50
 */
public class BankPaysPlayerChance extends ChanceCard implements AddCashChanceType {

    public BankPaysPlayerChance() {
        super(false);
    }

    @Override
    public void collectMoney(Bank bank, Player player) {
        bank.updateBank(-50);
        player.updateCash(50);
    }
}