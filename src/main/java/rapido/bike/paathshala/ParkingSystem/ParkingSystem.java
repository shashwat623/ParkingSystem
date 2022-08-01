package rapido.bike.paathshala.ParkingSystem;

import java.util.HashSet;

public class ParkingSystem {

    private int totalNumberOfSlots;
    private int noOfCarsAlreadyParked = 0;
    private HashSet<Vehicle> ParkedCarSet = new HashSet<Vehicle>();

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
    public String checkForfullSlot(){
        if(totalNumberOfSlots==noOfCarsAlreadyParked){
            return "Slot is Full";
        }
        else{
            return "Slot is not Full";
        }
    }


}
