package MyEmpire.Model.Chance.ReceiveMoneyChance;


import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;

/**
 * AddCashChanceType is an abstract class that deals with Chance Cards
 * whose main goal is supplying the player with cash. There are five cards of
 * this type, namely, BankPaysPlayerChance, BirthdayChance, StartChance,
 * TaxRefundChance, and WonCompetitionChance. There are a total of six cards in
 * this group in the chance deck.
 */
public interface AddCashChanceType {

    /**
     * An abstract method that allows the player to collect money.
     */
    public void collectMoney(Bank bank, Player player);
}