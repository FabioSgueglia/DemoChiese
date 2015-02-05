package demochiese.app.lapsy.com.demochiese;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.VideoView;


public class MultimediaActivity extends ActionBarActivity {

    public boolean video, audio, photo;
    public String mediaFileName, path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        video = extras.getBoolean("video");
        audio = extras.getBoolean("audio");
        photo = extras.getBoolean("photo");

        if(video){
            mediaFileName = extras.getString("mediaFileName");
            setContentView(R.layout.activity_multimedia_video);
            VideoView videoView = (VideoView)findViewById(R.id.video);
            if(mediaFileName.equals("basilica_san_petronio"))
                path = "android.resource://" + getPackageName() + "/" + R.raw.san_petronio;
            else if(mediaFileName.equals("porta_magna"))
                path = "android.resource://" + getPackageName() + "/" + R.raw.san_petronio;
            else if(mediaFileName.equals("altare_maggiore"))
                path = "android.resource://" + getPackageName() + "/" + R.raw.san_petronio;
            else if(mediaFileName.equals("altare_madonna_in_trono"))
                path = "android.resource://" + getPackageName() + "/" + R.raw.san_petronio;
            videoView.setVideoURI(Uri.parse(path));
            videoView.start();
        }

        if(audio){
            mediaFileName = extras.getString("mediaFileName");
            setContentView(R.layout.activity_multimedia_audio);
            VideoView videoView = (VideoView)findViewById(R.id.video);
            if(mediaFileName.equals("basilica_san_petronio"))
                path = "android.resource://" + getPackageName() + "/" + R.raw.la_porta_magna_della_nativita;
            else if(mediaFileName.equals("porta_magna"))
                path = "android.resource://" + getPackageName() + "/" + R.raw.la_porta_magna_della_nativita;
            else if(mediaFileName.equals("altare_maggiore"))
                path = "android.resource://" + getPackageName() + "/" + R.raw.la_porta_magna_della_nativita;
            else if(mediaFileName.equals("altare_madonna_in_trono"))
                path = "android.resource://" + getPackageName() + "/" + R.raw.la_porta_magna_della_nativita;
            videoView.setVideoURI(Uri.parse(path));
            videoView.start();
        }

        if(photo){
            /*mediaFileName = extras.getString("mediaFileName");
            setContentView(R.layout.activity_multimedia_photo);
            ImageSwitcher imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
            imageSwitcher.setImageResource(R.drawable.ic_launcher);
            imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    ImageView myView = new ImageView(getApplicationContext());
                    return myView;
                }
            });*/

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multimedia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
