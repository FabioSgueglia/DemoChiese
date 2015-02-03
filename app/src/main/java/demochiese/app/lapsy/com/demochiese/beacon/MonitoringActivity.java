package demochiese.app.lapsy.com.demochiese.beacon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Region;

import demochiese.app.lapsy.com.demochiese.DemoChieseApplication;
import demochiese.app.lapsy.com.demochiese.R;

/**
 * Created by francesco on 03/02/15.
 */
public class MonitoringActivity extends Activity {
    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranging);

        this.verifyBluetoothLESupport();
    }

    private void verifyBluetoothLESupport() {

        try {
            if (!BeaconManager.getInstanceForApplication(this).checkAvailability()) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bluetooth not enabled");
                builder.setMessage("Please enable bluetooth in settings and restart this application.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                        System.exit(0);
                    }
                });
                builder.show();
            }
        } catch (RuntimeException e) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bluetooth LE not available");
            builder.setMessage("Sorry, this device does not support Bluetooth LE.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                    System.exit(0);
                }

            });
            builder.show();

        }

    }

    public void onRangingClicked(View view) {
        Intent myIntent = new Intent(this, RangingActivity.class);
        this.startActivity(myIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        // Tell the Application not to pass off ranging updates to this activity
        ((DemoChieseApplication)this.getApplication()).setMonitoringActivity(null);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Tell the Application to pass off ranging updates to this activity
        ((DemoChieseApplication)this.getApplication()).setMonitoringActivity(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void didEnterRegion(Region region) {
        Log.i(TAG, "I just saw a beacon named " + region.getUniqueId() + " for the first time!");
    }

    public void didExitRegion(Region region) {
        Log.i(TAG, "I no longer see a beacon named: " + region.getUniqueId());
    }

    public void didDetermineStateForRegion(int state, Region region) {
        Log.i(TAG, "I have just switched from seeing/not seeing beacons: " + state);
    }

}