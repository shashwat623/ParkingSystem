package rapido.bike.paathshala.ParkingSystem;

import java.util.ArrayList;

public class FillOneLotStrategy implements Strategy{

        private static final FillOneLotStrategy instance = new FillOneLotStrategy();

        private FillOneLotStrategy(){}

        @Override
        public int getParkingLots(ArrayList<ParkingLot> parkingLots) {
            for(int index =0;index<parkingLots.size();index++){
                if(parkingLots.get(index).isSlotAvailable()){
                    return index;
                }
            }
            return -1;
        }

        public static Strategy getInstance(){
            return instance;
        }

}
