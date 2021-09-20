package MyEmpire.Model.Game;

import MyEmpire.Model.Chance.ChanceCard;
import MyEmpire.Model.Chance.GetOutOfJailChance;
import MyEmpire.Model.Chance.GiveMoneyChance.DonateChance;
import MyEmpire.Model.Chance.GiveMoneyChance.PayTaxChance;
import MyEmpire.Model.Chance.ProceedToOwnableChance.ProceedToProperty;
import MyEmpire.Model.Chance.ProceedToOwnableChance.ProceedToRailroad;
import MyEmpire.Model.Chance.ProceedToOwnableChance.ProceedToUtility;
import MyEmpire.Model.Chance.ReceiveMoneyChance.*;
import MyEmpire.Model.Chance.RentChance.*;
import MyEmpire.Model.Chance.TakeATripChance.GoToJailChance;
import MyEmpire.Model.Chance.TakeATripChance.TripToPropertyChance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The ChanceDeck class contains the deck of cards to be used.
 * Players draw or discard from the Chance deck. Players can keep a card until it is applicable,
 * or use immediately.
 */
public class ChanceDeck {
    private ArrayList<ChanceCard> drawPile;
    private ArrayList<ChanceCard> discardPile;
    private int nTop;

    /**
     * Initializes the reference of Chance Cards to be used.
     * @return a new ArrayList of Chance Cards
     */
    public static ArrayList<ChanceCard> GET_CHANCELIST() {
        ArrayList<ChanceCard> chanceList = new ArrayList<ChanceCard>();

        // add 2 get out of jails; index 0
        chanceList.add(new GetOutOfJailChance());
        chanceList.add(new GetOutOfJailChance());

        // add 6; ProceedToOwnableChance
        for (int i = 0; i < 2; i++) {
            chanceList.add(new ProceedToProperty());
            chanceList.add(new ProceedToRailroad());
            chanceList.add(new ProceedToUtility());
        }

        // add 6; ReceiveMoneyChance
        chanceList.add(new BankPaysPlayerChance());
        chanceList.add(new TaxRefundChance());
        chanceList.add(new AdvanceToStartChance());
        chanceList.add(new BirthdayChance());
        chanceList.add(new WonCompetitonChance());

        // add 4; index 9-10
        for (int i = 0; i < 2; i++) {
            chanceList.add(new GoToJailChance());
            chanceList.add(new TripToPropertyChance());
        }

        // add 7; index 11-15
        chanceList.add(new DoubleRentChance());
        chanceList.add(new DevelopedPropertyChance());
        chanceList.add(new DevelopedUtilRailChance());
        chanceList.add(new DilapidatedPropertyChance());
        chanceList.add(new DilapidatedUtilRailChance());

        // add 3 from donate and pay taxes; index 16-17
        chanceList.add(new DonateChance());
        chanceList.add(new PayTaxChance());

        return chanceList;
    }

    /**
     * Constructor for the Chance Deck class
     * The draw pile is initialized with all chance cards and
     * shuffled. The discard pile is initialized to an empty list.
     */
    public ChanceDeck() {
        discardPile = new ArrayList<ChanceCard>();
        Random rand = new Random();
        int choice;

        drawPile = new ArrayList<ChanceCard>();
        drawPile = GET_CHANCELIST();

        choice = rand.nextInt(5);
        if (choice == 1)
            drawPile.add(new BankPaysPlayerChance());
        else if (choice == 2)
            drawPile.add(new TaxRefundChance());
        else if (choice == 3)
            drawPile.add(new AdvanceToStartChance());
        else if (choice == 4)
            drawPile.add(new BirthdayChance());
        else if (choice == 5)
            drawPile.add(new WonCompetitonChance());

        for (int i = 0; i < 2; i++) {
            choice = rand.nextInt(5);
            if (choice == 1)
                drawPile.add(new DoubleRentChance());
            else if (choice == 2)
                drawPile.add(new DevelopedPropertyChance());
            else if (choice == 3)
                drawPile.add(new DevelopedUtilRailChance());
            else if (choice == 4)
                drawPile.add(new DilapidatedPropertyChance());
            else if (choice == 5)
                drawPile.add(new DilapidatedUtilRailChance());
        }

        choice = rand.nextInt(2);
        if (choice == 1)
            drawPile.add(new DonateChance());
        else if (choice == 2)
            drawPile.add(new PayTaxChance());


        shuffleDeck();
    }

    /**
     * Gets the current draw pile
     * @return the draw pile
     */
    public ArrayList<ChanceCard> getDrawPile() {
        return drawPile;
    }


    /**
     * Returns the top card in the draw pile.
     * @return the card drawn from the draw pile.
     */
    public ChanceCard giveCard() {
        ChanceCard card = drawPile.get(nTop);
        nTop--;

        if (nTop == -1)
            shuffleDeck();

        return card;
    }

    /**
     * Adds a Chance card into the discard pile
     * @param card the card to discard
     */
    public void addToDiscardPile(ChanceCard card) {
        discardPile.add(card);
    }


    /**
     * Shuffles the draw pile after initializing
     */
    private void shuffleDeck() {
        Collections.shuffle(drawPile);
        nTop = drawPile.size() - 1;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Draw Pile: \n");
        for (ChanceCard c : drawPile) {
            sb.append(c.toString() + "\n");
        }

        sb.append("\nDiscard Pile: ");
        for (ChanceCard c : discardPile) {
            sb.append(c.toString() + "\n");
        }

        return sb.toString();
    }
}