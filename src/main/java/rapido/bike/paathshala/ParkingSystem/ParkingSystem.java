package rapido.bike.paathshala.ParkingSystem;

import java.util.HashSet;

public class ParkingSystem {

    int totalNumberOfSlots;
    int noOfCarsAlreadyParked = 0;
    HashSet<Vehicle> ParkedCarSet = new HashSet<Vehicle>();

    public ParkingSystem(int totalNumberOfSlots) {
        this.totalNumberOfSlots = totalNumberOfSlots;
    }

    public String carParking(Vehicle vehicle) {
        if (totalNumberOfSlots - noOfCarsAlreadyParked > 0 && !ParkedCarSet.contains(vehicle)) {
            ParkedCarSet.add(vehicle);
            noOfCarsAlreadyParked += 1;
            return "Car Parked!";
        } else {
            return "Cannot Park !";
        }
    }

    public String carUnparking(Vehicle vehicle) {
        if (ParkedCarSet.contains(vehicle)) {
            ParkedCarSet.remove(vehicle);
            return "Unparked";
        } else {
            return "Cannot unpark a vehicle which is not already parked";
        }


    }


}
