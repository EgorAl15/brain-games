package hexlet.code.games;

import hexlet.code.Engine;
import hexlet.code.QuestionGenerator;
import hexlet.code.AnswerCalculator;
import hexlet.code.utils.GcdUtils; // Убедись, что у тебя есть этот файл с методом computeGcd

import java.util.Random;
import java.util.Scanner;

public class GcdGame {
    private static final String DESCRIPTION = "Find the greatest common divisor of given numbers.";
    private static final Random RANDOM = new Random();

    public static void play(Scanner scanner, String name) {
        System.out.println(DESCRIPTION);
        Engine.run(scanner, name, new GcdQuestionGenerator(), new GcdAnswerCalculator());
    }

    static class GcdQuestionGenerator implements QuestionGenerator {
        @Override
        public String generate() {
            int a = RANDOM.nextInt(50) + 1;
            int b = RANDOM.nextInt(50) + 1;
            // Возвращаем строку вида "15 25"
            return a + " " + b;
        }
    }

    static class GcdAnswerCalculator implements AnswerCalculator {
        @Override
        public String calculate(String question) {
            String[] parts = question.split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);

            int result = GcdUtils.computeGcd(a, b);

            // ВАЖНО: возвращаем СТРОКУ, а не int!
            return String.valueOf(result);
        }
    }
}
