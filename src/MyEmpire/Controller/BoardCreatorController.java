package MyEmpire.Controller;

import MyEmpire.Model.Game.Board;
import MyEmpire.Model.Game.GameData;
import MyEmpire.Model.Game.Space;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BoardCreatorController extends Controller {

    private static final Screen SCREEN = Screen.getPrimary();
    public static final double SCREENWIDTH = SCREEN.getVisualBounds().getWidth();
    public static final double SCREENHEIGHT = SCREEN.getVisualBounds().getHeight();;
    public static final double IMGSIZE = SCREENHEIGHT / 10;

    public static final Image DEFAULT = new Image("/assets/images/empty_space.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image ALMONDDRIVE = new Image("/assets/images/Almond_Drive.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image KASOYSTREET = new Image("/assets/images/Kasoy_Street.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image RODEODRIVE = new Image("/assets/images/Rodeo_Drive.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image ORANGESTREET = new Image("/assets/images/Orange_Street.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image VENTURASTREET = new Image("/assets/images/Ventura_Street.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image JUANLUNA = new Image("/assets/images/Juan_Luna.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image YLAYA = new Image("/assets/images/Ylaya.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image JABADSANTOS = new Image("/assets/images/J_Abad_Santos.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image MADISON  = new Image("/assets/images/Madison.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image ANNAPOLIS = new Image("/assets/images/Annapolis.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image CONNECTICUT = new Image("/assets/images/Connecticut.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image BOUGAINVILLA = new Image("/assets/images/Bougainvilla.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image DAMADENOCHE = new Image("/assets/images/Dama_de_Noche.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image ACACIA = new Image("/assets/images/Acacia.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image SOLARSTREET = new Image("/assets/images/Solar_Street.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image GALAXYSTREET = new Image("/assets/images/Galaxy_Street.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image _9THSTREET = new Image("/assets/images/9th_Street.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image _5THAVE = new Image("/assets/images/5th_Avenue.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image NORTH = new Image("/assets/images/North.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image SOUTH = new Image("/assets/images/South.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image METRO = new Image("/assets/images/Metro.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image WATER = new Image("/assets/images/Water.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image ELECTRICITY = new Image("/assets/images/Electricity.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image CHANCE = new Image("/assets/images/Chance.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image INCOMETAX = new Image("/assets/images/Income_Tax.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image LUXURYTAX = new Image("/assets/images/Luxury_Tax.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image GO = new Image("/assets/images/Go.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image JAIL = new Image("/assets/images/Jail.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image FREEPARKING = new Image("/assets/images/Free_Parking.png/", IMGSIZE, IMGSIZE, true, false);
    public static final Image COMMUNITYSERVICE = new Image("/assets/images/Community_Service.png/", IMGSIZE, IMGSIZE, true, false);

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane grid;
    @FXML
    private HBox grey, violet, green, pink, blue, red, yellow, railroads, utilities, chance, tax;
    @FXML
    private Button btnDone, btnAutoFill, btnMusic;

    private ArrayList<ImageView> boardSpaces;
    private ArrayList<HBox> containers;
    private LinkedHashMap<Image, Space> spaceMap;

    private ImageView dragSource;

    private void initBoard() {
        boardSpaces = new ArrayList<>();
        for(int i=9; i>0; i--){
            boardSpaces.add(new ImageView(DEFAULT));
            grid.add(boardSpaces.get(boardSpaces.size()-1), i-1, 8);
        }
        for(int i=8; i>0; i--){
            boardSpaces.add(new ImageView(DEFAULT));
            grid.add(boardSpaces.get(boardSpaces.size()-1), 0, i-1);
        }
        for(int i=1; i<9; i++){
            boardSpaces.add(new ImageView(DEFAULT));
            grid.add(boardSpaces.get(boardSpaces.size()-1), i, 0);
        }
        for(int i=1; i<8; i++){
            boardSpaces.add(new ImageView(DEFAULT));
            grid.add(boardSpaces.get(boardSpaces.size()-1), 8, i);
        }

        for(int i=0;i<boardSpaces.size();i++){
            if(i % 8 != 0){
                boardSpaces.get(i).setOnDragDetected((MouseEvent event) -> handleDragDetect(event));
                boardSpaces.get(i).setOnDragOver((DragEvent event) -> handleDragOver(event));
                boardSpaces.get(i).setOnDragDropped((DragEvent event) -> handleDragDrop(event));
            }
        }

        boardSpaces.get(0).setImage(GO);
        gameData.setBoard(Board.GET_SPACELIST().get("Start"), 0);

        boardSpaces.get(8).setImage(JAIL);
        gameData.setBoard(Board.GET_SPACELIST().get("Jail"), 8);

        boardSpaces.get(16).setImage(FREEPARKING);
        gameData.setBoard(Board.GET_SPACELIST().get("FreeParking"), 16);

        boardSpaces.get(24).setImage(COMMUNITYSERVICE);
        gameData.setBoard(Board.GET_SPACELIST().get("CommService"), 24);
    }

    private void initContainers() {
        containers = new ArrayList<>();
        containers.add(grey);
        containers.add(violet);
        containers.add(green);
        containers.add(pink);
        containers.add(blue);
        containers.add(red);
        containers.add(yellow);
        containers.add(railroads);
        containers.add(utilities);
        containers.add(chance);
        containers.add(tax);

        // Grey
        ((ImageView)containers.get(0).getChildren().get(0)).setImage(ALMONDDRIVE);
        ((ImageView)containers.get(0).getChildren().get(1)).setImage(KASOYSTREET);

        // Violet
        ((ImageView)containers.get(1).getChildren().get(0)).setImage(RODEODRIVE);
        ((ImageView)containers.get(1).getChildren().get(1)).setImage(ORANGESTREET);
        ((ImageView)containers.get(1).getChildren().get(2)).setImage(VENTURASTREET);

        // Pink
        ((ImageView)containers.get(2).getChildren().get(0)).setImage(JUANLUNA);
        ((ImageView)containers.get(2).getChildren().get(1)).setImage(YLAYA);
        ((ImageView)containers.get(2).getChildren().get(2)).setImage(JABADSANTOS);

        // Green
        ((ImageView)containers.get(3).getChildren().get(0)).setImage(MADISON);
        ((ImageView)containers.get(3).getChildren().get(1)).setImage(ANNAPOLIS);
        ((ImageView)containers.get(3).getChildren().get(2)).setImage(CONNECTICUT);

        // Blue
        ((ImageView)containers.get(4).getChildren().get(0)).setImage(BOUGAINVILLA);
        ((ImageView)containers.get(4).getChildren().get(1)).setImage(DAMADENOCHE);
        ((ImageView)containers.get(4).getChildren().get(2)).setImage(ACACIA);

        // Red
        ((ImageView)containers.get(5).getChildren().get(0)).setImage(SOLARSTREET);
        ((ImageView)containers.get(5).getChildren().get(1)).setImage(GALAXYSTREET);

        // Yellow
        ((ImageView)containers.get(6).getChildren().get(0)).setImage(_9THSTREET);
        ((ImageView)containers.get(6).getChildren().get(1)).setImage(_5THAVE);

        // Railroads
        ((ImageView)containers.get(7).getChildren().get(0)).setImage(NORTH);
        ((ImageView)containers.get(7).getChildren().get(1)).setImage(SOUTH);
        ((ImageView)containers.get(7).getChildren().get(2)).setImage(METRO);

        // Utilities
        ((ImageView)containers.get(8).getChildren().get(0)).setImage(WATER);
        ((ImageView)containers.get(8).getChildren().get(1)).setImage(ELECTRICITY);

        // Chance
        ((ImageView)containers.get(9).getChildren().get(0)).setImage(CHANCE);
        ((ImageView)containers.get(9).getChildren().get(1)).setImage(CHANCE);
        ((ImageView)containers.get(9).getChildren().get(2)).setImage(CHANCE);

        // Chance
        ((ImageView)containers.get(10).getChildren().get(0)).setImage(INCOMETAX);
        ((ImageView)containers.get(10).getChildren().get(1)).setImage(LUXURYTAX);

        for(int i=0; i<containers.size(); i++){
            containers.get(i).setPrefHeight(IMGSIZE);
            containers.get(i).setPrefWidth(IMGSIZE*3);
        }
    }

    private void initAnchorPane() {
        for(int i=1; i<=11; i++){
            if(i % 2 == 1){
                AnchorPane.setRightAnchor(anchorPane.getChildren().get(i), SCREENWIDTH*.25);
                AnchorPane.setTopAnchor(anchorPane.getChildren().get(i), IMGSIZE/2 + SCREENHEIGHT/15*(i-1));
            }
            else{
                AnchorPane.setRightAnchor(anchorPane.getChildren().get(i), (SCREENWIDTH*.25)-(IMGSIZE*3.5));
                AnchorPane.setTopAnchor(anchorPane.getChildren().get(i), IMGSIZE/2 + SCREENHEIGHT/15*(i-2));
            }
        }

        AnchorPane.setRightAnchor(anchorPane.getChildren().get(12), SCREENWIDTH*.125);
        AnchorPane.setTopAnchor(anchorPane.getChildren().get(12), IMGSIZE/2 + SCREENHEIGHT*.7);
        AnchorPane.setRightAnchor(anchorPane.getChildren().get(13), SCREENWIDTH*.125);
        AnchorPane.setTopAnchor(anchorPane.getChildren().get(13), IMGSIZE/2 + SCREENHEIGHT*.7);

    }

    private void initMute() {
        if (Main.mediaPlayer.getVolume() == 1)
            btnMusic.setText("Mute");
        else
            btnMusic.setText("Unmute");
    }

    private void initSpaceMap() {
        spaceMap = new LinkedHashMap<Image, Space>();

        // Properties
        spaceMap.put(GO, Board.GET_SPACELIST().get("Go")); // 0

        spaceMap.put(ALMONDDRIVE, Board.GET_SPACELIST().get("Almond Drive"));
        spaceMap.put(KASOYSTREET, Board.GET_SPACELIST().get("Kasoy Street"));
        spaceMap.put(RODEODRIVE, Board.GET_SPACELIST().get("Rodeo Drive"));
        spaceMap.put(ORANGESTREET, Board.GET_SPACELIST().get("Orange Street"));
        spaceMap.put(VENTURASTREET, Board.GET_SPACELIST().get("Ventura Street"));
        spaceMap.put(JUANLUNA, Board.GET_SPACELIST().get("Juan Luna"));
        spaceMap.put(YLAYA, Board.GET_SPACELIST().get("Ylaya"));

        spaceMap.put(JAIL, Board.GET_SPACELIST().get("Jail")); // 16

        spaceMap.put(JABADSANTOS, Board.GET_SPACELIST().get("J. Abad Santos"));
        spaceMap.put(MADISON, Board.GET_SPACELIST().get("Madison"));
        spaceMap.put(ANNAPOLIS, Board.GET_SPACELIST().get("Annapolis"));
        spaceMap.put(CONNECTICUT, Board.GET_SPACELIST().get("Connecticut"));
        spaceMap.put(BOUGAINVILLA, Board.GET_SPACELIST().get("Bougainvilla"));
        spaceMap.put(DAMADENOCHE, Board.GET_SPACELIST().get("Dama de Noche"));
        spaceMap.put(ACACIA, Board.GET_SPACELIST().get("Acacia"));

        spaceMap.put(FREEPARKING, Board.GET_SPACELIST().get("FreeParking")); // 24

        spaceMap.put(SOLARSTREET, Board.GET_SPACELIST().get("Solar Street"));
        spaceMap.put(GALAXYSTREET, Board.GET_SPACELIST().get("Galaxy Street"));
        spaceMap.put(_9THSTREET, Board.GET_SPACELIST().get("9th Street"));
        spaceMap.put(_5THAVE, Board.GET_SPACELIST().get("5th Avenue"));

        // Railroads and Utilities
        spaceMap.put(NORTH, Board.GET_SPACELIST().get("North"));
        spaceMap.put(SOUTH, Board.GET_SPACELIST().get("South"));
        spaceMap.put(METRO, Board.GET_SPACELIST().get("Metro"));

        spaceMap.put(COMMUNITYSERVICE, Board.GET_SPACELIST().get("CommService"));

        spaceMap.put(WATER, Board.GET_SPACELIST().get("Water"));
        spaceMap.put(ELECTRICITY, Board.GET_SPACELIST().get("Electric"));

        // Other Spaces
        spaceMap.put(CHANCE, Board.GET_SPACELIST().get("Chance"));
        spaceMap.put(INCOMETAX, Board.GET_SPACELIST().get("Income"));
        spaceMap.put(LUXURYTAX, Board.GET_SPACELIST().get("Luxury"));
    }

    public void initialize() {
        gameData = GameData.getInstance();
        fxmlService = FXMLService.getInstance();
        initSpaceMap();
        initBoard();
        initAnchorPane();
        initContainers();
        initMute();
    }

    public void handleDragDetect(MouseEvent event) {
        dragSource = (ImageView) event.getSource();
        System.out.println("Image: " + spaceMap.get(dragSource.getImage()));

        if (!dragSource.getImage().equals(DEFAULT)) {
            Dragboard dragboard = dragSource.startDragAndDrop(TransferMode.ANY);
            ClipboardContent clipboard = new ClipboardContent();

            clipboard.putImage(copyImage(dragSource.getImage()));
            dragboard.setContent(clipboard);
        }

        event.consume();
    }

    public void handleDragOver(DragEvent event) {
        if (((ImageView)event.getSource()).getImage().equals(DEFAULT))
            event.acceptTransferModes(TransferMode.ANY);
    }

    public void handleDragDrop(DragEvent event) {
        Image image= dragSource.getImage();
        ((ImageView) event.getSource()).setImage(image);

        if (dragSource.getParent() instanceof HBox)
            dragSource.setImage(null);
        else
            dragSource.setImage(DEFAULT);

        int destPos = boardSpaces.indexOf((ImageView) event.getSource());

        System.out.println(destPos);

        gameData.setBoard(spaceMap.get(image), destPos);

        if (isFull()) {
            btnDone.setVisible(true);
            btnAutoFill.setVisible(false);
        }
    }

    // DEMO VERSION
    public void fillBoard() {
        boardSpaces.get(1).setImage(NORTH);
        gameData.setBoard(spaceMap.get(NORTH), 1);
        boardSpaces.get(2).setImage(SOUTH);
        gameData.setBoard(spaceMap.get(SOUTH), 2);
        boardSpaces.get(3).setImage(METRO);
        gameData.setBoard(spaceMap.get(METRO), 3);
        boardSpaces.get(4).setImage(CHANCE);
        gameData.setBoard(spaceMap.get(CHANCE), 4);
        boardSpaces.get(5).setImage(WATER);
        gameData.setBoard(spaceMap.get(WATER), 5);
        boardSpaces.get(6).setImage(ELECTRICITY);
        gameData.setBoard(spaceMap.get(ELECTRICITY), 6);
        boardSpaces.get(7).setImage(LUXURYTAX);
        gameData.setBoard(spaceMap.get(LUXURYTAX), 7);

        boardSpaces.get(9).setImage(INCOMETAX);
        gameData.setBoard(spaceMap.get(INCOMETAX), 9);
        boardSpaces.get(10).setImage(ALMONDDRIVE);
        gameData.setBoard(spaceMap.get(ALMONDDRIVE), 10);
        boardSpaces.get(11).setImage(KASOYSTREET);
        gameData.setBoard(spaceMap.get(KASOYSTREET), 11);
        boardSpaces.get(12).setImage(CHANCE);
        gameData.setBoard(spaceMap.get(KASOYSTREET), 12);
        boardSpaces.get(13).setImage(RODEODRIVE);
        gameData.setBoard(spaceMap.get(RODEODRIVE), 13);
        boardSpaces.get(14).setImage(ORANGESTREET);
        gameData.setBoard(spaceMap.get(ORANGESTREET), 14);
        boardSpaces.get(15).setImage(VENTURASTREET);
        gameData.setBoard(spaceMap.get(VENTURASTREET), 15);

        boardSpaces.get(17).setImage(JUANLUNA);
        gameData.setBoard(spaceMap.get(JUANLUNA), 17);
        boardSpaces.get(18).setImage(YLAYA);
        gameData.setBoard(spaceMap.get(YLAYA), 18);
        boardSpaces.get(19).setImage(JABADSANTOS);
        gameData.setBoard(spaceMap.get(JABADSANTOS), 19);
        boardSpaces.get(20).setImage(CHANCE);
        gameData.setBoard(spaceMap.get(CHANCE), 20);
        boardSpaces.get(21).setImage(MADISON);
        gameData.setBoard(spaceMap.get(MADISON), 21);
        boardSpaces.get(22).setImage(ANNAPOLIS);
        gameData.setBoard(spaceMap.get(ANNAPOLIS), 22);
        boardSpaces.get(23).setImage(CONNECTICUT);
        gameData.setBoard(spaceMap.get(CONNECTICUT), 23);

        boardSpaces.get(25).setImage(BOUGAINVILLA);
        gameData.setBoard(spaceMap.get(BOUGAINVILLA), 25);
        boardSpaces.get(26).setImage(DAMADENOCHE);
        gameData.setBoard(spaceMap.get(DAMADENOCHE), 26);
        boardSpaces.get(27).setImage(ACACIA);
        gameData.setBoard(spaceMap.get(ACACIA), 27);
        boardSpaces.get(28).setImage(SOLARSTREET);
        gameData.setBoard(spaceMap.get(SOLARSTREET), 28);
        boardSpaces.get(29).setImage(GALAXYSTREET);
        gameData.setBoard(spaceMap.get(GALAXYSTREET), 29);
        boardSpaces.get(30).setImage(_9THSTREET);
        gameData.setBoard(spaceMap.get(_9THSTREET), 30);
        boardSpaces.get(31).setImage(_5THAVE);
        gameData.setBoard(spaceMap.get(_5THAVE), 31);

        btnDone.setVisible(true);
        btnAutoFill.setVisible(false);
    }

    private boolean isFull() {
        for (ImageView curImgView: boardSpaces)
            if (curImgView.getImage().equals(DEFAULT))
                return false;
        return true;
    }

    public void toGame(ActionEvent event) throws Exception {
        GameViewController gvc = (GameViewController)
                fxmlService.switchScene("gameView.fxml");

        ArrayList<Image> boardImages = new ArrayList<>();

        for (ImageView curView: boardSpaces)
            boardImages.add(curView.getImage());

        gvc.setImages(boardImages);
        gvc.initKeyListener();
    }

    public void toggleMusic(ActionEvent event) throws Exception{
        MediaPlayer player = Main.mediaPlayer;
        if (player.getVolume() == 1){
            player.setVolume(0);
            btnMusic.setText("Unmute");
        } else {
            player.setVolume(1);
            btnMusic.setText("Mute");
        }
    }

    public static WritableImage copyImage(Image image) {
        int height = (int) image.getHeight();
        int width = (int) image.getWidth();

        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width,height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                Color color = pixelReader.getColor(x, y);
                pixelWriter.setColor(x, y, color);
            }
        }
        return writableImage;
    }
}
