package WebApplication.Implementation.Optimizations;

import java.lang.Math;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class KNN {

    private int k;

    public KNN(int k) {
        this.k = k;
    }

    public static double CalculateDistance(ArrayList<Integer> arr1, ArrayList<Integer> arr2){

        if(arr1.size() != arr2.size()){
            return -1;
        }

        double distance = 0.0;

        for(int i=0; i<arr1.size(); i++){
            distance += (arr1.get(i) + arr2.get(i))*(arr1.get(i) + arr2.get(i));
        }

        return sqrt(distance);
    }
}
