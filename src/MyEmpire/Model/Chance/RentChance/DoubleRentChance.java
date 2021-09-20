package MyEmpire.Model.Chance.RentChance;


import MyEmpire.Model.Ownable.Ownable;
import MyEmpire.Model.Ownable.Property;

/**
 * The Double Rent Chance Card is a chance card that 
 * doubles the rent of an ownable (property, utility, or railroad)
 * with a given player as target. The effect of this chance card
 * ends when the target player pays his or her doubled rent. 
 */
public class DoubleRentChance extends RentChanceType {

    public DoubleRentChance() {
        super(false);
    }

    @Override
    public void applyRentToOwnable(Ownable asset) {
        if (asset instanceof Property) {
            ((Property) asset).applyDoubleRent();
            ((Property) asset).stackDoubleRentCard();
        }
    }
}