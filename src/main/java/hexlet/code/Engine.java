package hexlet.code;

import java.util.Scanner;

public class Engine {
    private static final int ROUNDS_COUNT = 3;

    public static void run(Scanner scanner, String name, QuestionGenerator questionGenerator, AnswerCalculator answerCalculator) {
        System.out.println("Welcome to the Brain Games!");
        System.out.println("Let's try to solve them.");

        for (int i = 0; i < ROUNDS_COUNT; i++) {
            String question = questionGenerator.generate();
            System.out.print("Question: " + question + "\nYour answer: ");

            // Читаем ответ пользователя как строку
            String userAnswer = scanner.nextLine().trim();

            // Получаем правильный ответ как строку
            String correctAnswer = answerCalculator.calculate(question);

            if (userAnswer.equals(correctAnswer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("'" + userAnswer + "' is wrong answer ;(. Correct answer was '" + correctAnswer + "'.");
                System.out.println("Let's try again, " + name + "!");
                return;
            }
        }

        System.out.println("Congratulations, " + name + "!");
    }
}
