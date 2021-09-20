package MyEmpire.Model.Chance.RentChance;


import MyEmpire.Model.Ownable.Ownable;
import MyEmpire.Model.Ownable.Railroad;
import MyEmpire.Model.Ownable.Utility;

/**
 * The DilapidatedUtilRailChance overrides the rent of Utilities or
 * Railroads by -10%.
 */
public class DilapidatedUtilRailChance extends RentChanceType {

    public DilapidatedUtilRailChance() {
        super(false);
    }


    @Override 
    public void applyRentToOwnable(Ownable asset) {
        if (asset instanceof Utility || asset instanceof Railroad) {
            asset.chanceOverrideRent(-0.1);
        }
    }
}