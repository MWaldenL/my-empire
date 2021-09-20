package MyEmpire.Model.Tax;

import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;
import MyEmpire.Model.Game.Space;

/**
 * Luxury Tax charges a fixed price of $75 to the player.
 */
public class LuxuryTax extends Space implements Taxable {

    public LuxuryTax(String imgUrl) {
        super(imgUrl);
    }

    @Override
    public int chargeTax(Bank bank, Player player) {
        System.out.println("Moved to Luxury");
        int cash = 75;

        bank.updateBank(75);
        player.updateCash(-75);

        return cash;
    }

    @Override
    public String toString(){
        return "Luxury Tax";
    }
}
