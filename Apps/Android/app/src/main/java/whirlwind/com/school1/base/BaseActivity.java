package whirlwind.com.school1.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isValid())
                Popup.showIdles(BaseActivity.this);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(null).registerReceiver(receiver, new IntentFilter(Popup.ACTION_FILTER));
        Popup.showIdles(this);
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(null).unregisterReceiver(receiver);
        Popup.dismissActives();
        super.onPause();
    }

    public boolean isValid() {
        return !isFinishing() && !isDestroyed();
    }
}