package hexlet.code.games;

import hexlet.code.QuestionGenerator;
import hexlet.code.AnswerCalculator;
import java.util.Random;
import java.util.Scanner;
import hexlet.code.Engine;


public class CalcGame {

    private static final Random RANDOM = new Random();
    private static final String[] OPERATORS = {"+", "-", "*"};

    // Класс-обертка, который реализует генерацию вопроса
    public static class CalcQuestionGenerator implements QuestionGenerator {
        @Override
        public String generate() {
            int num1 = RANDOM.nextInt(100); // Число от 0 до 99
            int num2 = RANDOM.nextInt(100);
            String operator = OPERATORS[RANDOM.nextInt(OPERATORS.length)];

            return num1 + " " + operator + " " + num2;
        }
    }

    // Класс-обертка, который считает ответ по строке вопроса
    public static class CalcAnswerCalculator implements AnswerCalculator {
        @Override
        public int calculate(String question) {
            // Разбиваем строку "12 + 5" на части
            String[] parts = question.split(" ");
            int a = Integer.parseInt(parts[0]);
            String op = parts[1];
            int b = Integer.parseInt(parts[2]);

            switch (op) {
                case "+":
                    return a + b;
                case "-":
                    return a - b;
                case "*":
                    return a * b;
                default:
                    throw new IllegalArgumentException("Unknown operator: " + op);
            }
        }
    }

    public static void play(Scanner scanner, String name) {
        // Передаем в движок наши генераторы и калькуляторы
        Engine.run(scanner, name, new CalcQuestionGenerator(), new CalcAnswerCalculator());
    }
}
