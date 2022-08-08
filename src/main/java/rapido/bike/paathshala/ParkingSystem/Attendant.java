package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;

public class Attendant {
    int sizeOfParkingLots;
    int numberOfParkingLots;

    Attendant(int sizeOfParkingLots, int numberOfParkingLots) {
        this.sizeOfParkingLots = sizeOfParkingLots;
        this.numberOfParkingLots = numberOfParkingLots;
        for (int index = 0; index < numberOfParkingLots; index++) {
            this.parkingLots.add(new ParkingLot(sizeOfParkingLots, index+1));
        }
    }

    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    private ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    int allocateLotToVehicle() {
        for(ParkingLot parkingLot: parkingLots){
            if(parkingLot.isSlotAvailable()){
                return parkingLot.lotnumber;
            }
        }
        return -1;
    }

    int deAllocateVehicleFromLot(String LicenseNumber) {
        Vehicle vehicle = new Vehicle(LicenseNumber);
        for (ParkingLot parkingLot: parkingLots) {
            if (parkingLot.getParkedCarSet().contains(vehicle)) {
                return parkingLot.lotnumber;
            }
        }
        return -1;
    }

    int allotLotEvenly() {
        int minNumberOfCars = Integer.MAX_VALUE;
        int lotIndexWithMinCars = -1;
        for (int i = 0; i < parkingLots.size(); i++) {
            int carsInLot = parkingLots.get(i).getNoOfCarsAlreadyParked();
            if (carsInLot < minNumberOfCars) {
                minNumberOfCars = carsInLot;
                lotIndexWithMinCars = i;
            }
        }
        return lotIndexWithMinCars + 1;
    }

    int fillingUpLotsSeriallyUntilFull(Vehicle vehicle){
        for(ParkingLot parkingLot: parkingLots){
            if(parkingLot.isSlotAvailable()){
                parkingLot.parkCar(vehicle);
                return parkingLot.lotnumber;
            }
        }
        return -1;
    }

}