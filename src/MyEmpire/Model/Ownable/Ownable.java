package MyEmpire.Model.Ownable;

import MyEmpire.Model.Game.Player;
import MyEmpire.Model.Game.Space;

/**
 * The Ownable class is an abstract class for all assets
 * that can be owned by a player and rented to other players.
 * The three subclasses of this Ownable abstract class are 
 * Property, Utility, and Railroad, which each implement 
 * their own form of rent. Every Ownable has a name, color, 
 * price, and abstract rent method.
 */
public abstract class Ownable extends Space {
    protected int nInitialPrice;
    protected int nRentPrice;

    protected String strName;
    protected Color color;
    protected Player pOwner;


    public Ownable(String name, Color color, String imgUrl) {
        super(imgUrl);
        strName = name;
        this.color = color;
    }


    /**
     * Returns the name of the ownable
     * @return the name of the ownable
     */
    public String getName() {
        return strName;
    }


    /**
     * Returns the color of the ownable
     * @return the color of the ownable
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * Returns the price of the ownable
     * @return the price of the ownable
     */
    public int getPrice() {
        return nInitialPrice;
    }


    /**
     * Returns the owner of the ownable
     * @return the owner of the ownable
     */
    public Player getOwner() {
        return pOwner;
    }


    /**
     * Returns the rent price of the ownable
     * @return the rent price of the ownable
     */
    public abstract int getRentPrice();


    /**
     * Sets the owner of the ownable.
     * This is performed on a player purchase.
     * @param player the player who will own the ownable 
     */
    public void setOwner(Player player) {
        pOwner = player;
    }

    /**
     * Returns the number of same color ownables a player owns
     * @return the number of same color ownables a player owns
     */
    public int getSameCount() {
        return pOwner.getOwnableListByColor(color).size();
    }


    /**
     * Overrides the rent value with a multiplier. Used in RentChanceCards
     * @param multiplier
     */
    public void chanceOverrideRent(double multiplier) {
        nRentPrice *= (1 + multiplier);
    }

    /**
     * Computes the value of the Ownable based on buying price and developments if present
     * @return the value of the Ownable
     */
    public abstract double getValue();
}