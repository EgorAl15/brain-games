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

    // Генератор вопроса: создаёт строку вида "5 7 9 11 .. 15 17"
    static class ProgressionQuestionGenerator implements QuestionGenerator {
        @Override
        public String generate() {
            // 1. Параметры прогрессии
            int length = RANDOM.nextInt(6) + 5; // Длина от 5 до 10 (5 + 0..5)
            int start = RANDOM.nextInt(50);      // Начальное число (0..49)
            int step = RANDOM.nextInt(10) + 1;  // Шаг прогрессии (1..10), чтобы не было 0

            // 2. Позиция пропущенного элемента (от 0 до length-1)
            int hiddenIndex = RANDOM.nextInt(length);

            // 3. Формируем строку вопроса
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

    // Калькулятор ответа: по строке вопроса находит пропущенное число
    static class ProgressionAnswerCalculator implements AnswerCalculator {
        @Override
        public int calculate(String question) {
            String[] parts = question.split(" ");

            // Находим индекс пропуска ".."
            int hiddenIndex = -1;
            for (int i = 0; i < parts.length; i++) {
                if ("..".equals(parts[i])) {
                    hiddenIndex = i;
                    break;
                }
            }

            // Чтобы найти шаг прогрессии, нам нужно два соседних числа.
            // Так как пропуск может быть в начале или конце, ищем любые два известных числа подряд.
            int step = 0;

            // Ищем шаг, пропуская скрытый элемент
            for (int i = 0; i < parts.length - 1; i++) {
                if (!"..".equals(parts[i]) && !"..".equals(parts[i+1])) {
                    int num1 = Integer.parseInt(parts[i]);
                    int num2 = Integer.parseInt(parts[i+1]);
                    step = num2 - num1;
                    break; // Нашли шаг, дальше искать не нужно
                }
            }

            // Теперь вычисляем пропущенное число по формуле
            // Нам нужно знать первое число прогрессии.
            // Если первый элемент не скрыт, то start = parts[0].
            // Если скрыт, идём вперёд до первого известного числа и вычитаем шаги.

            int firstKnownIndex = -1;
            int firstKnownValue = 0;

            for (int i = 0; i < parts.length; i++) {
                if (!"..".equals(parts[i])) {
                    firstKnownIndex = i;
                    firstKnownValue = Integer.parseInt(parts[i]);
                    break;
                }
            }

            // Восстанавливаем start: start = knownValue - (index * step)
            int start = firstKnownValue - (firstKnownIndex * step);

            // Считаем искомое число
            return start + hiddenIndex * step;
        }
    }
}
