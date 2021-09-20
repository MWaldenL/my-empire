package MyEmpire.Controller;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Chance.ChanceSpace;
import MyEmpire.Model.Chance.GetOutOfJailChance;
import MyEmpire.Model.Chance.GiveMoneyChance.DonateChance;
import MyEmpire.Model.Chance.GiveMoneyChance.GiveMoneyChanceType;
import MyEmpire.Model.Chance.ProceedToOwnableChance.ProceedChanceType;
import MyEmpire.Model.Chance.ProceedToOwnableChance.ProceedToProperty;
import MyEmpire.Model.Chance.ProceedToOwnableChance.ProceedToRailroad;
import MyEmpire.Model.Chance.ProceedToOwnableChance.ProceedToUtility;
import MyEmpire.Model.Chance.ReceiveMoneyChance.AddCashChanceType;
import MyEmpire.Model.Chance.ReceiveMoneyChance.AdvanceToStartChance;
import MyEmpire.Model.Chance.ReceiveMoneyChance.BirthdayChance;
import MyEmpire.Model.Chance.RentChance.*;
import MyEmpire.Model.Chance.TakeATripChance.GoToJailChance;
import MyEmpire.Model.Chance.TakeATripChance.TakeATripChanceType;
import MyEmpire.Model.Chance.TakeATripChance.TripToPropertyChance;
import MyEmpire.Model.Corner.*;
import MyEmpire.Model.Game.*;
import MyEmpire.Model.Ownable.*;
import MyEmpire.Model.Tax.Taxable;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static MyEmpire.Controller.BoardCreatorController.*;

public class GameViewController extends Controller implements MovePlayerCallback, OnFinishedCallback{
    @FXML
    private Button btnMusic, btnDice, inventoryBtn, endTurnBtn, tradeBtn, useChanceBtn, applyChanceBtn, purchaseBtn;
    @FXML
    private GridPane grid;
    @FXML
    private Pane propOptionPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView dice;
    @FXML
    private ImageView player1, player2, player3, player4;
    @FXML
    private VBox inventoryPanel, curSpacePanel, playerStartPanel, propertyListPanel, rollDicePanel;
    @FXML
    private TextArea curSpaceInfo, diceValue;
    @FXML
    private TextField bankView;
    @FXML
    private ListView inventoryList, propertyList;

    private TextArea spaceHoverInfo;

    private Image houseImg = new Image("/assets/images/House.png/", IMGSIZE/4, IMGSIZE/4, true, false);
    private Image hotelImg = new Image("/assets/images/Hotel.png/", IMGSIZE, IMGSIZE/3, true, false);
    private Image p1Img = new Image("/assets/images/Player_1.png/", IMGSIZE, IMGSIZE, true, false);
    private Image p2Img = new Image("/assets/images/Player_2.png/", IMGSIZE, IMGSIZE, true, false);
    private Image p3Img = new Image("/assets/images/Player_3.png/", IMGSIZE, IMGSIZE, true, false);
    private Image p4Img = new Image("/assets/images/Player_4.png/", IMGSIZE, IMGSIZE, true, false);

    private ArrayList<ImageView> boardSpaceImages;
    private ArrayList<StackPane> boardSpaces;
    private ArrayList<HBox> houseBoxes;
    private ArrayList<ImageView> playerTokens;

    private int[] tokenLocations = {0, 0, 0, 0};
    private int curPlayerIndex;

    // DEMO
    @FXML
    private AnchorPane bigcontainer;
    private int chanceCount = 1;

