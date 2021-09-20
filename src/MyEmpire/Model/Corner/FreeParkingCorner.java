package MyEmpire.Model.Corner;

import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;


public class FreeParkingCorner extends CornerType {

    public FreeParkingCorner(String imgUrl) {
        super(imgUrl);
    }

    @Override
    public void actOnPlayer(Bank bank, Player player) {
        player.endTurn();
    }
}
