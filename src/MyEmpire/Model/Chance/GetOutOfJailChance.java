package MyEmpire.Model.Chance;


import MyEmpire.Model.Game.Player;


/**
 * Allows a player to get out of jail for free.
 */
public class GetOutOfJailChance extends ChanceCard {
    public GetOutOfJailChance() {
        super(true);
    }

    public void getOutOfJail(Player player) {
        player.setJailValue(false);
    }
}