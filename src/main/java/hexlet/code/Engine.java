package hexlet.code;

import java.util.Scanner;

public class Engine {
    private static final int ROUNDS_TO_WIN = 3;

    /**
     * Запускает игровой цикл.
     * @param scanner объект Scanner для ввода
     * @param name имя игрока
     * @param generateQuestion функция, которая возвращает текст вопроса
     * @param calculateAnswer функция, которая считает правильный ответ по вопросу
     */
    public static void run(Scanner scanner, String name,
                           QuestionGenerator questionGenerator,
                           AnswerCalculator answerCalculator) {

        System.out.println("What is the result of the expression?"); // Специфичное сообщение для калькулятора, но можно сделать общим позже

        for (int i = 0; i < ROUNDS_TO_WIN; i++) {
            String question = questionGenerator.generate();
            int correctAnswer = answerCalculator.calculate(question);

            System.out.println("Question: " + question);
            System.out.print("Your answer: ");

            // Читаем ответ. Важно: trim() убирает лишние пробелы
            String userInput = scanner.nextLine().trim();

            try {
                int userAnswer = Integer.parseInt(userInput);

                if (userAnswer == correctAnswer) {
                    System.out.println("Correct!");
                } else {
                    System.out.println("'" + userInput + "' is wrong answer ;(. Correct answer was '" + correctAnswer + "'.");
                    System.out.println("Let's try again, " + name + "!");
                    return; // Завершаем игру при ошибке
                }
            } catch (NumberFormatException e) {
                // Если пользователь ввел не число (например, "abc"), считаем это ошибкой
                System.out.println("'" + userInput + "' is not a valid number.");
                System.out.println("Let's try again, " + name + "!");
                return;
            }
        }

        System.out.println("Congratulations, " + name + "!");
    }
}
