package hexlet.code.utils;

public class PrimeUtils {
    /**
     * Предикат: возвращает true, если число простое.
     */
    public static boolean isPrime(int n) {
        // Числа меньше 2 не являются простыми
        if (n < 2) {
            return false;
        }
        // 2 — простое
        if (n == 2) {
            return true;
        }
        // Чётные числа больше 2 — не простые
        if (n % 2 == 0) {
            return false;
        }

        // Проверяем делители от 3 до sqrt(n) с шагом 2
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
