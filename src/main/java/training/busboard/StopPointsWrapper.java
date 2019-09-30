package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointsWrapper {

    public ArrayList<StopInformation> stopPoints;

    public ArrayList<StopInformation> getStopPoints() {
        return stopPoints;
    }

}
