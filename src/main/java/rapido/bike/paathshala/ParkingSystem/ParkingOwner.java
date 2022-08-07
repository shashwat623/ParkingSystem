package rapido.bike.paathshala.ParkingSystem;

public class ParkingOwner implements ParkingLotObserver {
    private final String ownerName;
    boolean isLotFull;

    public ParkingOwner(String ownerName) {
        this.ownerName = ownerName;
    }


    @Override
    public void notifyForFullLot() {
        isLotFull =true;

    }

    @Override
    public void notifyForAvailableSlot(){
        isLotFull = false;
    }
}
