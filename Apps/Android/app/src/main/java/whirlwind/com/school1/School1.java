package whirlwind.com.school1;

import android.app.Application;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import whirlwind.com.school1.backend.Database;

public class School1 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Init components
        new AsyncTask<Integer, Object, Object>() {
            @Override
            protected Object doInBackground(Integer... params) {
                LocalBroadcastManager.getInstance(School1.this);
                Database.getInstance().initialize(School1.this);
                return null;
            }
        }.execute(0);
    }
}