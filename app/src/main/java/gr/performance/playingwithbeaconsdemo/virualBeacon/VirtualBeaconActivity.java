package gr.performance.playingwithbeaconsdemo.virualBeacon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.uriio.beacons.model.Beacon;
import com.uriio.beacons.model.EddystoneURL;

import java.util.regex.Matcher;

import gr.performance.playingwithbeaconsdemo.R;

/**
 * From that activity we can make the Android Device to advertise as a eddystone url beacon
 * library used: https://github.com/uriio/beacons-android
 * THERE IS NO SUPPORT FOR ALL ANDROID DEVICES
 */
public class VirtualBeaconActivity extends AppCompatActivity {

    private static final String TAG = "VirtualBeaconActivity";

    private EditText mUrlEditText;
    private Beacon mBeacon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_beacon);

        mUrlEditText = (EditText) findViewById(R.id.et_url);
        mBeacon = null;
    }

    /**
     * Enable url advertising
     * @param view
     */
    public void onClickShare(View view){
        // read the url
        String url = mUrlEditText.getText().toString();
        Log.d(TAG, "share button clicked - url: " + url);
        // test if the input is URL
        Matcher matcher = Patterns.WEB_URL.matcher(url);
        if(matcher.matches()){
            Log.d(TAG, url + ":\tvalid");
            mBeacon = new EddystoneURL(url);
            Boolean start = mBeacon.start();
            if(!start){
                Toast.makeText(this, "Cannot start Advertising", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Stop url advertising
     * @param view
     */
    public void onClickStop(View view){
        if(mBeacon != null){
            mBeacon.stop();
            mBeacon.delete();
        }
    }

    @Override
    protected void onDestroy() {
        if(mBeacon != null){
            mBeacon.stop();
        }
        super.onDestroy();
    }
}
