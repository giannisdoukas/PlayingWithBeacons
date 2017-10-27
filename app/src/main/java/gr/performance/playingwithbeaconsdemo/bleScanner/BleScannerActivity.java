package gr.performance.playingwithbeaconsdemo.bleScanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gr.performance.playingwithbeaconsdemo.R;

import static android.bluetooth.le.ScanSettings.SCAN_MODE_BALANCED;

public class BleScannerActivity extends AppCompatActivity {

    private static final String TAG = "BleScannerActivity";
    // Stops scanning after 5 seconds.
    private static final long SCAN_PERIOD = 5000;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
    private ScanSettings mScanSettings;
    private ScanCallback mScanCallback;
    private List<ScanFilter> mScanFilterList;
    // view elements
    private RecyclerView mRecyclerView;
    private BeaconsViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_scanner);

        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        // for the workshop we assume that
        // the user does not turn off the bluetooth
        // while the application is running
        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();


        // initialize the view elements
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new BeaconsViewAdapter(new ArrayList<String>());
        mRecyclerView.setAdapter(mAdapter);

        // initialize the pre-required for the scan
        mScanCallback = new MyScanCallback(mAdapter);
        mScanSettings = new ScanSettings.Builder()
                .setScanMode(SCAN_MODE_BALANCED)
                .setReportDelay(SCAN_PERIOD)
                .build();
    }

    /**
     * Triggered by b_start
     * @param view
     */
    public void onClickBleScan(View view){
        if(mBluetoothLeScanner != null){
            Log.d(TAG, "Start scaning for beacons...");
            mBluetoothLeScanner.startScan(null, mScanSettings, mScanCallback);
        }
    }

    /**
     * Triggered by b_stop
     * @param view
     */
    public void onClickStop(View view){
        if(mBluetoothLeScanner != null){
            mBluetoothLeScanner.stopScan(mScanCallback);
        }
    }

    @Override
    protected void onDestroy() {
        if(mBluetoothLeScanner != null){
            mBluetoothLeScanner.stopScan((ScanCallback) null);
        }
        super.onDestroy();
    }
}
