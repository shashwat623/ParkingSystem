package rapido.bike.paathshala.ParkingSystem;

public class SecurityPerson implements ParkingLotObserver {
    private final String securityPersonName;
    public boolean isLotFull;

    public SecurityPerson(String securityPersonName) {
        this.securityPersonName = securityPersonName;
    }


    @Override
    public void notifyForFullLot() {
        isLotFull =true;
    }

    public void notifyForAvailableSlot(){
        isLotFull = false;
    }
}


