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

        ArrayList<ParkingLot> parkingLots = attendant.getAllParkingLots();
        parkingLotFiller(parkingLots.get(0));

        String assignedLot = attendant.allocateLotToVehicle();

        assertEquals("Lot 2", assignedLot);

    }

    @Test
    public void shouldNotAllowParkingIfAllSlotsAreFilled() {

        ArrayList<ParkingLot> parkingLots = attendant.getAllParkingLots();
        parkingLotsFiller(parkingLots);

        String unassignedLot = attendant.allocateLotToVehicle();

        assertEquals("Lot not available", unassignedLot);

    }

    @Test
    public void shouldAllowToUnParkIfVehicleIsPresentInAnyParkingLot() {

        String alreadyParkedVehicleNumber = "12370";
        ArrayList<ParkingLot> parkingLots = attendant.getAllParkingLots();
        parkingLotFiller(parkingLots.get(0));

        String slotToUnParkFrom = attendant.deAllocateVehicleFromLot(alreadyParkedVehicleNumber);

        assertEquals("You can un-park from lot 1", slotToUnParkFrom);


    }

    @Test
    public void shouldNotAllowToUnParkIfVehicleIsNotPresentInAnyParkingLot() {

        String notAlreadyParkedVehicleNumber = "12375";
        ArrayList<ParkingLot> parkingLots = attendant.getAllParkingLots();
        parkingLotFiller(parkingLots.get(0));

        String slotToUnParkFrom = attendant.deAllocateVehicleFromLot(notAlreadyParkedVehicleNumber);

        assertEquals("Vehicle not Present in any lot", slotToUnParkFrom);


    }

    @Test
    public void shouldParkCarInThirdLotIfOneCarEachIsParkedInFirstTwoLotsEvenlyParkingStrategyUsed() {

        ArrayList<ParkingLot> parkingLots = attendant.getAllParkingLots();
        Vehicle vehicle1 = new Vehicle("1231");
        Vehicle vehicle2 = new Vehicle("1232");
        parkingLots.get(0).parkCar(vehicle1);
        parkingLots.get(1).parkCar(vehicle2);
        attendant.setStrategy(Attendant.EVEN_DISTRIBUTION_STRATEGY);

        int allottedLot = attendant.getParkingLot();

        assertEquals(3, allottedLot);
    }


    @Test
    public void shouldParkInSecondLotWhenFirstAndThirdLotHaveOneCarEachEvenlyParkingStrategyUsed() {

        ArrayList<ParkingLot> parkingLots = attendant.getAllParkingLots();
        Vehicle vehicle1 = new Vehicle("1231");
        Vehicle vehicle2 = new Vehicle("1232");
        parkingLots.get(0).parkCar(vehicle1);
        parkingLots.get(2).parkCar(vehicle2);
        attendant.setStrategy(Attendant.EVEN_DISTRIBUTION_STRATEGY);

        int allottedLot = attendant.getParkingLot();

        assertEquals(2, allottedLot);
    }

    @Test
    public void shouldParkInThirdLotIfThreeCarsParkedInEachLotAndOneCarUnParkedFromThirdLotWhenEvenlyParkingStrategyUsed(){

        ArrayList<ParkingLot> parkingLots = attendant.getAllParkingLots();
        for (ParkingLot parkingLot : parkingLots) {
            parkingLotFiller(parkingLot);
        }
        parkingLots.get(2).carUnparking(new Vehicle("12370"));
        attendant.setStrategy(Attendant.EVEN_DISTRIBUTION_STRATEGY);

        int allottedLot = attendant.getParkingLot();

        assertEquals(3, allottedLot);

    }

    @Test
    public void shouldProvideSameSlotNumberWhenFillLotUntilFullStrategyUsed() {
        ArrayList<ParkingLot> parkingLots = attendant.getAllParkingLots();
        int firstLotProvided = attendant.getParkingLot();
        parkingLots.get(firstLotProvided).parkCar(new Vehicle("1111"));
        attendant.setStrategy(Attendant.FILL_ONE_LOT_STRATEGY);


        int secondLotProvided = attendant.getParkingLot();

        assertEquals(0, secondLotProvided);
    }

    @Test
    public void shouldProvideNextSlotNumberWhenFirstSlotIsFilledCompletelyInFillLotUntilFullStrategy(){
        ArrayList<ParkingLot> parkingLots = attendant.getAllParkingLots();
        attendant.setStrategy(Attendant.FILL_ONE_LOT_STRATEGY);
        for(int index = 0; index < parkingLots.get(0).getTotalNumberOfSlots(); index++){
            int lotToPark = attendant.getParkingLot();
            parkingLots.get(lotToPark).parkCar(new Vehicle("1234" + index));

        }

        int providedLot = attendant.getParkingLot();
        parkingLots.get(providedLot).parkCar(new Vehicle("vehicle check"));

        System.out.println(providedLot);
        assertFalse(parkingLots.get(0).isSlotAvailable());
        assertEquals(1, providedLot);

    }
}


