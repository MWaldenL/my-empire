package MyEmpire.Model.Ownable;


import java.util.ArrayList;

/**
 * Properties have different colors, and are feature-richer ownables.
 * Properties can be developed by building houses and hotels. Rent is based on
 * the number of houses a property has.
 */
public class Property extends Ownable {
    private int nBuildings;
    private int nHousePrice;
    private int nHotelPrice;
    private int[] aRentList;
    private double dMultiplier;
    private int nFootTraffic;
    private boolean isRentDoubled;
    private int nDoubleRentCardCount;


    /**
     * Constructor for Property class. Initial rent price is set to the
     * default, non-developed price (i.e. the first rent value in the rentList array).
     * Consequently, the number of buildings is initialized to 0.
     * The current foot traffic is temporary - the number of players are hardcoded.
     * The number of players must be obtained from the Game.
     *
     * @param name the name of the property
     * @param color the color of the property
     * @param initPrice the initial buying price of the property
     * @param housePrice the price of each house to be developed in the property
     * @param rentList the rent price list for this property, arranged in ascending order
     *                  from unimproved to fully developed
     * @param multiplier the multiplier used for computing the foot traffic
     */
    public Property(String name, Color color, int initPrice, int housePrice, int[] rentList, double multiplier,
                    String imgUrl) {
        super(name, color, imgUrl);
        nInitialPrice = initPrice;

        aRentList = rentList;
        nRentPrice = aRentList[0];

        nHousePrice = nHotelPrice = housePrice;
        dMultiplier = multiplier;

        nBuildings = 0;
        nFootTraffic = 0;
        isRentDoubled = false;
        nDoubleRentCardCount = 0;
    }


    /**
     * Returns the property's current number of buildings
     * @return the current number of buildings price of the property
     */
    public int getBldgCount() {
        return nBuildings;
    }


    /**
     * Returns the property's current foot traffic
     * Foot traffic is defined as the Math.ceil(m * np), where
     * m is the multiplier, and np is the number of players in the
     * game.
     *
     * @return the foot traffic of the property
     */
    public double getFootTrafficLimit(int numPlayers) {
        return Math.ceil(dMultiplier * numPlayers);
    }

    public double getCurrentFootTraffic() {
        return Math.ceil(dMultiplier * nFootTraffic);
    }

    /**
     * Returns the price of a house to be built on a given property
     * @return the house price of the property
     */
    public int getHousePrice() {
        return nHousePrice;
    }

    /**
     * Returns the price of a hotel to be built on a given property
     * @return the hotel price of the property
     */
    public int getHotelPrice() {
        return nHotelPrice;
    }

    public void increaseFootTraffic() {
        nFootTraffic++;
    }

    /**
     * Sets the rent price of the property based on
     * how many of the same color player has.
     */
    @Override
    public int getRentPrice() {
        if (pOwner != null) {
            int nSameProperties = pOwner.getOwnableListByColor(color).size();

            if (nSameProperties == 2)
                nRentPrice += 10;
            else if (nSameProperties == 3)
                nRentPrice += 20;
        }

        return nRentPrice;
    }

    /**
     * Checks whether this property is fully developed
     * @return whether the number of buildings (houses) equals 4.
     */
    public boolean isFullyDeveloped() {
        return nBuildings == 4;
    }


    /**
     * Checks whether a hotel can be built on this property.
     * A hotel can only be built on a property if
     * all properties of the same color are fully developed.
     * @return whether a hotel can be built
     */
    public boolean canBuildHotel() {
        ArrayList<Ownable> propertyList = getOwner().getOwnableListByColor(color);
        for (Ownable ownable : propertyList) {
            if (ownable instanceof Property)
                if ( !((Property) ownable).isFullyDeveloped() )
                    return false;
        }

        return true;
    }


    /**
     * Checks if the property has a hotel built
     * @return whether the property has a hotel built
     */
    public boolean hasHotel() {
        return nBuildings == aRentList.length - 1;
    }


    /**
     * Allows the player to develop his or her property
     * by building houses and hotels. The rent price is accessed in
     * the rent list with the number of buildings as the index.
     */
    public void build() {
        if (canBuildHotel() && nBuildings < 5)
            nBuildings++;
        else {
            if (nBuildings < 4)
                nBuildings++;
        }

        nRentPrice = aRentList[nBuildings];
    }


    /**
     * Overrides the building prices based on the DevelopedChanceCard
     * for propertiess
     */
    public void chanceOverrideBuildings() {
        nHousePrice = 25;
        nHotelPrice = 50;
    }

    /*
     * Sets the rent to double
     */
    public void applyDoubleRent() {
        isRentDoubled = true;
    }

    /**
     * Checks if a double rent card is applied
     * @return
     */
    public boolean isRentDoubled() {
        return nDoubleRentCardCount > 0;
    }

    /**
     * Stacks a double rent card on this property
     */
    public void stackDoubleRentCard() {
        nDoubleRentCardCount++;
    }

    /**
     * Removes a double rent card on this property once a player has paid
     */
    public void popDoubleRentCard() {
        if (nDoubleRentCardCount > 0)
            nDoubleRentCardCount--;
    }

    /**
     * Gets the number of Double Rent Cards on
     * this property.
     * @return the number of Double Rent Cards on this property
     */
    public int getNumDoubleRent() {
        return nDoubleRentCardCount;
    }


    @Override
    public double getValue(){
        return (double) (nInitialPrice + (nBuildings * nHousePrice));
    }

    @Override
    public String toString() {
        String owner = (getOwner() != null) ? String.valueOf(getOwner()) : "None";
        String out = strName +
                ", Owner: " + owner;
        out+= "\tPrice: " + nInitialPrice;
        out += "\nRent: $" + getRentPrice();

        if (!hasHotel())
            out += "\nNumber of houses: " + nBuildings +
                    "\nNumber of hotels: 0";
        else out += "\nNumber of houses: " + (nBuildings - 1) +
                    "\nNumber of hotels: 1";

        return out;
    }
}