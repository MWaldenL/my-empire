package MyEmpire.Model.Corner;

import MyEmpire.Model.Game.Bank;
import MyEmpire.Model.Game.Player;

public class CommunityServiceCorner extends CornerType {

    public CommunityServiceCorner(String imgUrl) {
        super(imgUrl);
    }

    @Override
    public void actOnPlayer(Bank bank, Player player) {
        bank.updateBank(50);
        player.updateCash(-50);
    }
}
