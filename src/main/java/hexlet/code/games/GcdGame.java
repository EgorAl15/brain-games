package hexlet.code.games;

import hexlet.code.Engine;
import hexlet.code.QuestionGenerator;
import hexlet.code.AnswerCalculator;
import hexlet.code.utils.GcdUtils;

import java.util.Random;
import java.util.Scanner;

public class GcdGame {
    private static final String DESCRIPTION = "Find the greatest common divisor of given numbers.";
    private static final Random RANDOM = new Random();

    public static void play(Scanner scanner, String name) {
        System.out.println(DESCRIPTION); // Выводим описание перед началом раундов
        Engine.run(scanner, name, new GcdQuestionGenerator(), new GcdAnswerCalculator());
    }

    // Генератор вопроса: создает строку "число1 число2"
    static class GcdQuestionGenerator implements QuestionGenerator {
        @Override
        public String generate() { // <-- Важно: именно generate(), а не generateQuestion()
            int num1 = RANDOM.nextInt(1, 100);
            int num2 = RANDOM.nextInt(1, 100);
            return num1 + " " + num2;
        }
    }

    // Калькулятор ответа: вычисляет правильный НОД
    static class GcdAnswerCalculator implements AnswerCalculator {
        @Override
        public int calculate(String question) { // <-- Именно calculate(), а не calculateAnswer()
            String[] parts = question.split(" ");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            return GcdUtils.computeGcd(num1, num2); // Возвращаем int, как требует интерфейс
        }
    }
}
