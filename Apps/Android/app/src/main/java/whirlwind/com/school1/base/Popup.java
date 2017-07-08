package whirlwind.com.school1.base;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;

public abstract class Popup {

    static final String ACTION_FILTER = "whirlwind.com.school1.base.Popup";
    private static final Looper mainLooper = Looper.getMainLooper();
    private static final ArrayList<Popup> actives = new ArrayList<>(),
            idles = new ArrayList<>();
    protected Context context;

    static void addActive(Popup popup) {
        synchronized (actives) {
            actives.add(popup);
        }
    }

    static void showIdles(BaseActivity activity) {
        while (true) {
            Popup popup;
            synchronized (idles) {
                if (idles.isEmpty())
                    return;
                popup = idles.remove(0);
            }
            popup.show(activity);
        }
    }

    static void dismissActives() {
        while (true) {
            Popup popup;
            synchronized (actives) {
                if (actives.isEmpty())
                    return;
                popup = actives.remove(0);
            }
            if (popup instanceof DialogPopup)
                synchronized (idles) {
                    idles.add(popup);
                }
            popup.dismissPopup();
        }
    }

    public Popup show() {
        synchronized (idles) {
            idles.add(this);
        }
        LocalBroadcastManager.getInstance(null).sendBroadcast(new Intent(ACTION_FILTER));
        return this;
    }

    public Popup show(Context context) {
        if (context != null && mainLooper == Looper.myLooper()) {
            if (this.context != context) {
                this.context = context;
                build();
            }
            showPopup();
        } else
            show();
        return this;
    }

    public void dismiss() {
        synchronized (idles) {
            idles.remove(this);
        }
        synchronized (actives) {
            actives.remove(this);
        }
        dismissPopup();
    }

    abstract protected void build();

    abstract protected void showPopup();

    abstract protected void dismissPopup();

    public enum ContentType {
        string, resId, stringResId, resIdString, custom
    }
}