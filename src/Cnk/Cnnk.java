package Cnk;

import java.util.*;

public class Cnnk {

    private static final int k = 3;
    private  static final int n = 3;
    private static int[] a;
    static ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();

    public static void main(String[] args) {

//        generateConstants(5, 6);



//        calculateCnk();
//        generateCnk(n, k);
//        System.out.println("Сгенерировано " + combinations.size() + " комбинаций: " + combinations);
    }

    static long calculateFactorial(long n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

//    public static long calculateCnk() {
//        long result = calculateFactorial(n + k - 1) / (calculateFactorial(k) * calculateFactorial(n - 1));
//        System.out.println("\nДля n = " + n + ", k = " + k + " всего " + result + " перестановок с повторениями:\n");
//        return result;
//    }

    public static ArrayList<ArrayList<Integer>> generateCnk(int n, int k) {
        a = new int[k];
        generateCombinations(0, 0, n - 1, k);
        return new ArrayList<ArrayList<Integer>>(combinations);
    }





    public static void generateCombinations(int pos, int maxUsed, int n, int k) {
        if (pos == k) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i : a)
                temp.add(i);
            if (!combinations.contains(temp))
                combinations.add(temp);
            ArrayList<Integer> temp2 = new ArrayList<>(temp);
            Collections.reverse(temp2);
            if (!combinations.contains(temp2))
                combinations.add(temp2);
        } else {
            for (int i = maxUsed; i <= n; i++) {
                a[pos] = i;
                generateCombinations(pos + 1, i, n, k);
            }
        }
    }

}