    /* Setup Methods */
    private void initBoard() {
        boardSpaces = new ArrayList<>();
        boardSpaceImages = new ArrayList<>();
        houseBoxes = new ArrayList<>();
        playerTokens = new ArrayList<>();

        for(int i=9; i>0; i--){
            boardSpaces.add(new StackPane());
            grid.add(boardSpaces.get(boardSpaces.size()-1), i-1, 8);
        }
        for(int i=8; i>0; i--){
            boardSpaces.add(new StackPane());
            grid.add(boardSpaces.get(boardSpaces.size()-1), 0, i-1);
        }
        for(int i=1; i<9; i++){
            boardSpaces.add(new StackPane());
            grid.add(boardSpaces.get(boardSpaces.size()-1), i, 0);
        }
        for(int i=1; i<8; i++){
            boardSpaces.add(new StackPane());
            grid.add(boardSpaces.get(boardSpaces.size()-1), 8, i);
        }

        for (StackPane curPane: boardSpaces) {
            boardSpaceImages.add(new ImageView());
            houseBoxes.add(new HBox());
            for(int i=0; i<4; i++){
                ImageView img = new ImageView(houseImg);
                img.setVisible(false);
                houseBoxes.get(houseBoxes.size()-1).getChildren().add(img);
            }
            curPane.getChildren().add(boardSpaceImages.get(boardSpaceImages.size()-1));
            curPane.getChildren().add(houseBoxes.get(houseBoxes.size()-1));
            curPane.setOnMouseEntered(e->viewProperty(boardSpaces.indexOf(curPane)));
            curPane.setOnMouseExited(e->closeViewProperty());
        }
    }

    private void initSpaceInfo() {
        Label boardTitle = new Label("MY EMPIRE");
        boardTitle.getStyleClass().add("title");
        boardTitle.setStyle("-fx-font-size: "+ IMGSIZE*.75 +"px;");

        PseudoClass centered = PseudoClass.getPseudoClass("centered");
        spaceHoverInfo = new TextArea();
        spaceHoverInfo.setEditable(false);
        spaceHoverInfo.setMaxWidth(IMGSIZE*4.7);
        spaceHoverInfo.setMaxHeight(IMGSIZE);
        spaceHoverInfo.pseudoClassStateChanged(centered, true);
        spaceHoverInfo.setVisible(false);

        StackPane stackPane = new StackPane(boardTitle, spaceHoverInfo);
        grid.add(stackPane, 2, 4 , 5, 1);
    }

    public void viewProperty(int spaceIndex) {
        spaceHoverInfo.setText(gameData.getBoard().getSpace(spaceIndex).toString());
        spaceHoverInfo.setVisible(true);
    }

    public void closeViewProperty(){
        spaceHoverInfo.setVisible(false);
    }

    private void initPlayers() {
        player1.setImage(p1Img);
        playerTokens.add(player1);
        player2.setImage(p2Img);
        playerTokens.add(player2);

        if (gameData.getPlayerCount() >= 3) {
            player3.setImage(p3Img);
            playerTokens.add(player3);
        }

        if (gameData.getPlayerCount() == 4) {
            player4.setImage(p4Img);
            playerTokens.add(player4);
        }

        for (Node curNode: playerTokens) {
            AnchorPane.setBottomAnchor(curNode, 5.0);
            AnchorPane.setRightAnchor(curNode, 5.0);
        }
    }

    private void initGameData() {
        gameData = GameData.getInstance();
        curPlayerIndex = 0; // default first player
        updateBankView();
    }

    private void updateBankView() {
        bankView.setText("Bank: $" + gameData.getBank().getCash());
    }

    private void updateBankandPlayer(Player p, double cash) {
        gameData.getBank().updateBank(-cash);
        p.updateCash(cash);
    }

    public void initialize() {
        initGameData();
        initBoard();
        initSpaceInfo();
        Main.initMute(btnMusic);
        fxmlService = FXMLService.getInstance();
        initPlayers();
        getCurrentPlayer().startTurn();
        ((Label) playerStartPanel.getChildren().get(0)).setText(getCurrentPlayer() + "'s Turn");
        System.out.println("Start Game");


    }

