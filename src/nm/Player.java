package nm;

/**
 * The class that describe a Player. It is a Abstract class and other classes
 * inherits from this one (BOTPLayer and HumanPlayer)
 */
import java.util.Scanner;

abstract class Player {

    //Player name
    protected String name;

    //Reference to the pile and scanner to get user input.
    protected StickPile pile;
    protected Scanner userInput;

    /**
     * Getter for name
     *
     * @return name
     */
    String getPlayerName() {
        return name;
    }

    /**
     * Superclass Player own move, all it does is write whos turn it is.
     */
    void move() {
        System.out.println("Det är: " + getPlayerName() + " tur");
    }
}
