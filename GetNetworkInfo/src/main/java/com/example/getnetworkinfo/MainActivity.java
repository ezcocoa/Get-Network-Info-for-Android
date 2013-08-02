package com.example.getnetworkinfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.getnetworkinfo.util.NetworkUtils;

public class MainActivity extends Activity {

    private boolean _isRunningThread = false;

    private TextView _ipAddressTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _ipAddressTv = (TextView)findViewById(R.id.ipAddressTv);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check Network whether Wifi is connected or else.
        // If Network is connected, it gets network information.
        //
        if (_isRunningThread == false) {
            _isRunningThread = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (_isRunningThread == false) return;

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //
                    // Update UI
                    //
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            _ipAddressTv.setText("IP Address : " + NetworkUtils.getIPAddress(true));
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        _isRunningThread = false;
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Not used.
        return false;
    }

    /**
     * It calls when you click any button.
     * @param v
     */
    public void onAction(View v) {
        switch (v.getId()) {
            case R.id.setupBtn:
                return;
            case R.id.sharedBtn:
                return;
        }
    }
}
