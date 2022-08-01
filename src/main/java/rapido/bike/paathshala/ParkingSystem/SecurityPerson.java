package rapido.bike.paathshala.ParkingSystem;

public class SecurityPerson {
    private final String securityPersonName;

    public SecurityPerson(String securityPersonName) {
        this.securityPersonName = securityPersonName;
    }


    public String checkForfullSlot(ParkingSystem parkingSystem) {
        return parkingSystem.checkForfullSlot();
    }
}
