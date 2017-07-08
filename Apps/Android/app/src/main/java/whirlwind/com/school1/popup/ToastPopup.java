package whirlwind.com.school1.popup;

import android.annotation.SuppressLint;
import android.widget.Toast;

import whirlwind.com.school1.base.Popup;

public class ToastPopup extends Popup {

    private final ContentType contentType;
    private Toast toast;
    private String message;
    private int messageResId;

    public ToastPopup(String message) {
        this.message = message;
        this.contentType = ContentType.string;
    }

    public ToastPopup(int messageResId) {
        this.messageResId = messageResId;
        this.contentType = ContentType.resId;
    }

    @SuppressLint("ShowToast")
    @Override
    public void build() {
        if (contentType == ContentType.string)
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        else if (contentType == ContentType.resId)
            toast = Toast.makeText(context, messageResId, Toast.LENGTH_LONG);
    }

    @Override
    public void showPopup() {
        if (toast != null)
            toast.show();
    }

    @Override
    public void dismissPopup() {
        if (toast != null)
            toast.cancel();
    }
}