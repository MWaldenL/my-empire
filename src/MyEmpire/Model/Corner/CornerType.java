package MyEmpire.Model.Corner;

import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;
import MyEmpire.Model.Game.Space;


/**
 * The CornerType Space consists of the following:
 * START, JAIL, Community Service, and Free Parking.
 */
public abstract class CornerType extends Space {

    /**
     * Constructor of the CornerType. Sets the image url to
     * be used in the controller.
     * @param imgUrl image url to be used in the controller.
     */
    public CornerType(String imgUrl) {
        super(imgUrl);
    }

    /**
     * Performs an action on the player depending on the space.
     * Either collects money from the player, gives money to the player,
     * or ends the player's turn.
     * @param bank the bank object of the game
     * @param player the player who lands on this space.
     */
    public abstract void actOnPlayer(Bank bank, Player player);

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