    public void initKeyListener(){
        //DEMO
        fxmlService.stage.getScene().setOnKeyPressed(e -> {
            System.out.println(e.getCode().toString());
            switch(e.getCode().toString()){
                case "DIGIT1": rollDice(1); break;
                case "DIGIT2": rollDice(2); break;
                case "DIGIT3": rollDice(3); break;
                case "DIGIT4": rollDice(4); break;
                case "DIGIT5": rollDice(5); break;
                case "DIGIT6": rollDice(6); break;
                case "DIGIT7": rollDice(7); break;
                case "DIGIT8": rollDice(8); break;
                case "DIGIT9": rollDice(9); break;
            }
        });
    }

    public void setImages(ArrayList<Image> images) {
        for (int i=0; i<images.size(); i++)
            boardSpaceImages.get(i).setImage(images.get(i));
    }

    public void setHouses(int propertyLocation, int numHouses) {
        for (int i = 0; i < numHouses; i++)
            houseBoxes.get(propertyLocation).getChildren().get(i).setVisible(true);
    }

    public void setHotel(int propertyLocation) {
        int i;
        for (i = 0; i < 2; i++)
            ((ImageView)houseBoxes.get(propertyLocation).getChildren().get(i)).setImage(null);

        ((ImageView) houseBoxes.get(propertyLocation).getChildren().get(i)).setImage(hotelImg);

        houseBoxes.get(propertyLocation).getChildren().get(i).setVisible(true);
    }


    public void rollDice(ActionEvent actionEvent) {
        int dice = GameData.rollDice();
        GameData.increaseTurnCount();

        diceValue.setText(String.valueOf(dice));
        rollDicePanel.toFront();

        int curPos = getCurrentPlayer().getPos();
        movePlayer(curPlayerIndex + 1, dice, curPos, curPos + 1, this);

        btnDice.setVisible(false);
        endTurnBtn.setVisible(false);
    }

    public void rollDice(int keyNum) {
        GameData.increaseTurnCount();

        diceValue.setText("" + keyNum);
        rollDicePanel.toFront();

        int curPos = getCurrentPlayer().getPos();
        movePlayer(curPlayerIndex + 1, keyNum, curPos, curPos + 1, this);

        btnDice.setVisible(false);
        endTurnBtn.setVisible(false);
    }

    public void movePlayer(int playerNum, int numMoves, int initPos, int curPos, MovePlayerCallback callback) {
        Player player = getCurrentPlayer();
        Board board = gameData.getBoard();
        int finalPos = initPos + numMoves;

        if (player.isInJail()) {
            gameData.getBank().updateBank(50);
        }

        if (curPos <= finalPos) {
            TranslateTransition left = new TranslateTransition();
            left.setByX(-IMGSIZE*.99);
            TranslateTransition up   = new TranslateTransition();
            up.setByY(-IMGSIZE*.99);
            TranslateTransition right = new TranslateTransition();
            right.setByX(IMGSIZE*.99);
            TranslateTransition down = new TranslateTransition();
            down.setByY(IMGSIZE*.99);

            ImageView curNode = playerTokens.get(playerNum-1);
            TranslateTransition curTransition = new TranslateTransition();

            int locn = tokenLocations[playerNum-1] % 32;

            if (locn < 8)
                curTransition = left;
            else if (locn < 16)
                curTransition = up;
            else if (locn < 24)
                curTransition = right;
            else curTransition = down;

            tokenLocations[playerNum - 1]++;

            board.playerLandsOnSpace(player, curPos);
            player.setPosition(curPos);

            if (board.getSpace(curPos) instanceof StartCorner &&
                    !player.isInJail() &&
                    !player.hasDrawnProceedChance()) {
                updateBankandPlayer(player, 200);
            }

            board.playerExitsSpace(player, curPos);

            curTransition.setNode(curNode);
            curTransition.setDuration(Duration.seconds(0.1));
            curTransition.setOnFinished(event -> movePlayer(playerNum , numMoves, initPos, curPos + 1, callback));
            curTransition.play();
        } else {
            if (callback != null)
                callback.updatePlayer(curPos, player);
        }

        updateBankView();
    }


