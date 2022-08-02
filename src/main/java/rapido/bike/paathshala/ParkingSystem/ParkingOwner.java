package rapido.bike.paathshala.ParkingSystem;

public class ParkingOwner implements ParkingLotObserver {
    private final String ownerName;
    boolean isNotify;


    public ParkingOwner(String ownerName) {
        this.ownerName = ownerName;
    }


    @Override
    public void notifyFullSlot() {
        isNotify=true;

    }
}
