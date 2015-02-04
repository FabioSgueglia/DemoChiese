package demochiese.app.lapsy.com.demochiese;

import android.app.Application;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;


import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import org.altbeacon.beacon.startup.RegionBootstrap;

/**
 * Created by francesco on 03/02/15.
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import demochiese.app.lapsy.com.demochiese.MainActivity;
import demochiese.app.lapsy.com.demochiese.beacon.BeaconMapping;
import demochiese.app.lapsy.com.demochiese.beacon.MonitoringActivity;
import demochiese.app.lapsy.com.demochiese.beacon.RangingActivity;

public class DemoChieseApplication extends Application implements BootstrapNotifier, RangeNotifier {

    private static final String TAG = ".demochiese.app.lapsy.com.demochiese.DemoChieseApplication";

    private BeaconManager mBeaconManager;
    private Region mAllBeaconsRegion;
    private MonitoringActivity mMonitoringActivity;
    private RangingActivity mRangingActivity;
    private BackgroundPowerSaver mBackgroundPowerSaver;

    private RegionBootstrap mRegionBootstrap;

    private static final Integer MAJOR = 257;
    private static final Integer MINOR_BEACON_1 = 1;
    private static final Integer MINOR_BEACON_2 = 6;
    private static final Integer MINOR_BEACON_3 = 8;
    private Integer beaconSelector = null;

    public DemoChieseApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "App started up");
        // wake up the app when any beacon is seen (you can specify specific id filers in the parameters below)
        /*Region region = new Region("com.example.myapp.boostrapRegion", null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);*/

        /*Parse.initialize(this, "EqyQsQiWM8utoEJOrYboKpRksVVYAWRhy0hRYPtZ", "WCjDbRqz6enzuL7H869GQwOjwUAEox43V2ZOvQQi");
        // Also in this method, specify a default Activity to handle push notifications
        //PushService.setDefaultPushCallback(this, MainActivity.class);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Successfully subscribed to the broadcast channel.");
                } else {
                    Log.e(TAG, "Failed to subscribe for push", e);
                }
            }
        });*/

        mAllBeaconsRegion = new Region("All beacons region", null, null, null);

        this.mBeaconManager = BeaconManager.getInstanceForApplication(this);
        this.mBackgroundPowerSaver = new BackgroundPowerSaver(this);
        this.mBeaconManager.setBackgroundBetweenScanPeriod(6000);
        this.mBeaconManager.setBackgroundScanPeriod(10000);
        this.mRegionBootstrap = new RegionBootstrap(this, mAllBeaconsRegion);
        this.mBeaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
    }

    @Override
    public void didDetermineStateForRegion(int arg0, Region arg1) {
        // Don't care
    }

    @Override
    public void didEnterRegion(Region arg0) {
        Log.d(TAG, "Got a didEnterRegion call");
        // This call to disable will make it so the activity below only gets launched the first time a beacon is seen (until the next time the app is launched)
        // if you want the Activity to launch every single time beacons come into view, remove this call.
        //mRegionBootstrap.disable();
        //Intent intent = new Intent(this, MainActivity.class);
        // IMPORTANT: in the AndroidManifest.xml definition of this activity, you must set android:launchMode="singleInstance" or you will get two instances
        // created when a user launches the activity manually and it gets launched from here.
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //this.startActivity(intent);

        try {
            Log.d(TAG, String.format("Entered region. Starting ranging -> %s", arg0.toString()));
            mBeaconManager.setRangeNotifier(this);
            mBeaconManager.startRangingBeaconsInRegion(mAllBeaconsRegion);

        } catch (RemoteException e) {
            Log.e(TAG, "Cannot start ranging");
        }
    }

    @Override
    public void didExitRegion(Region arg0) {
        // Don't care
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {

        Log.d(TAG, "didRangeBeaconsInRegion()");

        if(beacons.size() > 0) {

            int maxRSSI = Integer.MIN_VALUE;
            Beacon nearestBeacon = null;

            Iterator<Beacon> allBeacons = beacons.iterator();
            while(allBeacons.hasNext()) {
                try {
                    Beacon newBeacon = allBeacons.next();
                    BeaconMapping bm = new BeaconMapping();
                    bm.getBeaconParameters(newBeacon);

                    if(newBeacon.getRssi() > maxRSSI) {
                        maxRSSI = newBeacon.getRssi();
                        nearestBeacon = newBeacon;

                        Log.d(TAG, "Nearest beacon: " + nearestBeacon.toString() +
                                "\n RSSI: " + nearestBeacon.getRssi() + "." +
                                "\n Distance: " + nearestBeacon.getDistance() + ".");

                        Log.d(TAG, "Beacon selector: " + nearestBeacon.getId3());

                        this.beaconSelector = nearestBeacon.getId3().toInt();
                    }
                }
                catch (Exception e) {
                    Log.i(TAG, e.toString());
                }
            }

            // Nel primo if rimettere this.MINOR_BEACON_2 al posto di this.MINOR_BEACON_1
            if(this.beaconSelector.equals(this.MINOR_BEACON_1)) {
                Intent menuItem1Intent = new Intent(this, Item1Activity.class);
                menuItem1Intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(menuItem1Intent);
            }
            else if(this.beaconSelector.equals(this.MINOR_BEACON_3)) {

                Intent menuItem2Intent = new Intent(this, Item2Activity.class);
                menuItem2Intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(menuItem2Intent);
            }
        }
    }

    public void setMonitoringActivity(MonitoringActivity activity) {
        this.mMonitoringActivity = activity;
    }

    public void setRangingActivity(RangingActivity activity) {
        this.mRangingActivity = activity;
    }
}