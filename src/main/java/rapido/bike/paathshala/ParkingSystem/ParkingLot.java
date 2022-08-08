package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ParkingLot {

    private int totalNumberOfSlots;
    public int lotnumber;
    private int noOfCarsAlreadyParked = 0;
    private HashSet<Vehicle> parkedCarSet = new HashSet<Vehicle>();
    private List<ParkingLotObserver> lotObserverList=new ArrayList<>();
    public ParkingLot(int totalNumberOfSlots, int lotNumber) {
        this.totalNumberOfSlots = totalNumberOfSlots;
        this.lotnumber = lotNumber;
    }
    public int getNoOfCarsAlreadyParked() {
        return noOfCarsAlreadyParked;
    }
    public int getTotalNumberOfSlots() {
        return totalNumberOfSlots;
    }
    public HashSet<Vehicle> getParkedCarSet() {
        return parkedCarSet;
    }

    public String parkCar(Vehicle vehicle) {
        if (isSlotAvailable() && !parkedCarSet.contains(vehicle)) {
            parkedCarSet.add(vehicle);
            noOfCarsAlreadyParked += 1;
            if(isSlotFull()){
                notifyAllObserverForFullParking();
            }
            return "Car Parked!";
        } else {
            return "Cannot Park !";
        }
    }

    private void notifyAllObserverForFullParking() {
        for(ParkingLotObserver parkingLotObserver:lotObserverList){
            parkingLotObserver.notifyForFullLot();
        }
    }

    private void notifyAllObserverForParkingAvailableAgain() {
        for(ParkingLotObserver parkingLotObserver:lotObserverList){
            parkingLotObserver.notifyForAvailableSlot();
        }
    }

    public boolean isSlotAvailable() {
        return totalNumberOfSlots - noOfCarsAlreadyParked > 0;
    }

    public String carUnparking(Vehicle vehicle) {
        if (parkedCarSet.contains(vehicle)) {
            parkedCarSet.remove(vehicle);
            noOfCarsAlreadyParked -= 1;
            if (noOfCarsAlreadyParked == totalNumberOfSlots - 1){
                notifyAllObserverForParkingAvailableAgain();
            }
            return "Unparked";
        } else {
            return "Cannot unpark a vehicle which is not already parked";
        }


    }
    public boolean isSlotFull(){
        if(totalNumberOfSlots==noOfCarsAlreadyParked){
            return true;
        }
        else{
            return false;
        }
    }

    public void addLotObserver(ParkingLotObserver parkingLotObserver) {
        lotObserverList.add(parkingLotObserver);
    }
}
