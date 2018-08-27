package WebApplication.Implementation.Optimizations;

import WebApplication.Model.Helpers.UserApplications;
import WebApplication.Model.Helpers.UsersDistancesJobs;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.sqrt;

public class KNN {

    private int k = 5;

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


    public static ArrayList<Long> findKNearestNeighbors(long userId){

        double distance;
        ArrayList<UsersDistancesJobs> distances = new ArrayList<UsersDistancesJobs>();
        ArrayList<Long> result = new ArrayList<Long>();

        for(Long l: UserApplications.values.keySet()){
            if(l != userId) {
                distance = CalculateDistance(UserApplications.values.get(l), UserApplications.values.get(userId));
                distances.add(new UsersDistancesJobs(l, distance));
            }
        }

        Comparator<UsersDistancesJobs> comp = new Comparator<UsersDistancesJobs>() {
            public int compare(UsersDistancesJobs o1, UsersDistancesJobs o2) {
                if(o1.getDistance() > o2.getDistance()) return 1;
                if(o1.getDistance() < o2.getDistance()) return -1;
                return 0;
            }
        };

        Collections.sort(distances, comp);

        for(int i=0; i<5; i++)
            if(distances.get(i).getDistance()!= 0.0)
                result.add(distances.get(i).getUserId());

        return result;
    }
}
