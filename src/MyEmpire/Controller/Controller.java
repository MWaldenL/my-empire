package MyEmpire.Controller;

import MyEmpire.Model.Game.GameData;
import javafx.scene.Parent;

public abstract class Controller {
    protected FXMLService fxmlService;
    protected Parent root;
    protected GameData gameData;

    public abstract void initialize();

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }
}
