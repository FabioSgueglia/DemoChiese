package demochiese.app.lapsy.com.demochiese;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Item3Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item3, menu);
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
        this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }

    public void goToAudioGallery(View view){
        Intent i = new Intent(this, MultimediaActivity.class);
        i.putExtra("video", false);
        i.putExtra("audio", true);
        i.putExtra("photo", false);
        i.putExtra("mediaFileName", "altare_madonna_in_trono");
        startActivity(i);
    }

    public void goToVideoGallery(View view){
        Intent i = new Intent(this, MultimediaActivity.class);
        i.putExtra("video", true);
        i.putExtra("audio", false);
        i.putExtra("photo", false);
        i.putExtra("mediaFileName", "altare_madonna_in_trono");
        startActivity(i);
    }

    public void goToPhotoGallery(View view){
        Intent i = new Intent(this, MultimediaActivity.class);
        i.putExtra("video", false);
        i.putExtra("audio", false);
        i.putExtra("photo", true);
        i.putExtra("mediaFileName", "");
        startActivity(i);
    }

}
