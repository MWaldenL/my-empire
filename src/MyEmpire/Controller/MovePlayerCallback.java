package MyEmpire.Controller;

import MyEmpire.Model.Game.Player;

public interface MovePlayerCallback {
    void updatePlayer(int pos, Player player);
}
