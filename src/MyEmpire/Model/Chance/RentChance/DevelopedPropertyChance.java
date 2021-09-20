package MyEmpire.Model.Chance.RentChance;

import MyEmpire.Model.Ownable.Ownable;
import MyEmpire.Model.Ownable.Property;

/**
 * The DevelopedPropertyChance increases the rent of Utilities or
 * Railroads by 50%.
 */
public class DevelopedPropertyChance extends RentChanceType {

    public DevelopedPropertyChance() {
        super(false);
    }

    @Override 
    public void applyRentToOwnable(Ownable asset) {
        if (asset instanceof Property) {
            ((Property) asset).chanceOverrideBuildings();
            asset.chanceOverrideRent(0.5);
        }
    }
}