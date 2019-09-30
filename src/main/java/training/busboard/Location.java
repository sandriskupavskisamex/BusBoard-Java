package training.busboard;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    public double longitude;
    public double latitude;

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

}
