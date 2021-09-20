package MyEmpire.Model.Chance.GiveMoneyChance;


import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;

import java.util.Random;

/**
 * The GiveMoneyChanceType chance card requires the player to give
 * a random amount of cash to the bank. The implementation is exactly the
 * same for all children
 */
public abstract class GiveMoneyChanceType extends ChanceCard {
    public GiveMoneyChanceType() {
        super(false);
    }

    /**
     * Requires the player to give a random amount of cash to the bank
     * @param bank the bank of the game
     * @param player the player who drew this card.
     */
    public void giveMoney(Bank bank, Player player) {
        Random rand = new Random();
        int amount = rand.nextInt(500);

        bank.updateBank(amount);
        player.updateCash(-amount);
    }
}
