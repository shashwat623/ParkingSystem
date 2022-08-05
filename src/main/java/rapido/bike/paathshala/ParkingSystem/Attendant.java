package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;

public class Attendant {
    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    private ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    int sizeOfParkingLots;
    int numberOfParkingLots;
    Attendant(int sizeOfParkingLots,int numberOfParkingLots)
    {
        this.sizeOfParkingLots = sizeOfParkingLots;
        this.numberOfParkingLots = numberOfParkingLots;
        for(int index=0;index<numberOfParkingLots;index++)
        {
            this.parkingLots.add(new ParkingLot(sizeOfParkingLots));
        }
    }
    String VehicleLotAllocatingToLot()
    {
        for(int index=0;index<this.parkingLots.size();index++)
        {
            if(this.parkingLots.get(index).isSlotAvailable())
            {
                return "Slot "+(index+1);
            }
        }
        return "Slot not available";
    }

    String VehicleLotDeAllocatingFromLot(String LicenseNumber)
    {
        Vehicle vehicle = new Vehicle(LicenseNumber);
        for(int index=0;index<parkingLots.size();index++)
        {
            if(parkingLots.get(index).getParkedCarSet().contains(vehicle))
            {
                return "You can un park from slot "+(index+1);
            }
        }
        return "Vehicle Not Present in any lot";
    }

    int parkCarEvenly(ArrayList<ParkingLot> parkingLots){
        this.parkingLots = parkingLots;
        int minNumberOfCars = Integer.MAX_VALUE;
        int lotOIndexWithMinCars = -1;
        for(int i = 0; i < parkingLots.size(); i++){
            int carsInLot = parkingLots.get(i).getNoOfCarsAlreadyParked();
            if(carsInLot < minNumberOfCars){
                minNumberOfCars = carsInLot;
                lotOIndexWithMinCars = i;
            }
        }
        return  lotOIndexWithMinCars+1;
    }

}