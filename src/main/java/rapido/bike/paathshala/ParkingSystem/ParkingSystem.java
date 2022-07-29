package rapido.bike.paathshala.ParkingSystem;

public class ParkingSystem {

    int totalNumberOfSlots;
    int noOfCarsAlreadyParked = 0;

    public ParkingSystem(int totalNumberOfSlots) {
        this.totalNumberOfSlots = totalNumberOfSlots;
    }

    public String carParking() {
       if(totalNumberOfSlots-noOfCarsAlreadyParked>0){
           noOfCarsAlreadyParked+=1;
           return "Car Parked!";
       }
       else{
           return "Cannot Park !";
       }
    }
}
