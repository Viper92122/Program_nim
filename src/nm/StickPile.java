package nm;

/**
 * The "gameboard" itself. This class contains information about the
 * matches/sticks that each player grabs from.
 */
public class StickPile {

    //Amount of sticks/matches this game should have
    private int numSticks;

    /**
     * Constructor, takes in argument how many sticks and sets this instance of
     * numSticks to that value.
     *
     * @param numSticks
     */
    public StickPile(int numSticks) {
        this.numSticks = numSticks;
    }

    /**
     * Getter for numsticks
     *
     * @return numSticks
     */
    public int getNumSticks() {
        return numSticks;
    }

    /**
     * Subtract numSticks by amount.
     *
     * @param amount - how much should get subtracted
     */
    public void substractSticks(int amount) {
        numSticks -= amount;
    }

    /**
     * getter for maximum allowed sticks during that round, according to game
     * rules
     *
     * @return casted value of ceil numSticks / 2
     */
    public int getMaxNumSticksAllowedRemoved() {
        return (int) Math.ceil(numSticks / 2);
    }

}
