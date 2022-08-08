package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;

public class Attendant {
    int sizeOfParkingLots;
    int numberOfParkingLots;

    private ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    Attendant(int sizeOfParkingLots,int numberOfParkingLots)
    {
        this.sizeOfParkingLots = sizeOfParkingLots;
        this.numberOfParkingLots = numberOfParkingLots;
        for(int index=0;index<numberOfParkingLots;index++)
        {
            this.parkingLots.add(new ParkingLot(sizeOfParkingLots));
        }
    }

    public void setParkingLots(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    String allocateLotToVehicle()
    {
        for(int index=0;index<this.parkingLots.size();index++)
        {
            if(this.parkingLots.get(index).isSlotAvailable())
            {
                return "Lot "+(index+1);
            }
        }
        return "Lot not available";
    }

    String deAllocateVehicleFromLot(String LicenseNumber)
    {
        Vehicle vehicle = new Vehicle(LicenseNumber);
        for(int index=0; index<parkingLots.size(); index++)
        {
            if(parkingLots.get(index).getParkedCarSet().contains(vehicle))
            {
                return "You can un-park from lot "+(index+1);
            }
        }
        return "Vehicle not Present in any lot";
    }

    int parkCarEvenly(){
        int minNumberOfCars = Integer.MAX_VALUE;
        int lotIndexWithMinCars = -1;
        for(int i = 0; i < parkingLots.size(); i++){
            int carsInLot = parkingLots.get(i).getNoOfCarsAlreadyParked();
            if(carsInLot < minNumberOfCars){
                minNumberOfCars = carsInLot;
                lotIndexWithMinCars = i;
            }
        }
        return  lotIndexWithMinCars+1;
    }

    public int getParkingLotsLinearly() {
        for(int index =0;index<parkingLots.size();index++){
            if(parkingLots.get(index).isSlotAvailable()){
                return index;
            }
        }
        return -1;
    }
}