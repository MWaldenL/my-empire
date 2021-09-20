package MyEmpire.Model.Game;

/*
 * The Player class is a class that represents a player in
 * the game. A player has a name, property list,
 * an initial cash of $1500, a position on the board,
 * and a list of chance cards. The player has the ability to
 * purchase properties, trade properties with other players, and
 * charge rent to other players who land on their properties.
 * A player is bankrupt if he or she does not have enough money to
 * perform money-related operations, such as paying rent, paying tax,
 * and getting out of jail.
 */

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Ownable.Color;
import MyEmpire.Model.Ownable.Ownable;
import MyEmpire.Model.Ownable.Property;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String strName;
    private HashMap<Color, ArrayList<Ownable>> ownableList;
    private ArrayList<ChanceCard> chanceList;
    private double dCash;
    private int nPosition;
    private boolean isTurn;
    private boolean isInJail;
    private boolean hasDrawnProceedChance;

    /**
     * Constructor for Player class. Player name is set according to the name passed. Initial cash is set to 1500.00.
     * The property list is initialized as a Hashmap and set according to colors. Initial position is set to 0.
     * isTurn boolean attribute is set to false.
     *
     * @param name the name that will be used for initializing strName
     */
    public Player(String name) {
        strName = name;
        dCash = 1500.0;

        ownableList = new HashMap<Color, ArrayList<Ownable>>();
        for (Color color : Color.values())
            ownableList.put(color, new ArrayList<Ownable>());

        chanceList = new ArrayList<ChanceCard>();
        nPosition = 0;
        isTurn = false;
        isInJail = false;
        hasDrawnProceedChance = false;
    }

    /**
     * Returns the player's name
     * @return a String of the player's name
     */
    public String getName() {
        return strName;
    }

    /**
     * Returns the player's current cash
     * @return a double value of the player's cash
     */
    public double getCash() {
        return dCash;
    }

    /**
     * Returns the player's current position on the Board
     * Modulo operator is used to ensure that the position
     * is bounded to 32 spaces (zero index).
     * @return an integer of the player's position
     */
    public int getPos() {
        return nPosition % 32;
    }

    /**
     * Returns the list of chance cards that the player has
     * @return an ArrayList of ChanceCards that player owns
     */
    public ArrayList<ChanceCard> getChanceList() {
        return chanceList;
    }

    /**
     * Returs the list of properties that the player owns
     * @return a Hashmap of Color and Property that lists the player's properties
     */
    public HashMap<Color, ArrayList<Ownable>> getOwnableList() {
        return ownableList;
    }

    /**
     * Returns the player's list of properties of a specific color
     * @param c the selected color of properties that will be returned
     * @return an ArrayList of Property with a specific Color that the player owns
     */
    public ArrayList<Ownable> getOwnableListByColor(Color c) {
        return ownableList.get(c);
    }


    /**
     * Returns the total number of ownables this player owns
     * @return the total number of ownables this player owns
     */
    public int getTotalOwnableCount() {
        int count = 0;
        for (Color c : Color.values())
            if (!c.equals(Color.RAIL) || !c.equals(Color.UTIL))
                count += getOwnableListByColor(c).size();

        return count;
    }


    /**
     * Returns the total number of properties this player owns
     * @return the total number of properties this player owns
     */
    public int getTotalPropertyCount() {
        int count = 0;

        for (Color c : Color.values())
            if (!c.equals(Color.RAIL) || !c.equals(Color.UTIL))
                count += getOwnableListByColor(c).size();

        return count;
    }


    /**
     * Sets the position of the player on the board. Used when moving the player.
     * @param pos the target position moved to
     */
    public void setPosition(int pos) {
        nPosition = pos % 32;
    }

    /**
     * Checks if the player is currently in jail
     * @return whether the player is currently in jail
     */
    public boolean isInJail() {
        return isInJail;
    }


    /**
     * Sets the jail value.
     */
    public void setJailValue(boolean value) {
        isInJail = value;
    }


    /**
     * Adds a ownable to this player's ownable list
     * @param ownable the ownable to add to list
     */
    public void addOwnableToList(Ownable ownable) {
        getOwnableListByColor(ownable.getColor()).add(ownable);
    }


    /**
     * Removes an ownable from this player's ownable list
     * @param ownable the ownable to add to list
     */
    public void removeOwnableFromList(Ownable ownable) {
        getOwnableListByColor(ownable.getColor()).remove(ownable);
    }


    /**
     * Draws a card from the top of the given Chance Deck.
     * The card is added to the player's card list.
     * Cards drawn may either be kept or used. In certain situations,
     * a card may be discarded if it cannot be used, for instance,
     * drawing a DevelopPropertyChance card when this player has no
     * properties on hand.
     *
     * @param deck the chance deck of the current game state
     * @return the card drawn from the deck
     */
    public ChanceCard drawCard(ChanceDeck deck) {
        ChanceCard card = deck.giveCard();

        if (card.isKeepable())
            chanceList.add(card);

        return card;
    }

    /**
     * Checks if the player has drawn a ProceedChanceCard.
     * This will prevent the player from collecting money from
     * START when drawing a ProceedToProperty
     * @return if the player has drawn a ProceedChanceCard.
     */
    public boolean hasDrawnProceedChance() {
        return hasDrawnProceedChance;
    }


    /**
     * Draw a proceed chance card. Will prevent the player from
     * collecting money at START
     */
    public void drawProceedChance() {
        hasDrawnProceedChance = true;
    }


    /**
     * Updates a declared amount of cash to the player's cash.
     * The amount may be positive or negative, depending on the
     * transaction.
     *
     * @param amount the amount that will be added to or
     *               subtracted from the player's Cash
     */
    public void updateCash(double amount) {
        dCash += amount;
    }


    /**
     * Determines whether the player's cash is less than or equal to 0
     * A player is bankrupt if he or she cannot pay for rent or jail.
     *
     * @return whether the player's cash is negative or 0.
     */
    public boolean isBankrupt() {
        return dCash <= 0.0;
    }

    /**
     * Lets the player purchase a ownable if the ownable is unowned
     *
     * @param ownable the ownable to purchase
     */
    public void purchase(Ownable ownable) {
        Color color = ownable.getColor();

        if (ownable.getOwner() == null) {
            // Add the property to the List associated with the color specified
            getOwnableListByColor(color).add(ownable);
            ownable.setOwner(this);
            updateCash(-ownable.getPrice());

            System.out.println("Purchase Successful!");
        } else System.out.println("Property not for sale.");
    }

    /**
     * Develops a player's property if it is due for development
     *
     * @param property the property that will be developed
     */
    public void develop(Property property) {
        if (property.getOwner() == this) {
            if (property.hasHotel()) {
                System.out.println("Property already has hotel.");
            } else {
                property.build();

                if (property.getBldgCount() < 5)
                    updateCash(-property.getHousePrice());
                else
                    updateCash(-property.getHotelPrice());
            }
        } else {
            System.out.println("Unowned Property");
        }
    }

    /**
     * Lets the player pay rent to the owner of the property
     *
     * @param owner the player that owns the property where rent will be paid to
     * @param asset the property where rent is charged
     */
    public void payRent(Player owner, Ownable asset) {
        int rent;

        if (asset instanceof Property) {
            if (((Property) asset).isRentDoubled()) {
                rent = asset.getRentPrice() * (int) Math.pow(2, ((Property) asset).getNumDoubleRent());
                ((Property) asset).popDoubleRentCard();
            }
            else
                rent = asset.getRentPrice();
        }
        else
            rent = asset.getRentPrice();

        updateCash(-rent);
        owner.updateCash(rent);
    }


    /**
     * Exchanges owners of ownables.
     * @param other the other player to exchange with
     * @param own the ownable owned by this player
     * @param toTrade the ownable to trade with the other player
     */
    public void trade(Player other, Ownable own, Ownable toTrade) {
        other.addOwnableToList(own);
        addOwnableToList(toTrade);

        other.removeOwnableFromList(toTrade);
        removeOwnableFromList(own);

        own.setOwner(other);
        toTrade.setOwner(this);
    }

    /**
     * Moves this player to a position on the board
     * Used for ProceedToOwnableChance, Go to Jail, Go to Start,
     * and other events that perform this action.
     *
     * @param dest the destination space on the board
     */
    public void jumpToPos(Board board, int dest) {
        board.getSpace(dest).addPlayer(this);
    }

    /**
     * Starts the player's turn, as well as set the player's upkeep.
     */
    public void startTurn() {
        upkeep();
        isTurn = true;
    }

    /**
     * Returns the number of full sets the player has.
     * @return the number of full sets the player has.
     */
    public int getNumFullSets() {
        int counter = 0;

        for (Color c : Color.values()) {
            if (c.equals(Color.VIOLET) || c.equals(Color.PINK) || c.equals(Color.GREEN) ||
                    c.equals(Color.BLUE) || c.equals(Color.RAIL)) {
                if (getOwnableListByColor(c).size() == 3)
                    counter++;
            } else if (c.equals(Color.GREY) || c.equals(Color.RED) || c.equals(Color.YELLOW) ||
                    c.equals(Color.UTIL) ) {
                if (getOwnableListByColor(c).size() == 2)
                    counter++;
            }
        }

        return counter;
    }


    /**
     * Adds to the effects that will affect the player on his or her next turn.
     */
    private void upkeep() {
        if (isInJail) {
            updateCash(-50);
            isInJail = false;
        }
    }

    /**
     * Ends the player's turn by changing the turn attribute to false
     */
    public void endTurn() {
        isTurn = false;
    }

    /**
     * Computes the player's score to be used for ranking
     * @return the player's cash plus the summation of the values of all owned property or 0 if bankrupt
     */
    public double getScore() {
        if (isBankrupt())
            return 0.0;
        double score = dCash;
        for(Color color: Color.values())
            for(Ownable curOwnable: getOwnableListByColor(color))
                score += curOwnable.getValue();
        return score;
    }

    @Override
    public String toString() {
        return strName;
    }
}