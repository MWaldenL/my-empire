package MyEmpire.Model.Game;

/**
 * The Bank class holds the cash of the game. The cash is
 * initialized to the number of players * 2500.0
 */
public class Bank {
    private double dCash;

    /**
     * Constructor for the Bank class.
     * The cash is initialized to the number of players * 2500.0
     */
    public Bank(int nPlayers) {
        dCash = nPlayers * 2500.0;
    }

    /**
     * Get the available cash of the bank object
     * @return the amount of available cash in the bank
     */
    public double getCash() {
        return dCash;
    }

    /**
     * Updates the bank with a given amount, whether
     * positive or negative.
     * @param amount the amount to update the bank
     */
    public void updateBank(double amount) {
        dCash += amount;
    }
}
