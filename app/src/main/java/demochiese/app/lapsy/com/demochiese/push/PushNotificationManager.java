package demochiese.app.lapsy.com.demochiese.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import demochiese.app.lapsy.com.demochiese.MainActivity;

/**
 * Created by francesco on 05/02/15.
 */
public class PushNotificationManager extends ParsePushBroadcastReceiver {

    private static final String LOG_TAG = "PushNotificationManager";
    @Override
    public void onPushOpen(Context context, Intent intent) {
        Log.d(LOG_TAG, "onPushOpen");
        Intent i = new Intent(context, MainActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
