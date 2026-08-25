package hexlet.code.games;

import hexlet.code.Engine;
import hexlet.code.QuestionGenerator;
import hexlet.code.AnswerCalculator;

import java.util.Random;
import java.util.Scanner;

public class ProgressionGame {
    private static final String DESCRIPTION = "What number is missing in the progression?";
    private static final Random RANDOM = new Random();

    public static void play(Scanner scanner, String name) {
        System.out.println(DESCRIPTION);
        Engine.run(scanner, name, new ProgressionQuestionGenerator(), new ProgressionAnswerCalculator());
    }

    static class ProgressionQuestionGenerator implements QuestionGenerator {
        @Override
        public String generate() {
            int length = RANDOM.nextInt(6) + 5; // 5..10
            int start = RANDOM.nextInt(50);
            int step = RANDOM.nextInt(10) + 1; // 1..10

            int hiddenIndex = RANDOM.nextInt(length);

            StringBuilder questionBuilder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    questionBuilder.append(" ");
                }
                if (i == hiddenIndex) {
                    questionBuilder.append("..");
                } else {
                    int value = start + i * step;
                    questionBuilder.append(value);
                }
            }
            return questionBuilder.toString();
        }
    }

    static class ProgressionAnswerCalculator implements AnswerCalculator {
        @Override
        public String calculate(String question) {
            String[] parts = question.split(" ");

            int hiddenIndex = -1;
            for (int i = 0; i < parts.length; i++) {
                if ("..".equals(parts[i])) {
                    hiddenIndex = i;
                    break;
                }
            }

            int step = 0;
            // Ищем шаг по двум соседним известным числам
            for (int i = 0; i < parts.length - 1; i++) {
                if (!"..".equals(parts[i]) && !"..".equals(parts[i+1])) {
                    int num1 = Integer.parseInt(parts[i]);
                    int num2 = Integer.parseInt(parts[i+1]);
                    step = num2 - num1;
                    break;
                }
            }

            // Находим первое известное число, чтобы восстановить start
            int firstKnownIndex = -1;
            int firstKnownValue = 0;
            for (int i = 0; i < parts.length; i++) {
                if (!"..".equals(parts[i])) {
                    firstKnownIndex = i;
                    firstKnownValue = Integer.parseInt(parts[i]);
                    break;
                }
            }

            int start = firstKnownValue - (firstKnownIndex * step);
            int missingNumber = start + hiddenIndex * step;

            // ВАЖНО: возвращаем СТРОКУ
            return String.valueOf(missingNumber);
        }
    }
}
