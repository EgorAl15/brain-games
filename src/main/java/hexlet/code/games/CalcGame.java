package hexlet.code.games;

import hexlet.code.Engine;
import hexlet.code.QuestionGenerator;
import hexlet.code.AnswerCalculator;

import java.util.Random;
import java.util.Scanner;

public class CalcGame {
    private static final String DESCRIPTION = "What is the result of the expression?";
    private static final Random RANDOM = new Random();

    public static void play(Scanner scanner, String name) {
        System.out.println(DESCRIPTION);
        Engine.run(scanner, name, new CalcQuestionGenerator(), new CalcAnswerCalculator());
    }

    static class CalcQuestionGenerator implements QuestionGenerator {
        @Override
        public String generate() {
            int a = RANDOM.nextInt(50) + 1;
            int b = RANDOM.nextInt(50) + 1;

            // Выбираем случайную операцию: 0 -> +, 1 -> -, 2 -> *
            int opIndex = RANDOM.nextInt(3);
            char operator;
            int result;

            if (opIndex == 0) {
                operator = '+';
                result = a + b;
            } else if (opIndex == 1) {
                operator = '-';
                result = a - b;
            } else {
                operator = '*';
                result = a * b;
            }

            // Сохраняем правильный ответ во временное поле или передаем через строку вопроса?
            // В этой архитектуре правильный ответ считается отдельно в Calculator.
            // Поэтому здесь мы просто возвращаем строку вопроса: "5 + 7"
            return a + " " + operator + " " + b;
        }
    }

    static class CalcAnswerCalculator implements AnswerCalculator {
        @Override
        public String calculate(String question) {
            // Разбираем строку вопроса обратно на числа и оператор
            String[] parts = question.split(" ");
            int a = Integer.parseInt(parts[0]);
            char operator = parts[1].charAt(0);
            int b = Integer.parseInt(parts[2]);

            int result;
            switch (operator) {
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
                case '*':
                    result = a * b;
                    break;
                default:
                    // На случай ошибки парсинга, хотя генератор гарантирует корректность
                    return "0";
            }

            // ВАЖНО: Возвращаем СТРОКУ, а не int!
            return String.valueOf(result);
        }
    }
}
