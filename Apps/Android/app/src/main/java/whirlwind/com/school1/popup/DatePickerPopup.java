package whirlwind.com.school1.popup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;

import java.util.Calendar;

import whirlwind.com.school1.base.DialogPopup;

public class DatePickerPopup extends DialogPopup {

    private final OnDateSetListener onDateSetListener;
    private final Calendar calendar;

    public DatePickerPopup(Calendar calendar, OnDateSetListener onDateSetListener) {
        this.calendar = calendar;
        this.onDateSetListener = onDateSetListener;
        contentType = ContentType.custom;
    }

    @Override
    public void build() {
        dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                onDateSetListener.onDateSet(context, year, month, dayOfMonth);
                dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePickerDialog datePickerDialog = (DatePickerDialog) dialog;

        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dismiss();
            }
        });

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, 2);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
    }

    @Override
    protected void onPreDismiss() {
        DatePicker datePicker = ((DatePickerDialog) dialog).getDatePicker();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
    }

    public interface OnDateSetListener {
        void onDateSet(Context context, int year, int month, int dayOfMonth);
    }
}