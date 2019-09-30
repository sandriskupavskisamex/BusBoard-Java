package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class resultPostCode {

    public Location result;

    public Location getResult() {
        return result;
    }
}
