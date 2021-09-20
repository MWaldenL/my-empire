package MyEmpire.Model.Chance.RentChance;


import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Ownable.Ownable;

/**
 * RentChanceType is an abstract class that allows for rent
 * modifications on Ownables. There are five Chance Cards of this
 * type, namely, Double Rent, Developed Properties, Developed Railroads
 * or Utilities, Dilapidated Properties, and Dilapidated Railroads or Utilities.
 * Cannot be kept.
 */
public abstract class RentChanceType extends ChanceCard {

    public RentChanceType(boolean keepable) {
        super(keepable);
    }

    /**
     * applyRentToOwnable is a generic method for Chance Cards of
     * this type. Each Chance Card has a unique implementation, but all
     * of them modify the rent value of a given Ownable.
     *
     * @param asset the Ownable whose rent is modified
     */
    public abstract void applyRentToOwnable(Ownable asset);
}