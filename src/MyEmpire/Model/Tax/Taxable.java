package MyEmpire.Model.Tax;

import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;

/**
 * Interface that charges tax to player once he or she has landed on
 * a Tax Space.
 */
public interface Taxable {
    public int chargeTax(Bank bank, Player player);
}
