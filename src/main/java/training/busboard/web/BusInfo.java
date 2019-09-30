package training.busboard.web;
import training.busboard.BusInformation;
import training.busboard.StopPointsWrapper;

import java.util.List;

public class BusInfo {
    private final String postcode;
    public List<BusInformation> busInformationList;
    public StopPointsWrapper stoppointswrapper;

    public BusInfo(String postcode, StopPointsWrapper stoppointswrapper) {
        this.postcode = postcode;
        this.stoppointswrapper = stoppointswrapper;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getDestination(){
        return busInformationList.get(0).getDestinationName();
    }

    public List<BusInformation> getBusInformationList(){
        return busInformationList;
    }

    public StopPointsWrapper getstoppointswrapper(){
        return stoppointswrapper;
    }
}
