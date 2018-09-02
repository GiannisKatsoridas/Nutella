package WebApplication.Model.Helpers;

public class UsersDistances {

    private long userId;
    private double distance;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public UsersDistances(long userId, double distance) {
        this.userId = userId;
        this.distance = distance;
    }
}