    @Override
    public void updatePlayer(int pos, Player player) {
        Board board = gameData.getBoard();
        int curPos = player.getPos();

        System.out.println("updatePlayer() Cur pos : " + curPos);

        curSpaceInfo.setText(gameData.getBoard().getSpace(curPos).toString());
        curSpacePanel.toFront();

        handleSpaceType(board.getSpace(curPos), player);

        endTurnBtn.setVisible(true);
    }

    public void trade(ActionEvent actionEvent) {
        Player curPlayer = getCurrentPlayer();
        Ownable otherProperty = (Ownable) gameData.getBoard().getSpace(curPlayer.getPos());
        Ownable curPlayerProperty = null;

        for (Color curColor: Color.values())
            for (Ownable curOwnable: curPlayer.getOwnableListByColor(curColor))
                if (curOwnable.getName().equals(inventoryList.getSelectionModel().getSelectedItem()))
                    curPlayerProperty = curOwnable;

        curPlayer.trade(otherProperty.getOwner(), otherProperty, curPlayerProperty);
        System.out.println(curPlayerProperty +" traded to " + otherProperty.getOwner().getName() +
                " for " + otherProperty.toString());
        if(gameData.isEndGame())
            endTurn();
    }

    public void viewInventory() {
        Player player = getCurrentPlayer();
        ObservableList<String> items = FXCollections.observableArrayList ("Cash:" + player.getCash());
        for (Color color : Color.values()) {
            List<Ownable> ownableList = player.getOwnableListByColor(color);
            System.out.println(color);
            items.add(color.toString());
            for (Ownable ownable : ownableList) {
                String ownableName = ownable.getName();
                items.add("\t" + ownableName);
            }
        }
        inventoryList.setItems(items);
        inventoryList.setPrefHeight(SCREENHEIGHT/3);
        tradeBtn.setVisible(false);
        useChanceBtn.setVisible(false);
        inventoryPanel.toFront();
        inventoryBtn.setText("Close Inventory");
        inventoryBtn.setOnAction(e->closeInventory());

        inventoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
                Space curSpace = gameData.getBoard().getSpace(getCurrentPlayer().getPos());
                if(((String)inventoryList.getSelectionModel().getSelectedItem()).startsWith("\t")) {
                    if (((String) inventoryList.getSelectionModel().getSelectedItem()).endsWith("Chance")) {
                        System.out.println("Chance drawn");
                        useChanceBtn.setVisible(true);
                    }
                    else if (curSpace instanceof Ownable
                            && ((Ownable)curSpace).getOwner() != null
                            && ((Ownable)curSpace).getOwner() != getCurrentPlayer())
                        tradeBtn.setVisible(true);
                }
                else{
                    tradeBtn.setVisible(false);
                    useChanceBtn.setVisible(false);
                    inventoryList.getSelectionModel().clearSelection();
                }
            }
        });
    }

    public void closeInventory() {
        inventoryPanel.toBack();
        inventoryBtn.setText("Open Inventory");
        inventoryBtn.setOnAction(e -> viewInventory());
    }

    public void showOwnableList(RentChanceType curChanceCard, String type, OnFinishedCallback ofc) {
        endTurnBtn.setVisible(false);

        ObservableList<String> items = FXCollections.observableArrayList();
        List<Ownable> ownableList;

        boolean condition = true;
        for (Color color : Color.values()) {
            if (type.equalsIgnoreCase("prop"))
                condition = !color.equals(Color.RAIL) && !color.equals(Color.UTIL);
            else if (type.equalsIgnoreCase("utilrail"))
                condition = color.equals(Color.RAIL) || color.equals(Color.UTIL);

            if (condition) {
                ownableList = getCurrentPlayer().getOwnableListByColor(color);
                System.out.println(color);
                items.add(color.toString());

                for (int i = 0; i < ownableList.size(); i++) {
                    Ownable ownable = ownableList.get(i);
                    String ownableName = ownable.getName();
                    items.add("\t" + ownableName);
                }
            }
        }

        propertyList.setItems(items);
        applyChanceBtn.setOnAction(e -> applyChanceToOwnable(curChanceCard, ofc));
        applyChanceBtn.setVisible(false);

        propertyListPanel.toFront();
        propertyList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (ov, old_val, new_val) -> {
            if (((String)propertyList.getSelectionModel().getSelectedItem()).startsWith("\t"))
                applyChanceBtn.setVisible(true);
            else {
                applyChanceBtn.setVisible(false);
                propertyList.getSelectionModel().clearSelection();
            }
        });
    }

    public Ownable applyChanceToOwnable(RentChanceType curChanceCard, OnFinishedCallback ofc) {
        String strProperty = ((String) propertyList.getSelectionModel().getSelectedItem()).substring(1);

        Ownable selectedOwnable = null;

        for (Color curColor: Color.values())
            for (Ownable curOwnable: getCurrentPlayer().getOwnableListByColor(curColor))
                if (curOwnable.getName().equals(strProperty)) {
                    selectedOwnable = curOwnable;
                    curChanceCard.applyRentToOwnable(selectedOwnable);
                }

        propertyListPanel.toBack();
        endTurnBtn.setVisible(true);

        if (ofc != null)
            ofc.onFinished(curChanceCard.getClass().getSimpleName());

        return selectedOwnable;
    }

    @Override
    public void onFinished(String title) {
        AlertBox.display(title, "Successfully applied chance card!");
    }


    public void toEndScreen(ActionEvent event) throws Exception {
        fxmlService.switchScene("endScreen.fxml");
    }

    public void toggleMusic(ActionEvent event) {
        MediaPlayer player = Main.mediaPlayer;
        if (player.getVolume() == 1){
            player.setVolume(0);
            btnMusic.setText("Unmute");
        } else {
            player.setVolume(1);
            btnMusic.setText("Mute");
        }
    }

    public void endTurn() {
        if (gameData.isEndGame()) {
            if (gameData.getBank().getCash() <= 0)
                AlertBox.display("GAME OVER", "The Bank is out of Money");
            else if (getCurrentPlayer().isBankrupt())
                AlertBox.display("LOST THE GAME", getCurrentPlayer().getName() + "is bankrupt.");
            else
            AlertBox.display("WON THE GAME", getCurrentPlayer().getName() + "won the game.");
        }
        getCurrentPlayer().endTurn();
        curPlayerIndex = (curPlayerIndex + 1) % gameData.getPlayerCount();
        closeInventory();

        if ( !gameData.isEndGame() ) {
            ((Label) playerStartPanel.getChildren().get(0)).setText(getCurrentPlayer() + "'s Turn");
            playerStartPanel.toFront();
            getCurrentPlayer().startTurn();
            btnDice.setVisible(true);
            endTurnBtn.setVisible(false);
        } else {
            System.out.println("Game End");
            try { toEndScreen(null); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }


    /* Private Methods  */
    private Player getCurrentPlayer() {
        return gameData.getPlayerList().get(curPlayerIndex);
    }

    // Done
    private void onChanceDrawn(ChanceCard card, Player player) {
        Board board = gameData.getBoard();

        // Done
        if (card instanceof GiveMoneyChanceType) {
            ((GiveMoneyChanceType) card).giveMoney(gameData.getBank(), player);

            AlertBox.display(
                    "Draw " + card.getClass().getSimpleName(),
                    getCurrentPlayer() + "cash: " + getCurrentPlayer().getCash());
        }

        // Done
        else if (card instanceof ProceedChanceType) {
            Ownable targetOwnable = null;
            int dist;

            if (card instanceof ProceedToProperty) {
                player.drawProceedChance();
                Property target = moveToRandomProperty(gameData.getBoard(), getCurrentPlayer());

                AlertBox.display("Draw " + card.getClass().getSimpleName(), "Proceed to " + target);
            } else {
                if (card instanceof ProceedToRailroad)
                    targetOwnable = getNearestOwnable("rail");
                else if (card instanceof ProceedToUtility)
                    targetOwnable = getNearestOwnable("util");

                // currentPos - (targetPos - currentPos) + offset between currentPos and start
                dist = Math.abs(player.getPos() -
                                Math.abs(targetOwnable.getBoardPos() - player.getPos())) +
                                (32 - player.getPos());

                System.out.println("Dist: " + dist);
                movePlayer(curPlayerIndex + 1, dist, player.getPos(), player.getPos() + 1, this);

                AlertBox.display("Draw " + card.getClass().getSimpleName(), "Proceed to " + targetOwnable);
            }

            ((ProceedChanceType) card).proceedToOwnable(gameData.getBoard(), player, targetOwnable);
        }

        // Done
        else if (card instanceof AddCashChanceType) {
            if (card instanceof AdvanceToStartChance) {
                int dist = Math.abs(getCurrentPlayer().getPos() - 32);
                movePlayer(curPlayerIndex + 1, dist, getCurrentPlayer().getPos(), getCurrentPlayer().getPos() + 1, this);
            }

            ((AddCashChanceType) card).collectMoney(gameData.getBank(), player);

            AlertBox.display("Draw " + card.getClass().getSimpleName(),
                    "New Cash: " + getCurrentPlayer().getCash());
        }

        // Done
        else if (card instanceof RentChanceType) {
            String propMsg = "Please pick a property from your inventory";
            String utilRailMsg = "Please pick a utility or railroad from your inventory";

            if (player.getTotalOwnableCount() > 0) {
                // Done
                if (card instanceof DevelopedPropertyChance) {
                    AlertBox.display("Developed Houses", propMsg);
                }

                // Done
                else if (card instanceof DilapidatedPropertyChance) {
                    AlertBox.display("Dilapidated Houses", propMsg);
                }

                // Done
                else if (card instanceof DoubleRentChance) {
                    AlertBox.display("Double Rent", propMsg);
                }

                if (player.getTotalPropertyCount() > 0) {
                    showOwnableList((RentChanceType) card, "prop", this);
                } else {
                    AlertBox.display("Unusable Card", "You have no available properties. Discard this card.");
                    card.discard(gameData.getChanceDeck());
                }

                // Done
                if (card instanceof DevelopedUtilRailChance) {
                    AlertBox.display("Developed Utility and Railroad", utilRailMsg);
                    showOwnableList((RentChanceType) card, "utilrail", this);
                }

                // Done
                else if (card instanceof DilapidatedUtilRailChance) {
                    AlertBox.display("Dilapidated Utility and Railroad", utilRailMsg);
                    showOwnableList((RentChanceType) card, "utilrail", this);
                }
            }

            else {
                AlertBox.display("Unusable Card", "You have no available ownables. Discard this card.");
                card.discard(gameData.getChanceDeck());
            }
        }

        // Done
        else if (card instanceof TakeATripChanceType) {
            if (card instanceof TripToPropertyChance) {
                // Pick a random property from the pool of properties
                int rand = new Random().nextInt(17); // 18 properties
                Property targetProperty = Board.getPropertyList().get(rand);

                // Get the player position on the board
                int oldPos = getCurrentPlayer().getPos();
                Space curSpace = board.getSpace(oldPos);

                // Checks if the property is found already
                boolean isSameProperty = false;

                // Walk the board until the property is found
                int newPos = oldPos + 1;
                while (!isSameProperty) {
                    curSpace = board.getSpace(newPos);

                    if (curSpace instanceof Property)
                        if (((Property) curSpace).getName().equalsIgnoreCase(targetProperty.getName()))
                            isSameProperty = true;

                    newPos++;
                }

                // Finally, move the player to the target property
                int numMoves = Math.abs(newPos - oldPos);
                int curPos = getCurrentPlayer().getPos();

                ((TripToPropertyChance) card).takeATrip(player, board, curPos + numMoves);
                movePlayer(curPlayerIndex + 1, numMoves, curPos, curPos + 1, this);

                handleSpaceType(curSpace, player);
                AlertBox.display("Take a Trip!", "Take a trip to " + targetProperty.getName());
            }
            else if (card instanceof GoToJailChance) {
                ((GoToJailChance) card).takeATrip(player, board, 8); // pos of jail
                AlertBox.display("Go To Jail!", "Go To Jail! Pay $50 on your next turn.");
            }
        }

        // Done
        else if (card instanceof GetOutOfJailChance) {
            ((GetOutOfJailChance) card).getOutOfJail(player);
            AlertBox.display("Get Out of Jail!", "Get Out of Jail for free!");
        }

        if(gameData.isEndGame())
            endTurn();
    }

    private void handleSpaceType(Space curSpace, Player player) {
        // Done
        if (curSpace instanceof CornerType) {
            ((CornerType) curSpace).actOnPlayer(gameData.getBank(), player);

            if (curSpace instanceof JailCorner) {
                AlertBox.display(
                        "Go To Jail!",
                        "You are in jail! You have a debt of $50.");
                endTurn();
            }

            else if (curSpace instanceof CommunityServiceCorner) {
                AlertBox.display("Pay to Bank!",
                        "You landed on " + curSpace.getClass().getSimpleName() +
                        "\n$50 was deducted from your cash. \nNew Cash: " + player.getCash());
            }

            else if (curSpace instanceof StartCorner) {
                AlertBox.display("Start Corner",
                        "You landed on " + String.valueOf(curSpace.getClass().getSimpleName()) +
                                "\nYou earned $200. \nNew Cash: " + player.getCash());
            }

            else if (curSpace instanceof FreeParkingCorner) {
                AlertBox.display(
                        "Free Parking!",
                        "End your turn");
                endTurn();
            }
        }

        // Done
        else if (curSpace instanceof Taxable) {
            String taxSpace = curSpace.getClass().getSimpleName();
            int cash = ((Taxable) curSpace).chargeTax(gameData.getBank(), player);
            AlertBox.display(taxSpace, "\n$" + cash + " has been deducted from your cash.");
        }

        // Done
        else if (curSpace instanceof ChanceSpace) {
            // DEMO
            ChanceCard card = null;
            if (chanceCount % 6 == 0)
                card = new DonateChance();
            else if (chanceCount % 6 == 1)
                card = new ProceedToProperty();
            else if (chanceCount % 6 == 2)
                card = new BirthdayChance();
            else if (chanceCount % 6 == 3)
                card = new DevelopedUtilRailChance();
            else if (chanceCount % 6 == 4)
                card = new GoToJailChance();
            else if (chanceCount % 6 == 5)
                card = new GetOutOfJailChance();

            chanceCount++;
            onChanceDrawn(card, player);
        }

        // Done
        else if (curSpace instanceof Ownable) {
            Ownable currentOwnable = (Ownable) curSpace;

            if (currentOwnable.getOwner() == null)
                purchaseBtn.setVisible(true);
            else if (currentOwnable.getOwner() != null)
                purchaseBtn.setVisible(false);

            if (curSpace instanceof Property) {
                Property currentProperty = (Property) curSpace;

                // Develop and Pay Rent options
                if (currentProperty.getOwner() != null) {
                    if (currentProperty.getOwner().equals(player)) {
                        if (player.getCash() > currentProperty.getPrice() &&
                            currentProperty.getCurrentFootTraffic() >= currentProperty.getFootTrafficLimit(gameData.getPlayerCount())
                        ) {
                            int nBldgs = currentProperty.getBldgCount();
                            player.develop(currentProperty);

                            if (nBldgs < 5) {
                                setHouses(currentProperty.getBoardPos(), nBldgs);
                            }
                            else if (currentProperty.canBuildHotel())
                                setHotel(currentProperty.getBoardPos());

                            AlertBox.display(
                                    "Develop",
                                    "Developed" + currentProperty.getName() + "!\n$" +
                                            currentProperty.getHousePrice() + " has been deducted from your cash.");
                        }
                    } else {
                        player.payRent(currentProperty.getOwner(), currentOwnable);
                        AlertBox.display("Pay Rent",
                                "Pay Rent to " + currentProperty.getOwner() + "!\n$" +
                                currentProperty.getRentPrice() + " has been deducted from your cash.");
                    }
                } else { // Purchase option
                    purchaseBtn.setOnAction(e -> {
                        purchaseOwnable(player, currentProperty);
                    });
                }
            } else { // Railroad and Utility
                if (currentOwnable.getOwner() == null)
                    purchaseBtn.setOnAction(e -> {
                        purchaseOwnable(player, currentOwnable);
                    });
                else if (currentOwnable.getOwner() != player) {
                    String msg = "";

                    if (currentOwnable instanceof Utility) {
                        player.payRent(currentOwnable.getOwner(), currentOwnable);
                        msg = "Dice has been rolled for you!\n";
                    } else {
                        player.payRent(currentOwnable.getOwner(), currentOwnable);
                    }

                    AlertBox.display("Pay Rent",
                            msg + "Pay Rent to " + currentOwnable.getOwner() + "!\n$" +
                                    currentOwnable.getRentPrice() + " has been deducted from your cash.");
                }
            }
        }
        if(gameData.isEndGame())
            endTurn();
    }

    private void purchaseOwnable(Player player, Ownable ownable) {
        player.purchase(ownable);
        AlertBox.display("Purchase Successful!", "Successfully purchased " + ownable.getName());
        curSpaceInfo.setText(ownable.toString());
        gameData.getBank().updateBank(ownable.getPrice());
        updateBankView();
        purchaseBtn.setVisible(false);
        if(gameData.isEndGame())
            endTurn();

    }

    private Property moveToRandomProperty(Board board, Player player) {
        int rand = new Random().nextInt(17); // 18 properties
        Property targetProperty = Board.getPropertyList().get(rand);

        // Get the player position on the board
        int oldPos = getCurrentPlayer().getPos();
        Space curSpace = board.getSpace(oldPos);

        // Checks if the property is found already
        boolean isSameProperty = false;

        // Walk the board until the property is found
        int newPos = oldPos + 1;
        while (!isSameProperty) {
            curSpace = board.getSpace(newPos);

            if (curSpace instanceof Property)
                if (((Property) curSpace).getName().equalsIgnoreCase(targetProperty.getName()))
                    isSameProperty = true;

            if(!isSameProperty)
                newPos++;
        }

        // Finally, move the player to the target property
        int numMoves = Math.abs(newPos - oldPos);
        int curPos = getCurrentPlayer().getPos();

        movePlayer(curPlayerIndex + 1, numMoves, curPos, curPos + 1, this);
        handleSpaceType(curSpace, player);

        return targetProperty;
    }

    private Ownable getNearestOwnable(String type) {
        Board board = gameData.getBoard();
        boolean isFound = false;
        int curPos = getCurrentPlayer().getPos();
        Space curSpace = board.getSpace(curPos);
        Ownable result = null;

        while (!isFound) {
            curSpace = board.getSpace(curPos);

            if (type.equalsIgnoreCase("rail")) {
                if (curSpace instanceof Railroad) {
                    result = (Ownable) curSpace;
                    isFound = true;
                }
            } else if (type.equalsIgnoreCase("util")) {
                if (curSpace instanceof Utility) {
                    result = (Ownable) curSpace;
                    isFound = true;
                }
            }

            curPos++;
        }

        return result;
    }

}
