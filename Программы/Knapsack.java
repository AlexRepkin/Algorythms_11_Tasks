import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Knapsack {

    static int allowedWeight = 10;  // Максимальный вес рюкзака

    // Восстановление решения с учетом повторяющихся предметов.
    public static List<Integer> reincarnate(int[][] dpTable, int[] weights, int[] values) {
        List<Integer> solution = new ArrayList<>();
        int remainingWeight = allowedWeight;
        int currentItem = weights.length;
        // Обратный проход для восстановления решения
        for (int i = weights.length - 1; i >= 0 && remainingWeight > 0 && currentItem > 0; i--) {
            int currentWeight = weights[i];
            if (dpTable[remainingWeight][currentItem] == dpTable[remainingWeight - currentWeight][currentItem - 1] + values[i]) {
                solution.add(1);  // Этот предмет включён в рюкзак.
                remainingWeight -= currentWeight;
            } else solution.add(0);  // Этот предмет не включён в рюкзак.
            currentItem--;
        }
        Collections.reverse(solution);
        return solution;
    }

    // Нахождение решения с использованием повторений. Затрачиваемое время = O(n * allowedWeight)
    static void knapsackWithRepetitionsBU(int[] weights, int[] values) {
        int[] dpTable = new int[allowedWeight + 1];
        for (int i = 0; i < weights.length; i++)
            for (int weight = weights[i]; weight <= allowedWeight; weight++)
                dpTable[weight] = Math.max(dpTable[weight], dpTable[weight - weights[i]] + values[i]);
        System.out.println("Method with repetitions counted price = " + dpTable[allowedWeight] + "\n");
    }

    // Нахождение решения без использования повторений. Затрачиваемое время = O(n * allowedWeight) (без учёта reincarnate)
    static void knapsackWithoutRepetitionsBU(int[] weights, int[] values) {
        int[][] dpTable = new int[allowedWeight + 1][weights.length + 1];
        for (int i = 0; i <= weights.length; i++) dpTable[0][i] = 0;
        for (int weight = 0; weight <= allowedWeight; weight++) dpTable[weight][0] = 0;
        for (int item = 1; item <= weights.length; item++) {
            for (int weight = 1; weight <= allowedWeight; weight++) {
                dpTable[weight][item] = dpTable[weight][item - 1];
                int currentWeight = weights[item - 1];
                if (currentWeight <= weight)
                    dpTable[weight][item] = Math.max(dpTable[weight][item], dpTable[weight
                            - currentWeight][item - 1] + values[item - 1]);
            }
        }
        List<Integer> reincarnation = reincarnate(dpTable, weights, values);
        System.out.println("Method without repetitions counted price = " + dpTable[allowedWeight][weights.length]);
        System.out.println("Included elements are:");
        for (int i = 0; i < reincarnation.size(); i++)
            if (reincarnation.get(i) == 1)
                System.out.println("Element" + (i+1) + ", which weights " + weights[i] + " and costs " + values[i]);
    }

    public static void main(String[] args) {
        System.out.println("Good day! It's KnapSack program!\nSack can keep weight = " + allowedWeight);
        int[] itemsWeight = new int[]{6, 3, 4, 2};
        int[] values = new int[]{30, 14, 16, 9};
        for (int i = 0; i < itemsWeight.length; i++) System.out.println((i+1) + " thing weights " + itemsWeight[i] + " and costs " + values[i]);
        knapsackWithoutRepetitionsBU(itemsWeight, values);
        knapsackWithRepetitionsBU(itemsWeight, values);
    }
}
