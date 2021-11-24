package CayleyTable;

public class CayleyTable {

    public static int mod = 17;

    public static void main(String[] args) {
        generateMultiplySquare(mod);
        generateSumSquare(mod);
    }

    public static void generateMultiplySquare(int mod) {
        System.out.println("Квадрат для произведения: ");
        int modSize = String.valueOf(mod).length();
        for (int i = 1; i < mod; i++) {
            for (int j = 1; j < mod; j++) {
                String number = String.format("%0" + modSize + "d", (i * j) % mod);
                System.out.print(number + " ");
                }
            System.out.println();
        }
        System.out.println();
    }

    public static void generateSumSquare(int mod) {
        System.out.println("Квадрат для суммы: ");
        int modSize = String.valueOf(mod).length();
        for (int i = 1; i < mod; i++) {
            for (int j = 1; j < mod; j++) {
                String number = String.format("%0" + modSize + "d", (i + j) % mod);
                System.out.print(number + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
