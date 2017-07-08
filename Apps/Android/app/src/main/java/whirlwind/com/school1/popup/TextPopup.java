package whirlwind.com.school1.popup;

import android.app.AlertDialog;
import android.content.DialogInterface;

import whirlwind.com.school1.R;
import whirlwind.com.school1.base.DialogPopup;


public class TextPopup extends DialogPopup {

    private int titleResId, messageResId;
    private String title, message;

    public TextPopup(String title, String message) {
        this.title = title;
        this.message = message;

        contentType = ContentType.string;
    }

    public TextPopup(int titleResId, String message) {
        this.titleResId = titleResId;
        this.message = message;
        contentType = ContentType.resIdString;
    }

    public TextPopup(String title, int messageResId) {
        this.title = title;
        this.messageResId = messageResId;
        contentType = ContentType.stringResId;
    }

    public TextPopup(int titleResId, int messageResId) {
        this.titleResId = titleResId;
        this.messageResId = messageResId;

        contentType = ContentType.resId;
    }

    @Override
    public void build() {
        if (contentType == ContentType.resIdString)
            title = context.getString(titleResId);
        else if (contentType == ContentType.stringResId)
            message = context.getString(messageResId);
        else if (contentType == ContentType.resId) {
            title = context.getString(titleResId);
            message = context.getString(messageResId);
        }

        dialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message)
                .setPositiveButton(R.string.text_ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dismiss();
                    }
                }).create();
    }
}
