/*Create the BroadcastReceiver and thereby override the onReceive() method. Use the BatteryManager
intent and get the battery percentage and update the UI with the battery percentage.*/

package com.acadgild.soniyasinghal.androidbatterypercentage;
//Import
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
//Main Class
public class MainActivity extends AppCompatActivity {
    //declaration
    private TextView mBatteryLevelText;
    private ProgressBar mBatteryLevelProgress;
    private BroadcastReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBatteryLevelText = (TextView) findViewById(R.id.textView);
        mBatteryLevelProgress = (ProgressBar) findViewById(R.id.progressBar);
        mReceiver = new BatteryBroadcastReceiver();
    }
    @Override
    protected void onStart() {
        registerReceiver(mReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();
    }
    @Override
    protected void onStop() {
        unregisterReceiver(mReceiver);
        super.onStop();
    }
    //Inner class
    public class BatteryBroadcastReceiver extends BroadcastReceiver {
        //overriding the onReceive() method
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            mBatteryLevelText.setText(getString(R.string.Android_Battery_Percentage) + " " + level+ "%");
            mBatteryLevelProgress.setProgress(level);
        }
    }
}
