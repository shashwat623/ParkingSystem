package rapido.bike.paathshala.ParkingSystem;

public class ParkingOwner {
    private final String ownerName;



    public ParkingOwner(String ownerName) {
        this.ownerName = ownerName;
    }

    public String putFullSign(ParkingSystem parkingSystem) {
        if(parkingSystem.checkForfullSlot()){
            return "put Full Sign";
        }
        return "";
    }

}
