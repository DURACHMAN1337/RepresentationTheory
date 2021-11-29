package Services;

import java.util.ArrayList;

public interface MatricesService {
    int[][] multiplyMatrices(int[][] a, int[][] b);

    int[][] multiplyMatrixByNumber(int[][] matrix, int num);

    void printListOfMatrices(ArrayList<int[][]> list);

    void printMatrix(int[][] matrix);

    int[][] matricesSum(int[][] a, int[][] b);

    int[][] matricesMinus(int[][] a, int[][] b);
}
