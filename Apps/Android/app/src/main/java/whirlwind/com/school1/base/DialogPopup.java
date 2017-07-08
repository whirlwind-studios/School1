package whirlwind.com.school1.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

public abstract class DialogPopup extends Popup {

    protected AlertDialog dialog;
    protected View customView, customTitle;
    protected ContentType contentType;

    @Override
    public Popup show(Context context) {
        if (context instanceof BaseActivity && ((BaseActivity) context).isValid()) {
            Popup.addActive(this);
            return super.show(context);
        } else
            return show();
    }

    @Override
    public void showPopup() {
        if (dialog != null) {
            dialog.show();
            onPostShow();
        }
    }

    @Override
    public void dismissPopup() {
        if (dialog != null && dialog.isShowing() && !((Activity) context).isDestroyed()) {
            onPreDismiss();
            dialog.dismiss();
        }
    }

    protected void onPreDismiss() {
    }

    protected void onPostShow() {
    }
}