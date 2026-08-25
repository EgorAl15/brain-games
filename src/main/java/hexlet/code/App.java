package hexlet.code;

import java.util.Scanner;
import hexlet.code.games.EvenGame;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Brain Games!");
        System.out.print("May I have your name? ");
        String name = scanner.nextLine().trim();
        System.out.println("Hello, " + name + "!");

        while (true) {
            System.out.println();
            System.out.println("Please enter the game number and press Enter.");
            System.out.println("1 - Greet");
            System.out.println("2 - Even");
            System.out.println("0 - Exit");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Hello again, " + name + "!");
                    break;
                case "2":
                    EvenGame.play(scanner, name);
                    break;
                case "0":
                    System.out.println("Goodbye, " + name + "!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 0.");
            }
        }
    }
}
