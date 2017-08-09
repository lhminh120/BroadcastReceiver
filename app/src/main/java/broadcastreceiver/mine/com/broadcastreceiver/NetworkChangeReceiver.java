package broadcastreceiver.mine.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by MyPC on 28/07/2017.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(MainActivity.ACTION_NHẬN_DIỆN)){
            String tittle = intent.getStringExtra(MainActivity.ACTION_NHẬN_DIỆN);
            Toast.makeText(context, tittle, Toast.LENGTH_LONG).show();
        }
       // Toast.makeText(context, "Wifi is turned ON/OFF", Toast.LENGTH_LONG).show();
    }
}
