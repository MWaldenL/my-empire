package MyEmpire.Controller;

import MyEmpire.Model.Game.GameData;
import MyEmpire.Model.Game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EndScreenController extends Controller {

    @FXML
    public Button btnMusic;
    @FXML
    public VBox centerBox;

    private GameData gameData;
    private MediaPlayer endSongPlayer;

    public void initialize() {
        Main.mediaPlayer.stop();
        URL resource = getClass().getResource("/assets/sounds/happyending.mp3");
        Media media = new Media(resource.toString());
        endSongPlayer= new MediaPlayer(media);
        endSongPlayer.play();
        Main.initMute(btnMusic);
        gameData = GameData.getInstance();

        ArrayList<Player> rankedPlayerList = gameData.getPlayerList();

        Collections.sort(rankedPlayerList, Comparator.comparingDouble(Player::getScore));
        Collections.reverse(rankedPlayerList);

        centerBox.getChildren().add(new Label("1st " + rankedPlayerList.get(0).getName() +
                "\t" + rankedPlayerList.get(0).getScore()));
        centerBox.getChildren().add(new Label("2nd " + rankedPlayerList.get(1).getName() +
                "\t" + rankedPlayerList.get(1).getScore()));
        if(gameData.getPlayerCount() >= 3)
            centerBox.getChildren().add(new Label("3rd " + rankedPlayerList.get(2).getName() +
                    "\t" + rankedPlayerList.get(2).getScore()));
        if(gameData.getPlayerCount() == 4)
            centerBox.getChildren().add(new Label("4th " + rankedPlayerList.get(3).getName() +
                    "\t" + rankedPlayerList.get(3).getScore()));
        for (Node curNode:centerBox.getChildren())
            curNode.getStyleClass().add("ranking");
        Label gameOver = new Label("GAME OVER");
        gameOver.getStyleClass().add("gameOver");
        centerBox.getChildren().add(0, gameOver);
    }

    public void toggleMusic(ActionEvent event) {
        if (endSongPlayer.getVolume() == 1){
            endSongPlayer.setVolume(0);
            btnMusic.setText("Unmute");
        } else {
            endSongPlayer.setVolume(1);
            btnMusic.setText("Mute");
        }
    }
}