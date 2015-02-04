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
    private static final Integer MINOR_BEACON_1 = 1;
    private static final Integer MINOR_BEACON_2 = 6;
    private static final Integer MINOR_BEACON_3 = 8;
    private String uuid;
    private Integer major;
    private Integer minor;
    private Integer beaconSelector = null;


    public BeaconMapping() {
    }

    public void getBeaconParameters(Beacon beacon) throws Exception{

        this.uuid = beacon.getId1().toString();
        this.major = beacon.getId2().toInt();
        this.minor = beacon.getId3().toInt();

        if(this.beaconValidation(this.uuid)) {
            /*if(this.minor.equals(this.MINOR_BEACON_1)) {
                this.beaconSelector = this.MINOR_BEACON_1;
            }
            else if(this.minor.equals(this.MINOR_BEACON_2)) {
                this.beaconSelector = this.MINOR_BEACON_2;
            }*/
        }
        else
            throw new Exception("Beacon di tipo sconosciuto per questa applicazione.");

        //return beaconSelector;
    }

    private boolean beaconValidation(String uuid) {
        if(uuid.toUpperCase().equals(this.uuid.toUpperCase()))
            return true;
        else
            return false;
    }
}
