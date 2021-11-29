package Services;

import LinearSpace.GeomVector;

import java.util.ArrayList;
import java.util.Set;

public interface LinearSpaceService {
    Set<GeomVector> generateLinearSpace(ArrayList<ArrayList<Integer>> combinations, ArrayList<GeomVector> vectors, int mod);
    GeomVector calculateLinearCombination(ArrayList<GeomVector> vectors, ArrayList<Integer> combination, int mod);
    ArrayList<ArrayList<Integer>> generateConstants(int mod, int dim);
    int calculateDistance(Set<GeomVector> LinearSpace);
}
