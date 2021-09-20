package MyEmpire.Model.Chance.ReceiveMoneyChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;


/**
 * The BirthdayChance allows the player to gain $300
 */
public class BirthdayChance extends ChanceCard implements AddCashChanceType{

    public BirthdayChance() {
        super(false);
    }

    @Override
    public void collectMoney(Bank bank, Player player) {
        bank.updateBank(-300);
        player.updateCash(300);
    }
}