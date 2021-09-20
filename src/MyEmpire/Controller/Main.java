package MyEmpire.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class Main extends Application {

    public static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLService.initFXMLService(primaryStage);

        URL resource = getClass().getResource("/assets/sounds/intro.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
//        mediaPlayer.play();

        primaryStage.setTitle("My Empire");

        Parent startMenu = FXMLLoader.load(getClass().getResource("/View/startMenu.fxml"));
        Scene startScene = new Scene(startMenu, 640, 480);

        primaryStage.setScene(startScene);
        startScene.getStylesheets().add(StartMenuController.class.getResource("/assets/css/sample.css").toExternalForm());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void initMute(Button btn){
        if (Main.mediaPlayer.getVolume() == 1)
            btn.setText("Mute");
        else
            btn.setText("Unmute");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
