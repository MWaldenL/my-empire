package MyEmpire.Model.Corner;

import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;

/**
 * The START Corner gives the player $200
 */
public class StartCorner extends CornerType {

    public StartCorner(String imgUrl) {
        super(imgUrl);
    }

    @Override
    public void actOnPlayer(Bank bank, Player player) {
        bank.updateBank(-200);
        player.updateCash(200);
    }
}
