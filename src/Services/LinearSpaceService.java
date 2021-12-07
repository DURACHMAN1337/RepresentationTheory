package Services;

import LinearSpace.GeomVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface LinearSpaceService {
    Set<GeomVector> generateLinearSpace(ArrayList<ArrayList<Integer>> combinations, ArrayList<GeomVector> vectors, int mod);
    GeomVector calculateLinearCombination(ArrayList<GeomVector> vectors, ArrayList<Integer> combination, int mod);
    int calculateDistance(Set<GeomVector> LinearSpace);
    ArrayList<ArrayList<Integer>> generateConstants(int vectorDimension, int mod);
}
