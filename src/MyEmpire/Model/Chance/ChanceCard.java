package MyEmpire.Model.Chance;

import MyEmpire.Model.Game.ChanceDeck;

/**
 * Chance cards provide the game with more energy than usual,
 * allowing for temporary or permanent modifications in rules that
 * give players a boost or decline in their in-game statuses.
 * There are six (6) groups of Chance cards, namely, GiveMoneyChance,
 * ProceedToOwnableChance, ReceiveMoneyChance, RentChance, TakeATripChance,
 * and GetOutOfJailChance. When drawing a chance card, players may either use
 * it if immediately applicable, or keep it. In situations where a card cannot be used,
 * the player may choose to discard it.
 */
public class ChanceCard  {
    protected int nCardCount;
    protected boolean keepable;

    /**
     * Checks if a card can be kept for future use,
     * @param keepable
     */
    public ChanceCard(boolean keepable) {
        this.keepable = keepable;
    }

    /**
     * Discards this chance card into the discard pile
     * of the game state's chance deck.
     */
    public void discard(ChanceDeck deck) {
        deck.addToDiscardPile(this);
    }

    /**
     * Checks if a card can be kept.
     * @return
     */
    public boolean isKeepable() {
        return keepable;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}