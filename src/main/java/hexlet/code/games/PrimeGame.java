package hexlet.code.games;

import hexlet.code.Engine;
import hexlet.code.QuestionGenerator;
import hexlet.code.AnswerCalculator;
import hexlet.code.utils.PrimeUtils;

import java.util.Random;
import java.util.Scanner;

public class PrimeGame {
    private static final String DESCRIPTION = "Answer 'yes' if given number is prime. Otherwise answer 'no'.";
    private static final Random RANDOM = new Random();

    public static void play(Scanner scanner, String name) {
        System.out.println(DESCRIPTION);
        Engine.run(scanner, name, new PrimeQuestionGenerator(), new PrimeAnswerCalculator());
    }

    static class PrimeQuestionGenerator implements QuestionGenerator {
        @Override
        public String generate() {
            int number = RANDOM.nextInt(99) + 2; // от 2 до 100
            return String.valueOf(number);
        }
    }

    static class PrimeAnswerCalculator implements AnswerCalculator {
        @Override
        public String calculate(String question) {
            int number = Integer.parseInt(question);
            return PrimeUtils.isPrime(number) ? "yes" : "no";
        }
    }
}
