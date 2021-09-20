package MyEmpire.Model.Ownable;

/**
 * There are three railroads in this game, North,
 * South, and Metro. The initial price of railroads start at $200.
 */
public class Railroad extends Ownable {
    public Railroad(String name, String imgUrl) {
        super(name, Color.RAIL, imgUrl);
        nInitialPrice = 200;
        nRentPrice = 25;
    }

    @Override
    public int getRentPrice() {
        int nSameProperties = getSameCount();

        if ( nSameProperties == 1 )
            nRentPrice = 25;
        else if ( nSameProperties == 2 )
            nRentPrice = 50;
        else if ( nSameProperties == 3 )
            nRentPrice = 150;

        return nRentPrice;
    }

    @Override
    public double getValue(){
        return getPrice();
    }

    @Override
    public String toString(){
        String out = strName + "\nPrice: " + getPrice();
        if(pOwner == null)
            return out + "\nOwner: None" + "\nRent: " + 25;
        else
            return out + "\nOwner: " + pOwner.getName() + "\nRent: " + getRentPrice();
    }
}