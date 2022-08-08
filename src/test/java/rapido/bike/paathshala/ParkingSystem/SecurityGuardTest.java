package rapido.bike.paathshala.ParkingSystem;

import org.mockito.Mockito;
import org.testng.annotations.Test;

public class SecurityGuardTest {

    @Test
    public void shouldNotifySecurityPersonWhenSlotIsFull() {
        int totalSlots = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots, 1);
        SecurityPerson securityPerson= Mockito.spy(new SecurityPerson("aman"));
        parkingLot.addLotObserver(securityPerson);
        Vehicle vehicle = new Vehicle("DL AB 723");

        parkingLot.parkCar(vehicle);

        Mockito.verify(securityPerson,Mockito.times(1)).notifyForFullLot();
    }

}
