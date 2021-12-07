package LinearSpace;

import java.util.ArrayList;

public interface LieAlgebra {

    String getAlgebraName();

    ArrayList<int[][]> generateMatricesH(int dim);

    ArrayList<int[][]> generateSpecificMatrices(int dim);

    ArrayList<GeomVector> generateVectorsA(ArrayList<int[][]> h, ArrayList<int[][]> f);

    ArrayList<GeomVector> generateVectorsB(ArrayList<GeomVector> vectorsA,int modP);

}
