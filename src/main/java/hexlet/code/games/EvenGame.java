package hexlet.code.games;

import java.util.Scanner;
import java.util.Random;

public class EvenGame {

    private static final int ROUNDS_TO_WIN = 3;
    private static final Random RANDOM = new Random();

    public static void play(Scanner scanner, String name) {
        System.out.println("Answer 'yes' if the number is even, otherwise answer 'no'.");

        for (int i = 0; i < ROUNDS_TO_WIN; i++) {
            int number = RANDOM.nextInt(100);
            System.out.println("Question: " + number);
            System.out.print("Your answer: ");

            String answer = scanner.nextLine().trim();

            boolean isEven = number % 2 == 0;
            String correctAnswer = isEven ? "yes" : "no";

            if (answer.equalsIgnoreCase(correctAnswer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("'" + answer + "' is wrong answer ;(. Correct answer was '" + correctAnswer + "'.");
                System.out.println("Let's try again, " + name + "!");
                return; // Завершаем игру при ошибке
            }
        }

        System.out.println("Congratulations, " + name + "!");
    }
}
