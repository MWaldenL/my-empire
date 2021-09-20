package MyEmpire.Model.Game;

import MyEmpire.Model.Chance.ChanceSpace;
import MyEmpire.Model.Chance.GiveMoneyChance.DonateChance;
import MyEmpire.Model.Chance.GiveMoneyChance.GiveMoneyChanceType;
import MyEmpire.Model.Corner.CommunityServiceCorner;
import MyEmpire.Model.Corner.FreeParkingCorner;
import MyEmpire.Model.Corner.JailCorner;
import MyEmpire.Model.Corner.StartCorner;
import MyEmpire.Model.Ownable.Color;
import MyEmpire.Model.Ownable.Property;
import MyEmpire.Model.Ownable.Railroad;
import MyEmpire.Model.Ownable.Utility;
import MyEmpire.Model.Tax.IncomeTax;
import MyEmpire.Model.Tax.LuxuryTax;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Board class holds the Board instance of the Game.
 * A board has 32 spaces, with Ownable spaces, Corner spaces,
 * Chance spaces, and Tax Spaces.
 */
public class Board {

    private Space[] spaces;

    /**
     * Constructor for the Board class. Each space is initially empty.
     * The players are given the freedom to choose where they want the
     * different spaces placed on the board in the Game proper
     */
    public Board() {
        spaces = new Space[32];
    }

    /**
     * Initializes the reference of spaces to be used.
     * @return a new ArrayList of Spaces
     */
    public static HashMap<String, Space> GET_SPACELIST() {
        HashMap<String, Space> spaceList = new HashMap<String, Space>();

//       Grey Properties
        {
            int[] rentList = {2, 10, 30, 90, 160, 250};
            spaceList.put("Almond Drive", new Property("Almond Drive", Color.GREY, 60, 50, rentList, 2.5,
                    "/assets/images/Almond_Drive.png/"));
        }
        {
            int[] rentList = {4, 20, 60, 180, 320, 450};
            spaceList.put("Kasoy Street", new Property("Kasoy Street", Color.GREY, 60, 50, rentList, 3.0,
                    "/assets/images/Kasoy_Street.png/"));
        }

//       Violet Properties
        {
            int[] rentList = {6, 30, 90, 270, 400, 550};
            spaceList.put("Rodeo Drive", new Property("Rodeo Drive", Color.VIOLET, 100, 50, rentList, 3.5,
                    "/assets/images/Rodeo_Drive.png/"));
            spaceList.put("Orange Street", new Property("Orange Street", Color.VIOLET, 100, 50, rentList, 4.0,
                    "/assets/images/Orange_Street.png/"));
        }
        {
            int[] rentList = {6, 40, 100, 300, 450, 600};
            spaceList.put("Ventura Street", new Property("Ventura Street", Color.VIOLET, 120, 50, rentList, 4.0,
                    "/assets/images/Ventura_Street.png/"));
        }

//       Pink Properties
        {
            int[] rentList = {10, 50, 150, 450, 625, 750};
            spaceList.put("Juan Luna", new Property("Juan Luna", Color.PINK, 140, 100, rentList, 4.5,
                    "/assets/images/Juan_Luna.png/"));
            spaceList.put("Ylaya", new Property("Ylaya", Color.PINK, 140, 100, rentList, 4.5,
                    "/assets/images/Ylaya.png/"));
        }
        {
            int[] rentList = {12, 60, 180, 500, 700, 900};
            spaceList.put("J. Abad Santos", new Property("J. Abad Santos", Color.PINK, 160, 100, rentList, 5.0,
                    "/assets/images/J_Abad_Santos.png/"));
        }

//       Green Properties
        {
            int[] rentList = {14, 70, 200, 550, 750, 950};
            spaceList.put("Madison", new Property("Madison", Color.GREEN, 180, 100, rentList, 5.0,
                    "/assets/images/Madison.png/"));
            spaceList.put("Annapolis", new Property("Annapolis", Color.GREEN, 180, 100, rentList, 5.5,
                    "/assets/images/Annapolis.png/"));
        }
        {
            int[] rentList = {16, 80, 220, 600, 800, 1000};
            spaceList.put("Connecticut", new Property("Connecticut", Color.GREEN, 200, 100, rentList, 5.5,
                    "/assets/images/Connecticut.png/"));
        }

//       Blue Properties
        {
            int[] rentList = {18, 90, 250, 700, 875, 1050};
            spaceList.put("Bougainvilla", new Property("Bougainvilla", Color.BLUE, 220, 100, rentList, 6.0,
                    "/assets/images/Bougainvilla.png/"));
            spaceList.put("Dama de Noche", new Property("Dama de Noche", Color.BLUE, 220, 100, rentList, 6.0,
                    "/assets/images/Dama_de_Noche.png/"));
        }
        {
            int[] rentList = {20, 100, 300, 750, 925, 1100};
            spaceList.put("Acacia", new Property("Acacia", Color.BLUE, 240, 150, rentList, 6.5,
                    "/assets/images/Acacia.png/"));
        }

//        Red Properties
        {
            int[] rentList = {22, 110, 330, 800, 975, 1150};
            spaceList.put("Solar Street", new Property("Solar Street", Color.RED, 260, 150, rentList, 6.5,
                    "/assets/images/Solar_Street.png/"));
            spaceList.put("Galaxy Street", new Property("Galaxy Street", Color.RED, 260, 150, rentList, 7.0,
                    "/assets/images/Galaxy_Street.png/"));
        }

//        Yellow Properties
        {
            int[] rentList = {26, 130, 390, 900, 1100, 1275};
            spaceList.put("9th Street", new Property("9th Street", Color.YELLOW, 300, 200, rentList, 7.0,
                    "/assets/images/9th_Street.png/"));
        }
        {
            int[] rentList = {28, 150, 450, 1000, 1200, 1400};
            spaceList.put("5th Avenue", new Property("5th Avenue", Color.YELLOW, 320, 200, rentList, 8.0,
                    "/assets/images/5th_Avenue.png/"));
        }

//        Railroads
        {
            spaceList.put("North", new Railroad("North", "/assets/images/North.png/"));
            spaceList.put("South", new Railroad("South", "/assets/images/South.png/"));
            spaceList.put("Metro", new Railroad("Metro", "/assets/images/Metro.png/"));
        }

//        Utils
        {
            spaceList.put("Electric", new Utility("Electric", "/assets/images/Electricity.png/"));
            spaceList.put("Water", new Utility("Water", "/assets/images/Water.png/"));
        }

        for (int i = 0; i < 3; i++) {
            spaceList.put("Chance", new ChanceSpace("/assets/images/Chance.png/"));
        }


        spaceList.put("Luxury", new LuxuryTax("/assets/images/Luxury_Tax.png/"));
        spaceList.put("Income", new IncomeTax("/assets/images/Income_Tax.png/"));

        spaceList.put("Start", new StartCorner("/assets/images/Go.png/"));
        spaceList.put("FreeParking", new FreeParkingCorner("/assets/images/Free_Parking.png/"));
        spaceList.put("CommService", new CommunityServiceCorner("/assets/images/Community_Service.png/"));
        spaceList.put("Jail", new JailCorner("/assets/images/Jail.png/"));

        return spaceList;
    }

