package demochiese.app.lapsy.com.demochiese.beacon;

import org.altbeacon.beacon.Beacon;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by francesco on 03/02/15.
 */
public class BeaconMapping {

    private static final String UUID = "01122334-4556-6778-899A-ABBCCDDEEFF0";
    private static final Integer MAJOR = 257;
    private static  final Integer MINOR_BEACON_1 = 6;
    private static  final Integer MINOR_BEACON_2 = 8;
    private String uuid;
    private Integer major;
    private Integer minor;
    private Integer selector = null;


    public BeaconMapping() {
    }

    public HashMap getBeaconParameters(Beacon beacon) throws Exception{

        this.uuid = beacon.getId1().toString();
        this.major = beacon.getId2().toInt();
        this.minor = beacon.getId3().toInt();

        HashMap beaconParameters = new HashMap();
        beaconParameters.put("UUID", this.uuid);
        beaconParameters.put("MAJOR", this.major);
        beaconParameters.put("MINOR", this.minor);

        if(this.beaconValidation() && (this.major.equals(this.MAJOR))) {
            if(this.minor.equals(this.MINOR_BEACON_1)) {
                this.selector = 1;
                beaconParameters.put("ITEM_SELECTOR", this.selector);
            }
            else if(this.minor.equals(this.MINOR_BEACON_2)) {
                this.selector = 2;
                beaconParameters.put("ITEM_SELECTOR", this.selector);
            }
        }
        else
            throw new Exception("Beacon di tipo sconosciuto per questa applicazione.");

        return beaconParameters;
    }

    private boolean beaconValidation() {
        if(this.UUID.equals(this.uuid))
            return true;
        else
            return false;
    }
}
