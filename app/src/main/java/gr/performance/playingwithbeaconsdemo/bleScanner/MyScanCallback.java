package gr.performance.playingwithbeaconsdemo.bleScanner;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Callback functions which are called when beacons are founded
 */
class MyScanCallback extends ScanCallback{

    private static final String TAG = "MyScanCallback";
    private BeaconsViewAdapter mAdapter; // need the visualize adapter to update the data

    public MyScanCallback(BeaconsViewAdapter adapter){
        mAdapter = adapter;
    }

    /**
     * Callback when a BLE advertisement has been found.
     *
     * @param callbackType Determines how this callback was triggered. Could be one of
     *            {@link ScanSettings#CALLBACK_TYPE_ALL_MATCHES},
     *            {@link ScanSettings#CALLBACK_TYPE_FIRST_MATCH} or
     *            {@link ScanSettings#CALLBACK_TYPE_MATCH_LOST}
     * @param result A Bluetooth LE scan result.
     */
    @Override
    public void onScanResult(int callbackType, ScanResult result) {
        super.onScanResult(callbackType, result);
        Log.d(TAG, "scan result: " + result);
        ArrayList<String> data = new ArrayList<>();

        // TODO: beacons in string format in the data arraylist to visualize the results

        mAdapter.update(data);
    }

    /**
     * Callback when batch results are delivered.
     *
     * @param results List of scan results that are previously scanned.
     */
    @Override
    public void onBatchScanResults(List<ScanResult> results) {
        super.onBatchScanResults(results);
        ArrayList<String> data = new ArrayList<>();

        // TODO: beacons in string format in the data arraylist to visualize the results

        mAdapter.update(data);
    }

    /**
     * Callback when scan could not be started.
     *
     * @param errorCode Error code (one of SCAN_FAILED_*) for scan failure.
     */
    @Override
    public void onScanFailed(int errorCode) {
        super.onScanFailed(errorCode);
        Log.d(TAG, "ERROR:" + errorCode);
    }

}