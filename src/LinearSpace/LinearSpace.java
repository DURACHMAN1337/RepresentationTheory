package LinearSpace;

import Services.LinearSpaceServiceBean;
import Services.MatricesServiceBean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class LinearSpace {
    private static final MatricesServiceBean matricesServiceBean = new MatricesServiceBean();
    private static final LinearSpaceServiceBean linearSpaceServiceBean = new LinearSpaceServiceBean();

    public static void main(String[] args) {
        LinearSpace linearSpace = new LinearSpace();
        LieAlgebra lieAlgebra = new SlnC();
        linearSpace.generateLinearCodeAssociatedWithAdditionalRepresentation(lieAlgebra,3,4);
    }
    
    private void generateLinearCodeAssociatedWithAdditionalRepresentation(LieAlgebra lieAlgebra, int modP, int dim){
        BigInteger bigInteger = BigInteger.valueOf(modP);

        if (bigInteger.isProbablePrime(modP)){
        ArrayList<int[][]> matricesH = lieAlgebra.generateMatricesH(dim); // Генерируем матрицы h
        ArrayList<int[][]> matricesF = lieAlgebra.generateSpecificMatrices(dim); // Генерируем матрицы f
        ArrayList<GeomVector> vectorsA = lieAlgebra.generateVectorsA(matricesH, matricesF); // Генерируем Векторы a
        ArrayList<GeomVector> vectorsB = lieAlgebra.generateVectorsB(vectorsA,modP); // Генерируем Векторы b
        int vectorsCount = vectorsB.size(); // Колличество векторов
        ArrayList<ArrayList<Integer>> combinations = linearSpaceServiceBean.generateConstants(vectorsCount, modP); // генерация перестановок
        Set<GeomVector> linearSpace = new HashSet<GeomVector>(vectorsB);

        System.out.println("Линейный код ассоцированный с присоединённым представлением для : " + lieAlgebra.getAlgebraName().replaceFirst("n", String.valueOf(dim)));
        System.out.println("-----------------Матрицы  h-----------------");
        matricesServiceBean.printListOfMatrices(matricesH);
        System.out.println("-----------------Специальные Матрицы  -----------------");
        matricesServiceBean.printListOfMatrices(matricesF);
        System.out.println("-----------------Векторы а-----------------");
        System.out.println("Всего векторов A : " + vectorsA.size());
        System.out.println(vectorsA);
        System.out.println("-----------------Векторы b-----------------");
        System.out.println(vectorsB);
        System.out.println("-----------------Перестановки для линейных комбинаций векторов-----------------");
        System.out.println("Всего перестановок :" + combinations.size() + " " + combinations);
        linearSpaceServiceBean.generateLinearSpace(combinations,vectorsB,modP);
        System.out.println("Линейная оболочка из " + linearSpace.size() + " векторов: " + linearSpace);
        System.out.println("Минимальное Кодовое расстояние : " + linearSpaceServiceBean.calculateDistance(linearSpace));
        }else {
            System.err.println("модуль числа должен быть простым");
        }
    }
}
