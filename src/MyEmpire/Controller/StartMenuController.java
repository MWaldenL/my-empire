package MyEmpire.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartMenuController extends Controller {

    @FXML
    private Button btn2p, btn3p, btn4p, btnMusic;

    private FXMLService fxmlService;

    public void initialize() {
        fxmlService = FXMLService.getInstance();
    }

    public void toSignUp(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/signUp.fxml"));
        Parent root = loader.load();
        SignUpController signUpController = loader.getController();

        Scene signUpScene = new Scene(root, 640, 480);
        signUpScene.getStylesheets().add(SignUpController.class.getResource("/assets/css/sample.css").toExternalForm());

        if (event.getSource() == btn2p)
            signUpController.setNumPlayers(2);
        else if (event.getSource() == btn3p)
            signUpController.setNumPlayers(3);
        else if (event.getSource() == btn4p)
            signUpController.setNumPlayers(4);

        primaryStage.hide();
        primaryStage.setScene(signUpScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    public void toggleMusic(ActionEvent event) throws Exception{
        MediaPlayer player = Main.mediaPlayer;
        if (player.getVolume() == 1){
             player.setVolume(0);
             btnMusic.setText("Unmute");
        }
        else{
            player.setVolume(1);
            btnMusic.setText("Mute");
        }
    }
}
