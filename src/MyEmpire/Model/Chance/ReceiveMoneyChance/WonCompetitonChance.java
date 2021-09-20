package MyEmpire.Model.Chance.ReceiveMoneyChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;


/**
 * The WonCompetitonChance allows the player to gain $150
 */
public class WonCompetitonChance extends ChanceCard implements AddCashChanceType {

    public WonCompetitonChance() {
        super(false);
    }

    @Override
    public void collectMoney(Bank bank, Player player) {
        bank.updateBank(-150);
        player.updateCash(150);
    }
}
