package nm;

/**
 * The main class of this game. This is where the entry point is and where the
 * game is being started from.
 */
import java.util.Scanner;
import nm.utils.Utils;

public class Game {

    //Minimum and Maximum amount of sticks that can be generated in a pile
    private final int MIN_ALLOWED_NUM_STICKS = 10;
    private final int MAX_ALLOWED_NUM_STICKS = 85;

    //The players, playerTwo is either instance of HumanPlayer or ComputerPlayer depending on choice.
    private Player playerOne;
    private Player playerTwo;

    //Pile is the stack of matches
    private StickPile pile;

    //Is true for aslong as the program is running
    private boolean running = true;

    //Round counter, there for determining whos turn it is
    private int round;

    //Scanner and Utils
    private Scanner userInput;
    private Utils utility;

    /**
     * The entry-point of the game, creates instance of Game called game and
     * calls method start to get the game started.
     *
     * @param args - not used
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public Game() {
        userInput = new Scanner(System.in);

        //Create instance of Utils
        utility = new Utils();

        //Start the game, round 1 - FIGHT!
        round = 1;
    }

    /**
     * Method that gets called at the games start, initiates everything and
     * contains a loop that calls play() for as long as the program is running.
     */
    private void start() {

        // Generate pile of matches
        generatePile();

        //Call method that create player/opponent object.
        createPlayers();

        //Display player names in beginning.
        System.out.println("Spelare 1: " + playerOne.getPlayerName());
        System.out.println("Spelare 2: " + playerTwo.getPlayerName());

        //Game loop, runs aslong as running is set to true.
        while (running) {
            play();
        }
    }

    /**
     * Method that contains the heart beat of the game. Runs as long as running
     * is set to true.
     */
    private void play() {

        // Can the game start? If either objects are null, just return.
        if (playerOne == null || playerTwo == null || pile == null) {
            return;
        }
        System.out.println("Runda: " + round);
        System.out.println("Antal stickor kvar: " + pile.getNumSticks());

        //Even or odd round determines whos turn it is.
        if ((round % 2) == 0) {
            playerTwo.move();
            checkWinner(playerTwo);
        } else {
            playerOne.move();
            checkWinner(playerOne);
        }
        round++;
        System.out.println("__________________");
    }

    /**
     * Method to stop the game.
     */
    private void stop() {
        running = false;
        System.exit(0);
    }

    /**
     * Method to restart the game. Creates a new instance of game and starts it
     */
    private void restart(Game game) {
        game = new Game();
        game.start();
    }

    /**
     * Create a player object and a opponent (either another player or a
     * computer bot).
     */
    private void createPlayers() {

        //Create first player
        System.out.print("Spelare 1 namn: ");
        String playerName = userInput.next();
        playerOne = new HumanPlayer(playerName, pile);

        int opponent = -1;
        String errorMessage = "Endast 1 eller 2, välj ett av dem";
        while (opponent != 1 && opponent != 2) {

            // Pass in identifier userOpponent as parameter, displays instructions.
            System.out.println("1 - Spela mot datorn, 2 - Spela mot annan människa");
            System.out.print("Ditt val: ");
            try {
                opponent = Integer.parseInt(userInput.next());
                //Create new object depending on if bot or other HumanPlayer.
                switch (opponent) {
                    case 1:
                        playerTwo = new BOTPlayer("DatorBot", pile);
                        break;
                    case 2:
                        System.out.print("Spelare 2 namn: ");
                        String opponentName = userInput.next();
                        playerTwo = new HumanPlayer(opponentName, pile);
                        break;
                    default:
                        System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private void generatePile() {

        int numSticks = 0;
        while (utility.numberInRange(MIN_ALLOWED_NUM_STICKS, MAX_ALLOWED_NUM_STICKS, numSticks) == false) {
            System.out.println("Välj antal stickor"
                    + " Minimum: " + MIN_ALLOWED_NUM_STICKS
                    + " Maximum: " + MAX_ALLOWED_NUM_STICKS
            );
            System.out.print("Ditt val: ");
            try {
                numSticks = Integer.parseInt(userInput.next());
                pile = new StickPile(numSticks);
            } catch (NumberFormatException e) {
                System.out.println("Inte ett nummer.");
            }
        }
    }

    /**
     * Method that checks if anybody won, gets called after each round.
     */
    private void checkWinner(Player p) {

        //Did someone win?
        if (pile.getNumSticks() == 1) {

            System.out.println("Spelare: " + p.getPlayerName() + " vann!");

            System.out.println("Spela igen? 1 - ja, 2 - nej");
            System.out.print("Ditt val: ");
            int choice = -1;
            while (choice != 1 && choice != 2) {
                try {
                    choice = Integer.parseInt(userInput.next());
                    switch (choice) {
                        case 1:
                            restart(this);
                            break;
                        case 2:
                            stop();
                            break;
                        default:
                            System.out.println("Endast 1 eller 2");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Endast 1 eller 2.");
                }
            }
        }
    }
}
