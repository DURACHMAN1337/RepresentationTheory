package LinearSpace;

import java.util.*;

public class GeomVector {

    private ArrayList<Integer> coordinates;


    public GeomVector(ArrayList<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public static GeomVector multiplyByNumber(int p, GeomVector v1, int a) {
        ArrayList<Integer> newCoordinates = new ArrayList<>(v1.getCoordinates());
        for (int i = 0; i < newCoordinates.size(); i++)
            newCoordinates.set(i, (newCoordinates.get(i) * a) % p);
        GeomVector result = new GeomVector(newCoordinates);
        System.out.println(a + " * " + v1 + " = " + result);
        return result;
    }

    public static GeomVector multiplyByNumber(GeomVector v1, int a) {
        ArrayList<Integer> newCoordinates = new ArrayList<>(v1.getCoordinates());
        for (int i = 0; i < newCoordinates.size(); i++)
            newCoordinates.set(i, newCoordinates.get(i) * a);
        return new GeomVector(newCoordinates);
    }

    public static GeomVector sumVectors(int p, GeomVector v1, GeomVector v2) {
        ArrayList<Integer> newCoordinates = new ArrayList<>(v1.getCoordinates());
        for (int i = 0; i < newCoordinates.size(); i++)
            newCoordinates.set(i, (v1.getCoordinates().get(i) + v2.getCoordinates().get(i)) % p);
        return new GeomVector(newCoordinates);
    }

    public static GeomVector sumVectors(GeomVector v1, GeomVector v2) {
        ArrayList<Integer> newCoordinates = new ArrayList<>(v1.getCoordinates());
        for (int i = 0; i < newCoordinates.size(); i++)
            newCoordinates.set(i, v1.getCoordinates().get(i) + v2.getCoordinates().get(i));
        return new GeomVector(newCoordinates);
    }

    public static GeomVector sumVectors(int p, ArrayList<GeomVector> vectors) {
        ArrayList<Integer> newCoordinates = new ArrayList<>(vectors.get(0).getCoordinates());
        for (int i = 1; i < vectors.size(); i++) {
            newCoordinates = GeomVector.sumVectors(p, new GeomVector(newCoordinates), vectors.get(i)).getCoordinates();
        }
        GeomVector result = new GeomVector(newCoordinates);
        System.out.println("?????????? ???????? ????????????????: " + result);
        return result;
    }

    public static GeomVector sumVectors(ArrayList<GeomVector> vectors) {
        ArrayList<Integer> newCoordinates = new ArrayList<>(vectors.get(0).getCoordinates());
        for (int i = 1; i < newCoordinates.size(); i++)
            newCoordinates = GeomVector.sumVectors(new GeomVector(newCoordinates), vectors.get(i)).getCoordinates();
        return new GeomVector(newCoordinates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeomVector that = (GeomVector) o;
        return Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        for (int i : coordinates)
            s.append(i).append(", ");
        s.delete(s.length()-2, s.length());
        s.append("}");
        return String.valueOf(s);
    }
}
