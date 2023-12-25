import java.util.ArrayList;

public class Fibonachi {

    static int needed = 10;
    static ArrayList<Integer> numbers = new ArrayList<>();

    // Время выполнения = O(n*n).
    static Integer fibTD(int length) {
        if (numbers.get(length) == -1) {
            if (length <= 1) numbers.set(length, length);
            else numbers.set(length, fibTD(length - 1) + fibTD(length - 2));
        }
        return numbers.get(length);
    }

    // Время выполнения = O(n*n). Итеративное решение с использованием массива.
    static Integer fibBU(int length) {
        ArrayList<Integer> values = new ArrayList<>();
        values.add(0);
        values.add(1);
        for (int i = 2; i < needed; i++) values.add(values.get(i - 1) + values.get(i - 2));
        return values.get(length);
    }

    // Время выполнения = O(n*n). Используются только три значения, а не целый массив - прошлый, текущий, следующий элементы.
    static Integer fibBUImproved(int length) {
        if (length <= 1) return length;
        int previous = 0;
        int current = 1;
        int next;
        for (int i = 1; i < length; i++) {
            next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    public static void main(String[] args) {
        for (int i = 0; i < needed; i++) numbers.add(-1);
        System.out.println("Good day! Needed element is on position " + needed);
        System.out.println("FibTD thinks, that answer is " + fibTD(needed - 1));
        System.out.println("FibBU thinks, that answer is " + fibBU(needed - 1));
        System.out.println("Improved FibBU thinks, that answer is " + fibBUImproved(needed - 1));
    }
}
