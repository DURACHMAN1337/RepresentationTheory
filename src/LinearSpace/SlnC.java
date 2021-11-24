package LinearSpace;

import java.util.ArrayList;
import java.util.Arrays;

public class SlnC {

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

    public ArrayList<int[][]> generateMatricesE(int dim) {
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

    public static void printListOfMatrices(ArrayList<int[][]> list) {
        for (int i = 0; i < list.size(); i++) {
            printMatrix(list.get(i));
            System.out.println();
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b.length];
        for (int i = 0; i < a.length; ++i)
            for (int j = 0; j < b.length; ++j)
                for (int k = 0; k < a.length; ++k)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    public static ArrayList<GeomVector> generateVectorsA(ArrayList<int[][]> h, ArrayList<int[][]> e) {
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
                temp.add(matricesMinus(multiplyMatrices(h.get(i), matrix), multiplyMatrices(matrix, h.get(i))));

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
            vectorsAA.add(new ArrayList<Integer>(vectorsA.subList(i,Math.min(n,i + l))));
        }
        for (ArrayList<Integer> list : vectorsAA) {
            vectors.add(new GeomVector(list));
        }



        return vectors;
    }

    public static ArrayList<GeomVector> generateVectorsB(ArrayList<GeomVector> vectorsA){
        ArrayList<GeomVector> vectorsB = new ArrayList<>();
        for (GeomVector geomVector : vectorsA) {
            ArrayList<Integer> newCoordinates = new ArrayList<>(geomVector.getCoordinates());
            for (int i = 0; i < newCoordinates.size(); i++) {
                if (newCoordinates.get(i) < 0){
                    newCoordinates.set(i,newCoordinates.get(i) + 3);
                }
            }
            vectorsB.add(new GeomVector(newCoordinates));
        }



        return vectorsB;
    }

    public static int[][] matricesSum(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    public static int[][] matricesMinus(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        SlnC slnC = new SlnC();
        ArrayList<int[][]> eMatrix = slnC.generateMatricesE(3);
        ArrayList<int[][]> hMatrix = slnC.generateMatricesH(3);
        ArrayList<GeomVector> vectorsA = generateVectorsA(hMatrix,eMatrix);
        ArrayList<GeomVector> vectorsB = generateVectorsB(vectorsA);
        System.out.println(vectorsB);




//        printMatrix(nullMatrix);
//        printMatrix(nullMatrix1);
//        System.out.println(Arrays.deepEquals(nullMatrix1, nullMatrix));



    }


}
