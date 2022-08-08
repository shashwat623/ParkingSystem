package rapido.bike.paathshala.ParkingSystem;

import org.mockito.Mockito;
import org.testng.annotations.Test;

public class ParkingOwnerTest {

    @Test
    public void shouldNotifyParkingOwnerWhenSlotIsFull() {
        int totalSlots = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots, 1);
        ParkingOwner parkingOwner = Mockito.spy(new ParkingOwner("ojasvi"));
        parkingLot.addLotObserver(parkingOwner);
        Vehicle vehicle = new Vehicle("DL AB 723");

        parkingLot.parkCar(vehicle);

        Mockito.verify(parkingOwner,Mockito.times(1)).notifyForFullLot();


    }

    @Test
    public void shouldNotifyOwnerWhenParkingAvailableAgain(){
        int totalSlots = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots, 1);
        ParkingOwner parkingOwner = Mockito.spy(new ParkingOwner("Shashwat"));
        parkingLot.addLotObserver(parkingOwner);
        Vehicle vehicle = new Vehicle("DL AB 123");
        parkingLot.parkCar(vehicle);

        parkingLot.carUnparking(vehicle);

        Mockito.verify(parkingOwner, Mockito.times(1)).notifyForAvailableSlot();

    }

}
