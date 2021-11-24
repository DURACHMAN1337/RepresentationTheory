package Cnk;

import java.util.*;

public class Cnk {

    private static final int n = 6;
    private static final int k = 3;

    public static void main(String[] args) {
        calculateCnk();
        for(ArrayList<Integer> list : generateCnk(n,k))
            System.out.println(list);
    }

    static long calculateFactorial(long n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *=  i;
        }
        return result;
    }

    public static long calculateCnk() {
        long result = calculateFactorial(n) / (calculateFactorial(k) * calculateFactorial(n - k));
        System.out.println("\nДля n = " + n + ", k = " + k + " всего " + result + " перестановок:\n");
        return result;
    }

    public static ArrayList<ArrayList<Integer>> generateCnk(int n, int k) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int[] arr = null;
        while ((arr = generateCombinations(arr,n,k)) != null) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int value : arr)
                temp.add(value);
            list.add(temp);
        }
        return list;
    }

    private static int[] generateCombinations(int[] arr, int n, int k) {
        if (arr == null) {
            arr = new int[k];
            for (int i = 0; i < k; i++)
                arr[i] = i + 1;
            return arr;
        }
        for (int i = k - 1; i >= 0; i--)
            if (arr[i] < n - k + i + 1) {
                arr[i]++;
                for (int j = i; j < k - 1; j++)
                    arr[j + 1] = arr[j] + 1;
                return arr;
            }
        return null;
    }
}
