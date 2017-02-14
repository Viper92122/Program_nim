package nm.utils;

import java.util.Random;

public class Utils {

    /**
     * Generates a number between a specific range specified in parameters Uses
     * a common formula to get a random integer between the specified
     * parameters.
     *
     * @param min - minimum number to be generated
     * @param max - maximum number to be generated
     * @return randomNum - the generated number.
     */
    public int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max + 1 - min) + min;
        return randomNum;
    }

    public boolean numberInRange(int min, int max, int num) {
        return ((num >= min) && (num <= max));
     }
}
