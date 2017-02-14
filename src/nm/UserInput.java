package nm;

import java.util.Scanner;

public class UserInput {

    private Scanner scanner;

    public UserInput() {
        scanner = new Scanner(System.in);
    }

    public String readString() {
        return scanner.next();
    }

    public int readInt() {
        int choice = -1;
            try {
                Integer.parseInt(scanner.next());
            } catch (NumberFormatException nfe) {
                System.out.println("Not a number.");
            }
        return choice;
    }

}
