package LinearSpace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static LinearSpace.SpnC.*;


public class LinearSpaceSpnC {
    public static int dim = 4; //размерность алгебры Sp
    public static SpnC spnC = new SpnC();
    public static ArrayList<int[][]> matricesH = spnC.generateMatricesH(dim); // Генерируем матрицы h
    public static ArrayList<int[][]> matricesF = spnC.generateMatricesF(dim); // Генерируем матрицы f
    public static ArrayList<GeomVector> vectorsA = generateVectorsA(matricesH, matricesF); // Генерируем Векторы a
    public static ArrayList<GeomVector> vectorsB = generateVectorsB(vectorsA); // Генерируем Векторы b

    public static Set<GeomVector> linearSpace = new HashSet<GeomVector>(vectorsB);


    public static int p = 3; // Модуль простого числа
    public static int vectorsCount = vectorsB.size(); // Колличество векторов


    public static ArrayList<ArrayList<Integer>> combinations = generateConstants(p, vectorsCount); // генерация перестановок


    public static void main(String[] args) {

        System.out.println("-----------------Матрицы  h-----------------");
        printListOfMatrices(matricesH);

        System.out.println("-----------------Матрицы  f-----------------");
        printListOfMatrices(matricesF);

        System.out.println("-----------------Векторы а-----------------");
        System.out.println("Всего векторов A : " + vectorsA.size());
        System.out.println(vectorsA);

        System.out.println("-----------------Векторы b-----------------");
        System.out.println(vectorsB);

        System.out.println("-----------------Перестановки для линейных комбинаций векторов-----------------");


        System.out.println("Всего перестановок :" + combinations.size() + " " + combinations);

        generateLinearSpace(vectorsB);

        System.out.println("Линейная оболочка из " + linearSpace.size() + " векторов: " + linearSpace);

        System.out.println("Минимальное Кодовое расстояние : " + calculateDistance(linearSpace));
    }


    public static void generateLinearSpace(ArrayList<GeomVector> vectors) {
        for (ArrayList<Integer> combination : combinations) {
            linearSpace.add(calculateLinearCombination(vectors, combination));
        }
    }

    public static GeomVector calculateLinearCombination(ArrayList<GeomVector> vectors, ArrayList<Integer> combination) {
        ArrayList<GeomVector> newVectors = new ArrayList<>();

        for (int i = 0; i < combination.size(); i++) {
            newVectors.add(GeomVector.multiplyByNumber(p, vectors.get(i), combination.get(i)));
        }

        return GeomVector.sumVectors(p, newVectors);
    }


    public static ArrayList<ArrayList<Integer>> generateConstants(int mod, int dim) {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        ArrayList<Integer> combination = new ArrayList<>();
        ArrayList<Integer> temp;

        for (int j = 0; j < dim; j++) {
            combination.add(0);
        }
        combinations.add(combination);
        temp = new ArrayList<>(combination);

        while (combinations.size() < (int) Math.pow(mod, dim)) {
            for (int i = 0; i < dim; i++) {
                temp.set(i, (int) (Math.random() * mod));
            }
            if (!combinations.contains(temp)) {
                combinations.add(new ArrayList<>(temp));
            }
        }
        return combinations;
    }

    public static int calculateDistance(Set<GeomVector> LinearSpace) {
        int min = 100;
        int weight = 0;
        for (GeomVector geomVector : LinearSpace) {
            for (int i = 0; i < geomVector.getCoordinates().size(); i++) {
                if (geomVector.getCoordinates().get(i) == 0)
                    weight++;
            }
            if (weight < min)
                min = weight;
            weight = 0;
        }
        return min;
    }

}
