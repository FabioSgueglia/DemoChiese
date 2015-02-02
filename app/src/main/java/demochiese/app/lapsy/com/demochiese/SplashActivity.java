package demochiese.app.lapsy.com.demochiese;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    // Splash screen timer
    private static int SPLASH_TIME_OUT_SECONDS = 4;

    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();


    @Override
    protected void onStart() {
        super.onStart();

        Runnable task = new Runnable() {
            public void run() {
                //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences( SplashActivity.this);
                //String email = sp.getString(PREF_LOGIN_KEY, null);

                Intent i;
                i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        };
        worker.schedule(task, SPLASH_TIME_OUT_SECONDS, TimeUnit.SECONDS);
    }
}
