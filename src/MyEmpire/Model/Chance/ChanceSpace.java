package MyEmpire.Model.Chance;

import MyEmpire.Model.Game.Space;

/**
 * Space object to hold a Chance image
 */
public class ChanceSpace extends Space {
    public ChanceSpace(String imgUrl) {
        super(imgUrl);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
