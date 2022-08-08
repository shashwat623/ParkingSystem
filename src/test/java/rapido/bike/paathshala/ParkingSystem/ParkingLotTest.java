package rapido.bike.paathshala.ParkingSystem;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class ParkingLotTest {


    @Test
    public void shouldAllowParkingWhenParkingNotFull() {
        int totalSlots = 1;
        int lotNumber = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots, lotNumber);
        Vehicle vehicle = new Vehicle("DL AB 1237");

        String parkStatus = parkingLot.parkCar(vehicle);

        assertEquals("Car Parked!", parkStatus);
    }

    @Test
    public void shouldNotAllowParkingWhenLotIsFull() {
        int totalSlots = 1;
        int lotNumber = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots, lotNumber);
        Vehicle vehicle1 = new Vehicle("DL AB 1237");
        parkingLot.parkCar(vehicle1);
        Vehicle vehicle2 = new Vehicle("DL AB 1234");
        String parkStatus = parkingLot.parkCar(vehicle2);

        assertEquals("Cannot Park !", parkStatus);
    }

    @Test
    public void shouldAllowUnparkIfCarIsParked() {
        int totalSlots = 6;
        int lotNumber = 1;
        ParkingLot parkingLot = new ParkingLot(totalSlots, lotNumber);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");
        parkingLot.parkCar(vehicle);

        String unParkStatus = parkingLot.carUnparking(vehicle);

        assertEquals("Unparked", unParkStatus);
    }

    @Test
    public void shouldNotAllowUnparkIfCarIsNotAlreadyParked() {
        int totalSlots = 6;
        ParkingLot parkingLot = new ParkingLot(totalSlots, 1);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");

        String unParkStatus = parkingLot.carUnparking(vehicle);

        assertEquals("Cannot unpark a vehicle which is not already parked", unParkStatus);

    }

}






