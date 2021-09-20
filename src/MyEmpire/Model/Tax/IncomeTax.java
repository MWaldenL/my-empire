package MyEmpire.Model.Tax;

import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;
import MyEmpire.Model.Game.Space;

/**
 * Income Tax charges tax to the player, specifically, $200 or
 * 10% of the player's cash.
 */
public class IncomeTax extends Space implements Taxable {

    public IncomeTax(String imgUrl) {
        super(imgUrl);
    }

    @Override
    public int chargeTax(Bank bank, Player player) {
        System.out.println("Moved to Income");
        int cashToCompare = (int) (player.getCash() * 0.1);

        if (cashToCompare > 200) {
            bank.updateBank(cashToCompare);
            player.updateCash(-cashToCompare);
        } else {
            cashToCompare = 200;
            bank.updateBank(cashToCompare);
            player.updateCash(-cashToCompare);
        }

        return cashToCompare;
    }

    @Override
    public String toString(){
        return "Income Tax";
    }
}
