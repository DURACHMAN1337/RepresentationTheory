package Services;

import LinearSpace.GeomVector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LinearSpaceServiceBean implements LinearSpaceService {
    @Override
    public Set<GeomVector> generateLinearSpace(ArrayList<ArrayList<Integer>> combinations, ArrayList<GeomVector> vectors, int mod) {
        Set<GeomVector> linearSpace = new HashSet<GeomVector>(vectors);
        for (ArrayList<Integer> combination : combinations) {
            linearSpace.add(calculateLinearCombination(vectors, combination, mod));
        }
        return linearSpace;
    }

    @Override
    public GeomVector calculateLinearCombination(ArrayList<GeomVector> vectors, ArrayList<Integer> combination, int mod) {
        ArrayList<GeomVector> newVectors = new ArrayList<>();

        for (int i = 0; i < combination.size(); i++) {
            newVectors.add(GeomVector.multiplyByNumber(mod, vectors.get(i), combination.get(i)));
        }

        return GeomVector.sumVectors(mod, newVectors);
    }

    @Override
    public ArrayList<ArrayList<Integer>> generateConstants(int mod, int dim) {
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

    @Override
    public int calculateDistance(Set<GeomVector> LinearSpace) {
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
