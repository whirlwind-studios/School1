package whirlwind.com.school1.backend;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

public final class Database {

    private static Database database = new Database();
    // Local data
    private SQLiteDatabase db;
    private SharedPreferences configuration;

    public static Database getInstance() {
        return database;
    }

    public SQLiteDatabase getDatabase() {
        return db;
    }

    public void initialize(Context context) {
        if (db != null || configuration != null)
            throw new UnsupportedOperationException("Cannot initialize Database class twice");

        db = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().getPath() + "data.db3", null);
        configuration = PreferenceManager.getDefaultSharedPreferences(context);
    }
}