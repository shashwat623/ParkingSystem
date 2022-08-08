package rapido.bike.paathshala.ParkingSystem;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class ParkingLotTest {


    @Test
    public void shouldAllowParkingWhenParkingNotFull() {
        int totalSlots = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots);
        Vehicle vehicle = new Vehicle("DL AB 1237");

        String parkStatus = parkingLot.parkCar(vehicle);

        assertEquals("Car Parked!", parkStatus);
    }

    @Test
    public void shouldNotAllowParkingWhenLotIsFull() {
        int totalSlots = 1;

        ParkingLot parkingLot = new ParkingLot(totalSlots);
        Vehicle vehicle1 = new Vehicle("DL AB 1237");
        parkingLot.parkCar(vehicle1);
        Vehicle vehicle2 = new Vehicle("DL AB 1234");
        String parkStatus = parkingLot.parkCar(vehicle2);

        assertEquals("Cannot Park !", parkStatus);
    }

    @Test
    public void shouldAllowUnparkIfCarIsParked() {
        int totalSlots = 6;
        ParkingLot parkingLot = new ParkingLot(totalSlots);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");
        parkingLot.parkCar(vehicle);

        String unParkStatus = parkingLot.carUnparking(vehicle);

        assertEquals("Unparked", unParkStatus);
    }

    @Test
    public void shouldNotAllowUnparkIfCarIsNotAlreadyParked() {
        int totalSlots = 6;
        ParkingLot parkingLot = new ParkingLot(totalSlots);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");

        String unParkStatus = parkingLot.carUnparking(vehicle);

        assertEquals("Cannot unpark a vehicle which is not already parked", unParkStatus);

    }

    @Test
    public void shouldNotifyParkingOwnerWhenSlotIsFull() {
        int totalSlots = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots);
        ParkingOwner parkingOwner = Mockito.spy(new ParkingOwner("ojasvi"));
        parkingLot.addLotObserver(parkingOwner);
        Vehicle vehicle = new Vehicle("DL AB 723");

        parkingLot.parkCar(vehicle);

        Mockito.verify(parkingOwner,Mockito.times(1)).notifyForFullLot();


    }

    @Test
    public void shouldNotifySecurityPersonWhenSlotIsFull() {
        int totalSlots = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots);
        SecurityPerson securityPerson= Mockito.spy(new SecurityPerson("aman"));
        parkingLot.addLotObserver(securityPerson);
        Vehicle vehicle = new Vehicle("DL AB 723");

        parkingLot.parkCar(vehicle);

        Mockito.verify(securityPerson,Mockito.times(1)).notifyForFullLot();
    }

    @Test
    public void shouldNotifyOwnerWhenParkingAvailableAgain(){
        int totalSlots = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots);
        ParkingOwner parkingOwner = Mockito.spy(new ParkingOwner("Shashwat"));
        parkingLot.addLotObserver(parkingOwner);
        Vehicle vehicle = new Vehicle("DL AB 123");
        parkingLot.parkCar(vehicle);

        parkingLot.carUnparking(vehicle);

        Mockito.verify(parkingOwner, Mockito.times(1)).notifyForAvailableSlot();

    }
}






