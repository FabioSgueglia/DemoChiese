package demochiese.app.lapsy.com.demochiese.beacon;

import org.altbeacon.beacon.Beacon;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by francesco on 03/02/15.
 */
public class BeaconMapping {

    private String UUID;
    private String uuid;
    private Integer major;
    private Integer minor;
    private Integer beaconSelector = null;


    public BeaconMapping(String UUID) {
        this.UUID = new String(UUID);
    }

    public void setBeaconParameters(Beacon beacon) throws Exception {

        this.uuid = beacon.getId1().toString();
        this.major = beacon.getId2().toInt();
        this.minor = beacon.getId3().toInt();

        if(this.beaconValidation(this.uuid)) {
            //
        }
        else
            throw new Exception("Beacon di tipo sconosciuto per questa applicazione.");
    }

    private boolean beaconValidation(String uuid) {
        if(uuid.toUpperCase().equals(this.UUID.toUpperCase()))
            return true;
        else
            return false;
    }
}
