package MyEmpire.Model.Chance.ProceedToOwnableChance;


import MyEmpire.Model.Game.Board;
import MyEmpire.Model.Game.Player;
import MyEmpire.Model.Ownable.Ownable;

public interface ProceedChanceType {

    public void proceedToOwnable(Board board, Player player, Ownable ownable);
}