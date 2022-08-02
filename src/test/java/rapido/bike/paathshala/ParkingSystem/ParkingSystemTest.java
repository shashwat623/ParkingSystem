package rapido.bike.paathshala.ParkingSystem;

import org.junit.Assert;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class ParkingSystemTest {


    @Test
    public void shouldAllowParkingWhenParkingNotFull() {
        int totalSlots = 5;
        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);

        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = new Vehicle("DL AB 123" + i);
            String result = parkingSystem.carParking(vehicle);
        }
        Vehicle vehicle = new Vehicle("DL AB 1237");
        String FinalResult = parkingSystem.carParking(vehicle);

        assertEquals("Car Parked!", FinalResult);
    }

    @Test
    public void shouldNotAllowParkingWhenSlotIsFull() {
        int totalSlots = 6;
        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);

        for (int i = 0; i < 6; i++) {
            Vehicle vehicle = new Vehicle("DL AB 723" + i);
            String result = parkingSystem.carParking(vehicle);

        }
        Vehicle vehicle = new Vehicle("DL AB 1237");
        String FinalResult = parkingSystem.carParking(vehicle);

        assertEquals("Cannot Park !", FinalResult);
    }

    @Test
    public void shouldAllowUnparkIfCarIsParked() {
        int totalSlots = 6;
        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");

        parkingSystem.carParking(vehicle);
        String status = parkingSystem.carUnparking(vehicle);

        assertEquals("Unparked", status);
    }

    @Test
    public void shouldNotAllowUnparkIfCarIsNotAlreadyParked() {
        int totalSlots = 6;
        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        Vehicle vehicle = new Vehicle("UP 16 AB 6638");


        String status = parkingSystem.carUnparking(vehicle);

        assertEquals("Cannot unpark a vehicle which is not already parked", status);

    }

    @Test
    public void shouldNotifyParkingOwnerWhenSlotIsFull() {
        int totalSlots = 6;
        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        ParkingOwner parkingOwner=new ParkingOwner("ojasvi");
        SecurityPerson securityPerson=new SecurityPerson("aman");
        parkingSystem.addLotObserver(parkingOwner);
        parkingSystem.addLotObserver(securityPerson);

        for (int i = 0; i < 6; i++) {
            Vehicle vehicle = new Vehicle("DL AB 723" + i);
            String result = parkingSystem.carParking(vehicle);
        }

        assertEquals(true,parkingOwner.isNotify);

    }

    @Test
    public void shouldNotifySecurityPersonWhenSlotIsFull() {
        int totalSlots = 6;
        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        ParkingOwner parkingOwner=new ParkingOwner("ojasvi");
        SecurityPerson securityPerson=new SecurityPerson("aman");
        parkingSystem.addLotObserver(parkingOwner);
        parkingSystem.addLotObserver(securityPerson);

        for (int i = 0; i < 6; i++) {
            Vehicle vehicle = new Vehicle("DL AB 723" + i);
            String result = parkingSystem.carParking(vehicle);
        }

        assertEquals(true,securityPerson.isNotify);

    }
}

