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
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gr.performance.playingwithbeaconsdemo.R;

public class BleScannerActivity extends AppCompatActivity {

    private static final String TAG = "BleScannerActivity";


    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;

    private ScanSettings mScanSettings;
    private ScanCallback mScanCallback;
    private List<ScanFilter> mScanFilterList;

    // view elements
    private RecyclerView mRecyclerView;
    private BeaconsViewAdapter mAdapter;

    //TODO: create a static variable to define the SCAN_PERIOD
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_scanner);

        // for the workshop we assume that
        // the user does not turn off the bluetooth
        // while the application is running
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        // TODO: Initializes Bluetooth adapter & BluetoothLeScanner

        // initialize the view elements
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new BeaconsViewAdapter(new ArrayList<String>());
        mRecyclerView.setAdapter(mAdapter);

        // TODO: initialize the ScanCallback with the adapter

        // TODO: initialize the ScanSettings with mode BALANCED & set the SCAN_PERIOD

    }

    /**
     * Triggered by b_start
     * @param view
     */
    public void onClickBleScan(View view){
        // TODO: start ble Scan
    }

    /**
     * Triggered by b_stop
     * @param view
     */
    public void onClickStop(View view){
        // TODO: stop ble Scan
    }

    @Override
    protected void onDestroy() {
        // TODO: stop ble Scan
        super.onDestroy();
    }
}
