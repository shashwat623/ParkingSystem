package rapido.bike.paathshala.ParkingSystem;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AttendantTest {
    void parkingLotFiller(ParkingLot parkingLot) {

        for (int index = 0; index < parkingLot.getTotalNumberOfSlots(); index++) {
            Vehicle vehicle = new Vehicle("1237" + index);
            parkingLot.carParking(vehicle);
        }

    }

    @Test
    public void shouldAllowParkingInFirstLotIfAllLotsAreVacant() {

        Attendant attendant = new Attendant(5, 5);

        String assignedLot = attendant.VehicleLotAllocatingToLot();

        assertEquals("Slot 1", assignedLot);
    }

    @Test
    public void shouldAllowParkingInSecondSlotIfOnlyFirstIsFilled() {

        Attendant attendant = new Attendant(5, 5);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));

        String assignedLot = attendant.VehicleLotAllocatingToLot();

        assertEquals("Slot 2", assignedLot);

    }

    @Test
    public void shouldNotAllowParkingIfAllSlotsAreFilled() {

        Attendant attendant = new Attendant(2, 2);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));
        parkingLotFiller(parkingLots.get(1));


        String unassignedLot = attendant.VehicleLotAllocatingToLot();

        assertEquals("Slot not available", unassignedLot);

    }

    @Test
    public void shouldAllowToUnParkIfVehicleIsPresentInAnyParkingLot() {

        Attendant attendant = new Attendant(2, 2);
        String vehicle = "12370";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));

        String slotToUnParkFrom = attendant.VehicleLotDeAllocatingFromLot(vehicle);

        assertEquals("You can un park from slot 1", slotToUnParkFrom);


    }

    @Test
    public void shouldNotAllowToUnParkIfVehicleIsPresentInAnyParkingLot() {

        Attendant attendant = new Attendant(2, 2);
        String vehicle = "12375";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));

        String slotToUnParkFrom = attendant.VehicleLotDeAllocatingFromLot(vehicle);

        assertEquals("Vehicle Not Present in any lot", slotToUnParkFrom);


    }

    @Test
    public void shouldParkCarInThirdLotIfOneCarEachIsParkedInFirstTwoLots() {
        Attendant attendant = new Attendant(3, 3);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        Vehicle vehicle1 = new Vehicle("1231");
        Vehicle vehicle2 = new Vehicle("1232");
        parkingLots.get(0).carParking(vehicle1);
        parkingLots.get(1).carParking(vehicle2);

        int allotedLot = attendant.parkCarEvenly(parkingLots);

        assertEquals(3, allotedLot);
    }


    @Test
    public void shouldParkInSecondLotWhenFirstAndThirdLotHaveOneCarEach() {
        Attendant attendant = new Attendant(3, 3);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        Vehicle vehicle1 = new Vehicle("1231");
        Vehicle vehicle2 = new Vehicle("1232");
        parkingLots.get(0).carParking(vehicle1);
        parkingLots.get(2).carParking(vehicle2);

        int allotedLot = attendant.parkCarEvenly(parkingLots);

        assertEquals(2, allotedLot);
    }
}