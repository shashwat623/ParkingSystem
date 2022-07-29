package rapido.bike.paathshala.ParkingSystem;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class ParkingSystemTest {


    @Test
    public void shouldAllowParkingWhenParkingNotFull () {
        int totalSlots = 5;
        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);

       for(int i=0;i<3;i++){
           String result = parkingSystem.carParking();
       }
        String FinalResult=parkingSystem.carParking();

        assertEquals("Car Parked!", FinalResult);
    }

    @Test
    public void shouldNotAllowParkingWhenSlotIsFull () {
        int totalSlots = 6;
        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);

        for(int i=0;i<6;i++){
            String result = parkingSystem.carParking();
        }
        String FinalResult=parkingSystem.carParking();

        assertEquals("Cannot Park !", FinalResult);
    }

//    @Test
//    public void shouldNotAllowParkingForCar6 () {
//        int totalSlots = 6;
//        int numberOfCarsAlreadyParked = 6;
//        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
//
//        String result = parkingSystem.carParking(numberOfCarsAlreadyParked);
//
//        assertEquals("Cannot Park !", result);
//    }
}
