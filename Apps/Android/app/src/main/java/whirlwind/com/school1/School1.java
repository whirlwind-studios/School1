package whirlwind.com.school1;

import android.app.Application;
import android.support.v4.content.LocalBroadcastManager;

import whirlwind.com.school1.backend.Database;

public class School1 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Init components
        LocalBroadcastManager.getInstance(this);
        Database.getInstance().initialize(this);
    }
}
