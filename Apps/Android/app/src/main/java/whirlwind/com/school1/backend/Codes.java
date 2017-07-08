package whirlwind.com.school1.backend;

import android.content.Context;
import android.util.DisplayMetrics;

public class Codes {

    private static DisplayMetrics metrics;

    public static void initialize(Context context) {
        metrics = context.getResources().getDisplayMetrics();
    }

    public static float convertDpFloat(float value) {
        return value * metrics.density;
    }

    public static int convertDp(float value) {
        return (int) convertDpFloat(value);
    }
}
