package nm;

/**
 * A childclass/subclass that inherits all of a Players property. The
 * HumanPlayer defines a player that acts on its own.
 */
import java.util.Scanner;
import nm.utils.Utils;

public class HumanPlayer extends Player {

    private Utils utility;
    /**
     * Constructor for HumanPlayer class. Takes in two parameters and creates a
     * instance of a HumanPlayer.
     *
     * @param name - name of player
     * @param pile - reference to the pile the player will play in and take
     * matches from
     */
    public HumanPlayer(String name, StickPile pile) {
        userInput = new Scanner(System.in);
        utility = new Utils();
        this.name = name;
        this.pile = pile;
        
    }

    /**
     * Getter for HumanPlayer name.
     *
     * @return name
     */
    @Override
    String getPlayerName() {
        return name;
    }

    /**
     * HumanPlayers own move() method. Starts by calling super class and asks
     * player for input after that (how many matches should be removed)
     * according to the game rules.
     */
    @Override
    void move() {
        super.move();
        System.out.print("Ditt val: ");
        int amount = Integer.parseInt(userInput.next());

        //Run loop aslong as user enters number that is higher then allowed.
        while (utility.numberInRange(1, pile.getMaxNumSticksAllowedRemoved(), amount) == false) {
            System.out.printf("Det går endast att ta bort max: %d stickor och minst en åt gången", pile.getMaxNumSticksAllowedRemoved());
            System.out.print("Ditt val: ");
            amount = Integer.parseInt(userInput.next());
        }

        //Substract the amount and display result to the players
        pile.substractSticks(amount);
        System.out.printf("Spelare %s tog bort %d stickor", getPlayerName(), amount);
        System.out.println();
    }
}
