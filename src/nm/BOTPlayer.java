package nm;

import nm.utils.Utils;

/**
 * A childclass/subclass that inherits all of a Players property. The BOT does
 * its move automatically via a simple algorithm following the rules.
 */
public class BOTPlayer extends Player {

    private Utils utility;

    public BOTPlayer(String name, StickPile pile) {
        this.name = name;
        this.pile = pile;
        utility = new Utils();
    }

    /**
     * BotPlayers own getPlayerName(), overrides superclass.
     *
     * @return - name
     */
    @Override
    String getPlayerName() {
        return name;
    }

    /**
     * BOTPlayers own move class. Calls super-class method move() in beginning
     * Generates a random number of sticks to be removed by the computer with
     * the rules in mind.
     */
    @Override
    void move() {
        super.move();

        int minNumSticks = 1;
        int maxNumSticks = pile.getMaxNumSticksAllowedRemoved();

        int randNumSticks = utility.generateRandomNumber(minNumSticks, maxNumSticks);
        if (randNumSticks <= minNumSticks) {
            // set to minimum num sticks if it generates under.
            randNumSticks = minNumSticks;
        } else {
            // Must always be atleast one left, so subtract 1 from whatever value it is if over 1.
            randNumSticks--;
        }
        pile.substractSticks(randNumSticks);
        System.out.printf("Datorn tog bort %d stickor", randNumSticks);
        System.out.println();
    }
}
