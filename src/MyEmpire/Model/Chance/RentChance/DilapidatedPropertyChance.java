package MyEmpire.Model.Chance.RentChance;


import MyEmpire.Model.Ownable.Ownable;
import MyEmpire.Model.Ownable.Property;

/**
 * The DilapidatedPropertyChance decreases the rent of Properties by 10%.
 */
public class DilapidatedPropertyChance extends RentChanceType {

    public DilapidatedPropertyChance() {
        super(false);
    }

    @Override 
    public void applyRentToOwnable(Ownable asset) {
        if (asset instanceof Property) {
            asset.chanceOverrideRent(-0.1);
        }
    }
}