package rapido.bike.paathshala.ParkingSystem;

public class SecurityPerson {
    private final String securityPersonName;

    public SecurityPerson(String securityPersonName) {
        this.securityPersonName = securityPersonName;
    }


    public String redirectSecurityStaff(ParkingSystem parkingSystem) {
        if(parkingSystem.checkForfullSlot()){
            return "redirect my security staff";
        }
            return "";
    }

}
