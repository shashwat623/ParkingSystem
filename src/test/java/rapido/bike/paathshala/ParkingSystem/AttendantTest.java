package rapido.bike.paathshala.ParkingSystem;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AttendantTest {

    Attendant attendant;

    void parkingLotFiller(ParkingLot parkingLot) {

        for (int index = 0; index < parkingLot.getTotalNumberOfSlots(); index++) {
            Vehicle vehicle = new Vehicle("1237" + index);
            parkingLot.parkCar(vehicle);
        }

    }

    void parkingLotsFiller(List<ParkingLot> parkingLots) {

        for (ParkingLot parkingLot : parkingLots) {
            parkingLotFiller(parkingLot);
        }
    }

    @BeforeMethod
    public void setUp() {
         attendant = new Attendant(5, 5);

    }

    @Test
    public void shouldAllowParkingInFirstLotIfAllLotsAreVacant() {


        String assignedLot = attendant.allocateLotToVehicle();

        assertEquals("Lot 1", assignedLot);
    }

    @Test
    public void shouldAllowParkingInSecondSlotIfOnlyFirstIsFilled() {

        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));

        String assignedLot = attendant.allocateLotToVehicle();

        assertEquals("Lot 2", assignedLot);

    }

    @Test
    public void shouldNotAllowParkingIfAllSlotsAreFilled() {

        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotsFiller(parkingLots);

        String unassignedLot = attendant.allocateLotToVehicle();

        assertEquals("Lot not available", unassignedLot);

    }

    @Test
    public void shouldAllowToUnParkIfVehicleIsPresentInAnyParkingLot() {

        String alreadyParkedVehicleNumber = "12370";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));

        String slotToUnParkFrom = attendant.deAllocateVehicleFromLot(alreadyParkedVehicleNumber);

        assertEquals("You can un-park from lot 1", slotToUnParkFrom);


    }

    @Test
    public void shouldNotAllowToUnParkIfVehicleIsNotPresentInAnyParkingLot() {

        String notAlreadyParkedVehicleNumber = "12375";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));

        String slotToUnParkFrom = attendant.deAllocateVehicleFromLot(notAlreadyParkedVehicleNumber);

        assertEquals("Vehicle not Present in any lot", slotToUnParkFrom);


    }

    @Test
    public void shouldParkCarInThirdLotIfOneCarEachIsParkedInFirstTwoLots() {

        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        Vehicle vehicle1 = new Vehicle("1231");
        Vehicle vehicle2 = new Vehicle("1232");
        parkingLots.get(0).parkCar(vehicle1);
        parkingLots.get(1).parkCar(vehicle2);

        int allottedLot = attendant.parkCarEvenly();

        assertEquals(3, allottedLot);
    }


    @Test
    public void shouldParkInSecondLotWhenFirstAndThirdLotHaveOneCarEach() {

        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        Vehicle vehicle1 = new Vehicle("1231");
        Vehicle vehicle2 = new Vehicle("1232");
        parkingLots.get(0).parkCar(vehicle1);
        parkingLots.get(2).parkCar(vehicle2);

        int allottedLot = attendant.parkCarEvenly();

        assertEquals(2, allottedLot);
    }

    @Test
    public void shouldParkInThirdLotIfThreeCarsParkedInEachLotAndOneCarUnParkedFromThirdLot(){

        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        for (ParkingLot parkingLot : parkingLots) {
            parkingLotFiller(parkingLot);
        }
        parkingLots.get(2).carUnparking(new Vehicle("12370"));

        int allottedLot = attendant.parkCarEvenly();

        assertEquals(3, allottedLot);

    }

    @Test
    public void shouldProvideSameSlotNumberWhenFillLotUntilFullUsed() {
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        int firstLotProvided = attendant.getParkingLotsLinearly();
        parkingLots.get(firstLotProvided).parkCar(new Vehicle("1111"));

        int secondLotProvided = attendant.getParkingLotsLinearly();

        assertEquals(0, secondLotProvided);
    }

    @Test
    public void shouldProvideNextSlotNumberWhenFirstSlotIsFilled() {

        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        for(int index = 0; index < parkingLots.get(0).getTotalNumberOfSlots(); index++){
            int lotToPark = attendant.getParkingLotsLinearly();
            parkingLots.get(lotToPark).parkCar(new Vehicle("1234" + index));

        }

        int providedLot = attendant.getParkingLotsLinearly();
        parkingLots.get(providedLot).parkCar(new Vehicle("vehicle check"));

        assertFalse(parkingLots.get(0).isSlotAvailable());
        assertEquals(1, providedLot);

    }
}


