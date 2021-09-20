package MyEmpire.Model.Ownable;

import MyEmpire.Model.Game.GameData;


/**
 * There are two Utilities in this game, Electric and Water.
 * The initial price of utilities start at $150.
 */
public class Utility extends Ownable {
    public Utility(String name, String imgUrl) {
        super(name, Color.UTIL, imgUrl);
        nInitialPrice = 150;
        nRentPrice = 0;
    }

    @Override
    public int getRentPrice() {
        int nSameProperties = getSameCount();

        if ( nSameProperties == 1 )
            nRentPrice = 4 * GameData.rollDice(); // 4 * dice
        else if ( nSameProperties == 2 )
            nRentPrice = 10 * GameData.rollDice(); // 10 * dice

        return nRentPrice;
    }

    @Override
    public double getValue(){
        return getPrice();
    }

    @Override
    public String toString(){

        String out = strName + "\nPrice: " + getPrice();
        out +="\nOwner: ";
        if (pOwner == null)
            return out + "None" + "\nRent: Dice roll times " + 4;
        else
            out += pOwner.getName();
        if ( getSameCount() == 1 )
            return out + "\nRent: Dice roll times " + 4;
        else if ( getSameCount() == 2 )
            return out + "\nRent: Dice roll times " + 10;
        else return out + "\n" + getSameCount() +" "+ color;
    }
}