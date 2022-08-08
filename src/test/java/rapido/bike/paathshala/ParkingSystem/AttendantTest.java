package rapido.bike.paathshala.ParkingSystem;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AttendantTest {
    void parkingLotFiller(ParkingLot parkingLot) {

        for (int index = 0; index < parkingLot.getTotalNumberOfSlots(); index++) {
            Vehicle vehicle = new Vehicle("1237" + index);
            parkingLot.parkCar(vehicle);
        }

    }

    @Test
    public void shouldAllowParkingInFirstLotIfAllLotsAreVacant() {

        Attendant attendant = new Attendant(5, 5);

        int assignedLot = attendant.allocateLotToVehicle();

        assertEquals(1, assignedLot);
    }

    @Test
    public void shouldAllowParkingInSecondSlotIfOnlyFirstIsFilled() {

        Attendant attendant = new Attendant(5, 5);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));

        int assignedLot = attendant.allocateLotToVehicle();

        assertEquals(2, assignedLot);

    }

    @Test
    public void shouldNotAllowParkingIfAllSlotsAreFilled() {

        Attendant attendant = new Attendant(2, 2);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));
        parkingLotFiller(parkingLots.get(1));


        int unassignedLot = attendant.allocateLotToVehicle();

        assertEquals(-1, unassignedLot);

    }

    @Test
    public void shouldAllowToUnParkIfVehicleIsPresentInAnyParkingLot() {

        Attendant attendant = new Attendant(2, 2);
        String alreadyParkedVehicleNumber = "12370";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));

        int slotToUnParkFrom = attendant.deAllocateVehicleFromLot(alreadyParkedVehicleNumber);

        assertEquals(1, slotToUnParkFrom);


    }

    @Test
    public void shouldNotAllowToUnParkIfVehicleIsNotPresentInAnyParkingLot() {

        Attendant attendant = new Attendant(2, 2);
        String notAlreadyParkedVehicleNumber = "12375";
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        parkingLotFiller(parkingLots.get(0));

        int slotToUnParkFrom = attendant.deAllocateVehicleFromLot(notAlreadyParkedVehicleNumber);

        assertEquals(slotToUnParkFrom, slotToUnParkFrom);

    }

    @Test
    public void shouldParkCarInThirdLotIfOneCarEachIsParkedInFirstTwoLots() {
        Attendant attendant = new Attendant(3, 3);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        Vehicle vehicle1 = new Vehicle("1231");
        Vehicle vehicle2 = new Vehicle("1232");
        parkingLots.get(0).parkCar(vehicle1);
        parkingLots.get(1).parkCar(vehicle2);

        int allotedLot = attendant.allotLotEvenly();

        assertEquals(3, allotedLot);
    }


    @Test
    public void shouldParkInSecondLotWhenFirstAndThirdLotHaveOneCarEach() {
        Attendant attendant = new Attendant(3, 3);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        Vehicle vehicle1 = new Vehicle("1231");
        Vehicle vehicle2 = new Vehicle("1232");
        parkingLots.get(0).parkCar(vehicle1);
        parkingLots.get(2).parkCar(vehicle2);

        int allotedLot = attendant.allotLotEvenly();

        assertEquals(2, allotedLot);
    }

    @Test
    public void shouldParkInThirdLotIfThreeCarsParkedInEachLotAndOneCarUnparkedFromThirdLot(){
        Attendant attendant = new Attendant(3,3);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        for(int index = 0; index < parkingLots.size(); index++){
            parkingLotFiller(parkingLots.get(index));
        }
        parkingLots.get(2).carUnparking(new Vehicle("12370"));

        int allotedLot = attendant.allotLotEvenly();

        assertEquals(3, allotedLot);

    }

    @Test
    public void shouldParkInLinearManner(){
        Attendant attendant = new Attendant(3,3);
        ArrayList<ParkingLot> parkingLots = attendant.getParkingLots();
        for(int i = 0; i < 3; i++){
            attendant.fillingUpLotsSeriallyUntilFull(new Vehicle("123" + i));
        }
        Vehicle vehicle = new Vehicle("1238");

        int allotedLot = attendant.fillingUpLotsSeriallyUntilFull(vehicle);

        assertEquals(parkingLots.get(0).getNoOfCarsAlreadyParked(), parkingLots.get(0).getTotalNumberOfSlots());
        assertEquals(2, allotedLot);

    }
}


