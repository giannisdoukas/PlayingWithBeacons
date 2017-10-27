package gr.performance.playingwithbeaconsdemo;

import com.uriio.beacons.Beacons;

/**
 * Created by Giannis.Doukas on 26/10/2017.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize the library which used to advertise url beacon
        Beacons.initialize(this);
    }
}
