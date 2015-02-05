package demochiese.app.lapsy.com.demochiese.beacon;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.WindowManager;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

import demochiese.app.lapsy.com.demochiese.DemoChieseApplication;
import demochiese.app.lapsy.com.demochiese.R;

/**
 * Created by francesco on 03/02/15.
 */
public class RangingActivity extends Activity {
    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranging);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        // Tell the Application not to pass off ranging updates to this activity
        ((DemoChieseApplication)this.getApplication()).setRangingActivity(null);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Tell the Application to pass off ranging updates to this activity
        ((DemoChieseApplication)this.getApplication()).setRangingActivity(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
        //
    }
}