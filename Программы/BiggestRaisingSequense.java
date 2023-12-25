import java.util.ArrayList;
import java.util.Random;

public class BiggestRaisingSequense {
    static int needed = 20;

    // Время выполнения = O(n*n).
    static Integer LISBottomUp(ArrayList<Integer> numbers) {
        ArrayList<Integer> additional = new ArrayList<>();
        for (int i = 0; i < needed; i++) additional.add(i);
        for (int i = 0; i < needed; i++) {
            additional.set(i, 1);
            for (int j = 0; j < i - 1; j++)
                if (numbers.get(j) < numbers.get(i) && additional.get(j) + 1 > additional.get(i))
                    additional.set(i, additional.get(j) + 1);
        }
        int ans = 0;
        for (int i = 0; i < needed; i++) ans = (ans >= additional.get(i)) ? ans : additional.get(i);
        return ans;
    }

    // Время выполнения = O(n*n).
    static ArrayList<Integer> LISBottomUpWithSequence(ArrayList<Integer> numbers) {
        ArrayList<Integer> additional = new ArrayList<>();
        ArrayList<Integer> previous = new ArrayList<>();
        for (int i = 0; i < needed; i++) {
            additional.add(i);
            previous.add(i);
        }
        for (int i = 0; i < needed; i++) {
            additional.set(i, 1);
            previous.set(i, -1);
            for (int j = 0; j < i - 1; j++)
                if (numbers.get(j) < numbers.get(i) && additional.get(j) + 1 > additional.get(i)) {
                    additional.set(i, additional.get(j) + 1);
                    previous.set(i, j);
                }
        }
        int ans = 0;
        for (int i = 0; i < needed; i++) ans = (ans >= additional.get(i)) ? ans : additional.get(i);
        System.out.println("Function with previous counted elements:");
        printingWithPrevious(ans, additional, previous, numbers);
        System.out.println("Function without previous counted elements:");
        printingWithoutPrevious(ans, additional, numbers);
        return additional;
    }

    static void printingWithPrevious(int ans, ArrayList<Integer> additional, ArrayList<Integer> previous, ArrayList<Integer> numbers) {
        ArrayList<Integer> reincarnate = new ArrayList<>();
        for (int i = 0; i < ans; i++) reincarnate.add(i);
        int k = 0;
        for (int i = 1; i < needed; i++)
            if (additional.get(i) > additional.get(k)) k = i;
        int j = ans - 1;
        while (k > 0) {
            reincarnate.set(j, k);
            j--;
            k = previous.get(k);
        }
        for (int i = 0; i < ans; i++) System.out.print(numbers.get(reincarnate.get(i)) + ", ");
        System.out.println();
    }

    static void printingWithoutPrevious(int ans, ArrayList<Integer> additional, ArrayList<Integer> numbers) {
        int element = ans;
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = needed - 1; i > -1; i--)
            if (additional.get(i) == element && (values.isEmpty() || values.get(values.size() - 1) > numbers.get(i))) {
                values.add(numbers.get(i));
                element--;
            }
        for (int i = ans - 1; i > -1; i--) System.out.print(values.get(i) + ", ");
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < needed; i++) numbers.add(random.nextInt(250));
        System.out.println("Good day! elements are:" + numbers);
        System.out.println("LISBottomUp thinks that biggest sequence is " + LISBottomUp(numbers));
        System.out.println("LISBottomUpWithSequence gave indexes to elements: " + LISBottomUpWithSequence(numbers));
    }
}
