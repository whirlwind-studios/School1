package whirlwind.com.school1.backend;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import whirlwind.com.school1.BuildConfig;

public final class Database {

    private static Database database = new Database();
    // Local data
    private SQLiteOpenHelper helper;
    private SharedPreferences configuration;

    public static Database getInstance() {
        return database;
    }

    public SQLiteOpenHelper getHelper() {
        return helper;
    }

    public void initialize(Context context) {
        if (helper != null || configuration != null)
            throw new UnsupportedOperationException("Cannot initialize Database class twice");

        configuration = PreferenceManager.getDefaultSharedPreferences(context);

        helper = new SQLiteOpenHelper(context, "data.db3", null, BuildConfig.VERSION_CODE) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                // TODO: Create database tables
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // TODO: Upgrade database tables
            }
        };
        helper.setWriteAheadLoggingEnabled(true);
        helper.getWritableDatabase();
    }
}