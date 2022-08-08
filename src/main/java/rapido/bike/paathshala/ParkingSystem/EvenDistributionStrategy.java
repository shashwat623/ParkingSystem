package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;

public class EvenDistributionStrategy implements Strategy {

    private static final EvenDistributionStrategy instance = new EvenDistributionStrategy();

    private EvenDistributionStrategy(){}

    @Override
    public int getParkingLots(ArrayList<ParkingLot> parkingLots) {
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

    public static Strategy getInstance(){
        return instance;
    }

}
