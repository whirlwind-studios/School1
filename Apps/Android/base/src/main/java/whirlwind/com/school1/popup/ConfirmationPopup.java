package whirlwind.com.school1.popup;

import android.app.AlertDialog;
import android.content.DialogInterface;

import whirlwind.com.school1.R;
import whirlwind.com.school1.base.DialogPopup;

public class ConfirmationPopup extends DialogPopup {

    private final Runnable onConfirmListener;
    private int titleResId, messageResId;
    private String title, message;

    public ConfirmationPopup(String title, String message, Runnable onConfirmListener) {
        this.title = title;
        this.message = message;
        this.onConfirmListener = onConfirmListener;
        contentType = ContentType.string;
    }

    public ConfirmationPopup(int titleResId, int messageResId, Runnable onConfirmListener) {
        this.titleResId = titleResId;
        this.messageResId = messageResId;
        this.onConfirmListener = onConfirmListener;
        contentType = ContentType.resId;
    }

    @Override
    public void build() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setNegativeButton(R.string.text_cancel_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton(R.string.text_ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                        onConfirmListener.run();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dismiss();
                    }
                });

        if (contentType == ContentType.string)
            builder.setTitle(title).setMessage(message);
        else if (contentType == ContentType.resId)
            builder.setTitle(titleResId).setMessage(messageResId);

        dialog = builder.create();
    }
}
