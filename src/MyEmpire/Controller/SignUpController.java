package MyEmpire.Controller;

import MyEmpire.Model.Game.GameData;
import MyEmpire.Model.Game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController extends Controller {

    @FXML
    private TextField tf1,tf2,tf3, tf4;
    private FXMLService fxmlService;

    public void initialize() {
        tf3.setVisible(false);
        tf4.setVisible(false);
        fxmlService = FXMLService.getInstance();
    }

    public void setNumPlayers(int numPlayers) {
        GameData.initInstance(numPlayers);
        gameData = GameData.getInstance();

        switch (numPlayers) {
            case 4:
                tf4.setVisible(true);
            case 3:
                tf3.setVisible(true);
        }
    }

    public void toStartMenu(ActionEvent event) throws Exception{
        Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/View/startMenu.fxml"));
        Parent root = loader.load();

        StartMenuController controller = loader.getController();
        controller.setRoot(root);

        Scene scene = new Scene(controller.getRoot(), 640, 480);

        scene.getStylesheets().add(getClass().getResource("/assets/css/sample.css").toExternalForm());

        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public void toBoardCreator(ActionEvent event) throws Exception {
        fxmlService.switchScene("boardCreator.fxml");

        gameData.addPlayer(new Player(tf1.getText()));
        gameData.addPlayer(new Player(tf2.getText()));

        if (gameData.getPlayerCount() >= 3) {
            gameData.addPlayer(new Player(tf3.getText()));
            gameData.addPlayer(new Player(tf4.getText()));
        }
    }
}