    /**
     * Initializes the reference of properties on the board.
     * @return a new ArrayList of properties
     */
    public static ArrayList<Property> getPropertyList() {
        ArrayList<Property> propertyMap = new ArrayList<>();

        //       Grey Properties
        {
            int[] rentList = {2, 10, 30, 90, 160, 250};
            propertyMap.add(new Property("Almond Drive", Color.GREY, 60, 50, rentList, 2.5,
                    "/assets/images/Almond_Drive.png/"));
        }
        {
            int[] rentList = {4, 20, 60, 180, 320, 450};
            propertyMap.add(new Property("Kasoy Street", Color.GREY, 60, 50, rentList, 3.0,
                    "/assets/images/Kasoy_Street.png/"));
        }

//       Violet Properties
        {
            int[] rentList = {6, 30, 90, 270, 400, 550};
            propertyMap.add(new Property("Rodeo Drive", Color.VIOLET, 100, 50, rentList, 3.5,
                    "/assets/images/Rodeo_Drive.png/"));
            propertyMap.add(new Property("Orange Street", Color.VIOLET, 100, 50, rentList, 4.0,
                    "/assets/images/Orange_Street.png/"));
        }
        {
            int[] rentList = {6, 40, 100, 300, 450, 600};
            propertyMap.add(new Property("Ventura Street", Color.VIOLET, 120, 50, rentList, 4.0,
                    "/assets/images/Ventura_Street.png/"));
        }

//       Pink Properties
        {
            int[] rentList = {10, 50, 150, 450, 625, 750};
            propertyMap.add(new Property("Juan Luna", Color.PINK, 140, 100, rentList, 4.5,
                    "/assets/images/Juan_Luna.png/"));
            propertyMap.add(new Property("Ylaya", Color.PINK, 140, 100, rentList, 4.5,
                    "/assets/images/Ylaya.png/"));
        }
        {
            int[] rentList = {12, 60, 180, 500, 700, 900};
            propertyMap.add(new Property("J. Abad Santos", Color.PINK, 160, 100, rentList, 5.0,
                    "/assets/images/J_Abad_Santos.png/"));
        }

//       Green Properties
        {
            int[] rentList = {14, 70, 200, 550, 750, 950};
            propertyMap.add(new Property("Madison", Color.GREEN, 180, 100, rentList, 5.0,
                    "/assets/images/Madison.png/"));
            propertyMap.add(new Property("Annapolis", Color.GREEN, 180, 100, rentList, 5.5,
                    "/assets/images/Annapolis.png/"));
        }
        {
            int[] rentList = {16, 80, 220, 600, 800, 1000};
            propertyMap.add(new Property("Connecticut", Color.GREEN, 200, 100, rentList, 5.5,
                    "/assets/images/Connecticut.png/"));
        }

//       Blue Properties
        {
            int[] rentList = {18, 90, 250, 700, 875, 1050};
            propertyMap.add(new Property("Bougainvilla", Color.BLUE, 220, 100, rentList, 6.0,
                    "/assets/images/Bougainvilla.png/"));
            propertyMap.add(new Property("Dama de Noche", Color.BLUE, 220, 100, rentList, 6.0,
                    "/assets/images/Dama_de_Noche.png/"));
        }
        {
            int[] rentList = {20, 100, 300, 750, 925, 1100};
            propertyMap.add(new Property("Acacia", Color.BLUE, 240, 150, rentList, 6.5,
                    "/assets/images/Acacia.png/"));
        }

//        Red Properties
        {
            int[] rentList = {22, 110, 330, 800, 975, 1150};
            propertyMap.add(new Property("Solar Street", Color.RED, 260, 150, rentList, 6.5,
                    "/assets/images/Solar_Street.png/"));
            propertyMap.add(new Property("Galaxy Street", Color.RED, 260, 150, rentList, 7.0,
                    "/assets/images/Galaxy_Street.png/"));
        }

//        Yellow Properties
        {
            int[] rentList = {26, 130, 390, 900, 1100, 1275};
            propertyMap.add(new Property("9th Street", Color.YELLOW, 300, 200, rentList, 7.0,
                    "/assets/images/9th_Street.png/"));
        }
        {
            int[] rentList = {28, 150, 450, 1000, 1200, 1400};
            propertyMap.add(new Property("5th Avenue", Color.YELLOW, 320, 200, rentList, 8.0,
                    "/assets/images/5th_Avenue.png/"));
        }

        return propertyMap;
    }


