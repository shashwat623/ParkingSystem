package rapido.bike.paathshala.ParkingSystem;

public class SecurityPerson implements ParkingLotObserver {
    private final String securityPersonName;
    public boolean isNotify;
    public SecurityPerson(String securityPersonName) {
        this.securityPersonName = securityPersonName;
    }


    @Override
    public void notifyFullSlot() {
         isNotify=true;
    }
}
