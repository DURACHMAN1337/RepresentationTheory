package LinearSpace;

import Services.MatricesServiceBean;

import java.util.ArrayList;
import java.util.Arrays;

public class SlnC implements LieAlgebra {
    private static MatricesServiceBean matricesServiceBean = new MatricesServiceBean();
    private static LieAlgebra slnC = new SlnC();

    public static void main(String[] args) {
        int modP = 3;
        ArrayList<int[][]> eMatrix = slnC.generateSpecificMatrices(3);
        ArrayList<int[][]> hMatrix = slnC.generateMatricesH(3);
        ArrayList<GeomVector> vectorsA = slnC.generateVectorsA(hMatrix, eMatrix);
        ArrayList<GeomVector> vectorsB = slnC.generateVectorsB(vectorsA,modP);
        System.out.println(vectorsB);
    }

    public ArrayList<int[][]> generateMatricesH(int dim) {

        ArrayList<int[][]> result = new ArrayList<>();
        int[][] matrix;

        for (int i = 0; i < dim - 1; i++) {
            matrix = new int[dim][dim];
            matrix[i][i] = 1;
            matrix[i + 1][i + 1] = -1;

            result.add(matrix);
        }
        return result;

    }

    public ArrayList<int[][]> generateSpecificMatrices(int dim) {
        ArrayList<int[][]> result = new ArrayList<>();
        int[][] matrix;

        for (int i = 0; i < dim; i++) {
            matrix = new int[dim][dim];

            for (int j = 0; j < matrix[i].length; j++) {

                if (i != j) {
                    matrix[i][j] = 1;
                    result.add(matrix);
                    matrix = new int[dim][dim];
                }
            }
        }
        return result;
    }

    public ArrayList<GeomVector> generateVectorsA(ArrayList<int[][]> h, ArrayList<int[][]> e) {
        int[][] nullMatrix = new int[h.get(0).length][h.get(0).length];
        ArrayList<int[][]> basis = new ArrayList<>();
        ArrayList<int[][]> temp = new ArrayList<>();
        ArrayList<Integer> vectorsA = new ArrayList<>();
        ArrayList<ArrayList<Integer>> vectorsAA = new ArrayList<>();
        ArrayList<GeomVector> vectors = new ArrayList<>();
        basis.addAll(h);
        basis.addAll(e);

        for (int i = 0; i < h.size(); i++) {
            for (int[][] matrix : basis) {
                temp.add(matricesServiceBean.matricesMinus(matricesServiceBean.multiplyMatrices(h.get(i), matrix), matricesServiceBean.multiplyMatrices(matrix, h.get(i))));
            }
        }

        for (int[][] matrix : temp) {
            if (Arrays.deepEquals(matrix, nullMatrix) == true) {
                vectorsA.add(0);
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {

                    if (matrix[i][j] != 0) {
                        vectorsA.add(matrix[i][j]);
                    }
                }
            }
        }


        final int l = vectorsA.size() / h.size();
        final int n = vectorsA.size();
        for (int i = 0; i < n; i += l) {
            vectorsAA.add(new ArrayList<Integer>(vectorsA.subList(i, Math.min(n, i + l))));
        }
        for (ArrayList<Integer> list : vectorsAA) {
            vectors.add(new GeomVector(list));
        }
        return vectors;
    }

    public ArrayList<GeomVector> generateVectorsB(ArrayList<GeomVector> vectorsA,int modP) {
        ArrayList<GeomVector> vectorsB = new ArrayList<>();
        for (GeomVector geomVector : vectorsA) {
            ArrayList<Integer> newCoordinates = new ArrayList<>(geomVector.getCoordinates());
            for (int i = 0; i < newCoordinates.size(); i++) {
                if (newCoordinates.get(i) < 0) {
                    newCoordinates.set(i, newCoordinates.get(i) + modP);
                }
            }
            vectorsB.add(new GeomVector(newCoordinates));
        }
        return vectorsB;
    }
}