    /**
     * Returns the space list for the current board configuration
     * @return the space list for this boards
     */
    public Space[] getSpaces(){
        return spaces;
    }


    /**
     * Returns a specific space in this board from the position
     * @return a specific space in this board
     */
    public Space getSpace(int pos){
        return spaces[pos % 32];
    }

    /**
     * Places a space object in the given board position.
     * Used when prompting players to set up the board
     */
    public void setSpace(int pos, Space space) {
        spaces[pos % 32] = space;
    }


    /**
     * Adds a player to the current list of players on the given space.
     * Effects will be performed in the PlayGameController.
     * @param player the player who lands on this space.
     * @param pos the space position on the board.
     */
    public void playerLandsOnSpace(Player player, int pos) {
        spaces[pos % 32].addPlayer(player);
    }


    /**
     * Removes a player from the current playerList on the given space.
     * @param player the player who leaves this space.
     * @param pos the space position on the board.
     */
    public void playerExitsSpace(Player player, int pos) {
        spaces[pos % 32].removePlayer(player);
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Spaces: ");

        for (int i = 0; i < spaces.length; i++) {
            sb.append("\n" + (i + 1) + ") ");
            if (spaces[i] != null)
                sb.append(spaces[i].toString());
            else
                sb.append(spaces[i]);
        }

        return sb.toString();
    }
}