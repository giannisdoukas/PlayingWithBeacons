package gr.performance.playingwithbeaconsdemo;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.performance.playingwithbeaconsdemo.bleScanner.BleScannerActivity;
import gr.performance.playingwithbeaconsdemo.virualBeacon.VirtualBeaconActivity;

/**
 * MainActivity
 *  button to switch between scan and advertise activities
 */
public class MainActivity extends AppCompatActivity {

    // codes to use when requesting permissions
    private static final int PERMISSIONS_REQUEST_CODE_COARSE_LOCATION=10;
    private static final int REQUEST_ENABLE_BT = 11;


    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // Ensures Bluetooth is available on the device and it is enabled. If not,
        // displays a dialog requesting user permission to enable Bluetooth.
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // check if the app has the required permissions
        // and if not ask for them
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_REQUEST_CODE_COARSE_LOCATION);
        }
    }

    /**
     * Function to handle the onclick button event called by the b_virtual_beacon button
     * @param view
     */
    public void urlBeaconButton(View view){
        // Create a new intent to start the VirtualBeacon Activity
        Intent intent = new Intent(this, VirtualBeaconActivity.class);
        startActivity(intent);
    }

    /**
     * Function to handle the onclick button event called by the b_ble_scan button
     * @param view
     */
    public void scanBleButton(View view){
        // Create a new intent to start the BleScanner Activity
        Intent intent = new Intent(this, BleScannerActivity.class);
        startActivity(intent);
    }
}
