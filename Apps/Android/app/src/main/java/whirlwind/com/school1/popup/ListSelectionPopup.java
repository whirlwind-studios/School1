package whirlwind.com.school1.popup;

import android.app.AlertDialog;
import android.content.DialogInterface;

import whirlwind.com.school1.base.DialogPopup;
import whirlwind.com.school1.base.Popup;

public class ListSelectionPopup extends DialogPopup {

    private final OnClickListener onClickListener;
    private String title;
    private String[] strings;
    private int titleResId, stringsResId;

    public ListSelectionPopup(int titleResId, int stringArrayResId, OnClickListener onClickListener) {
        this.titleResId = titleResId;
        this.stringsResId = stringArrayResId;
        this.onClickListener = onClickListener;
        contentType = Popup.ContentType.resId;
    }

    public ListSelectionPopup(int titleResId, String[] strings, OnClickListener onClickListener) {
        this.titleResId = titleResId;
        this.strings = strings;
        this.onClickListener = onClickListener;
        contentType = ContentType.resIdString;
    }

    public ListSelectionPopup(String title, int stringArrayResId, OnClickListener onClickListener) {
        this.title = title;
        this.stringsResId = stringArrayResId;
        this.onClickListener = onClickListener;
        contentType = ContentType.stringResId;
    }

    public ListSelectionPopup(String title, String strings[], OnClickListener onClickListener) {
        this.title = title;
        this.strings = strings;
        this.onClickListener = onClickListener;
        contentType = ContentType.string;
    }

    @Override
    public void build() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dismiss();
                    }
                });

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
                ListSelectionPopup.this.onClickListener.onClick(which);
            }
        };

        if (contentType == ContentType.string)
            builder.setTitle(title).setItems(strings, onClickListener);
        else if (contentType == ContentType.stringResId)
            builder.setTitle(title).setItems(stringsResId, onClickListener);
        else if (contentType == ContentType.resId)
            builder.setTitle(titleResId).setItems(stringsResId, onClickListener);
        else if (contentType == ContentType.resIdString)
            builder.setTitle(titleResId).setItems(strings, onClickListener);

        dialog = builder.create();
    }

    public interface OnClickListener {
        void onClick(int which);
    }
}