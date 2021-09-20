package MyEmpire.Model.Chance.ReceiveMoneyChance;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;

/**
 * This Chance card allows the player to advance to START, where he or she will
 * collect $200.
 */
public class AdvanceToStartChance extends ChanceCard implements AddCashChanceType {

    public AdvanceToStartChance() {
        super(true);
    }

    @Override
    public void collectMoney(Bank bank, Player player) {}
}
