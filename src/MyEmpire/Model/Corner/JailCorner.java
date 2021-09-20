package MyEmpire.Model.Corner;

import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;


public class JailCorner extends CornerType {

    public JailCorner(String imgUrl) {
        super(imgUrl);
    }

    @Override
    public void actOnPlayer(Bank bank, Player player) {
        player.setJailValue(true);
        player.endTurn();
    }
}
