package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusInformation {

    int lineId;
    int timeToStation;
    String destinationName;

//    int lineId;
//    int timeToStationSeconds;
//    String stationName;
//    String vehicleId;
//    String destinationName;
//
//    BusInformation(int lineId, int timeToStationSeconds, String stationName, String vehicleId, String destinationName) {
//
//        this.lineId = lineId;
//        this.timeToStationSeconds = timeToStationSeconds;
//        this.stationName = stationName;
//        this.vehicleId = vehicleId;
//        this.destinationName = destinationName;


    public int getLineId(){
        return lineId;
    }
    public int getTimeToStation(){
        return timeToStation;
    }

    public String getDestinationName(){
        return destinationName;
    }


}


//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }

