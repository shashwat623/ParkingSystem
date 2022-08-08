package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;

public class Attendant {
    int sizeOfParkingLots;
    int numberOfParkingLots;

    Strategy strategy;

    public final static Strategy DEFAULT_STRATEGY = EvenDistributionStrategy.getInstance();

    public final static Strategy FILL_ONE_LOT_STRATEGY = FillOneLotStrategy.getInstance();
    public final static Strategy EVEN_DISTRIBUTION_STRATEGY = EvenDistributionStrategy.getInstance();



    private ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public Attendant(int sizeOfParkingLots, int numberOfParkingLots) {
        this.sizeOfParkingLots = sizeOfParkingLots;
        this.numberOfParkingLots = numberOfParkingLots;
        this.strategy =DEFAULT_STRATEGY;
        for(int index=0;index<numberOfParkingLots;index++)
        {
            this.parkingLots.add(new ParkingLot(5,5));
        }
    }

    public Attendant(int sizeOfParkingLots, int numberOfParkingLots, Strategy strategy)
    {
        this.sizeOfParkingLots = sizeOfParkingLots;
        this.numberOfParkingLots = numberOfParkingLots;
        this.strategy = strategy;
        for(int index=0;index<numberOfParkingLots;index++)
        {
            this.parkingLots.add(new ParkingLot(5,5));
        }
    }

    public ArrayList<ParkingLot> getAllParkingLots() {
        return parkingLots;
    }

    public String allocateLotToVehicle()
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

    public String deAllocateVehicleFromLot(String LicenseNumber)
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


    public int getParkingLot(){
        return strategy.getParkingLots(parkingLots);
    }

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
}