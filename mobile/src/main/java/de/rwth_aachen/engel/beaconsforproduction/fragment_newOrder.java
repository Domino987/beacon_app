package de.rwth_aachen.engel.beaconsforproduction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dominik Engel on 27.05.2016.
 */
public class fragment_newOrder extends Fragment {
    EditText edit;
    Date date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_new_order, container, false);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        setHasOptionsMenu(true);
        edit = ((EditText)view.findViewById(R.id.detailViewDate));
        date = Calendar.getInstance().getTime();
        edit.setText(df.format(date));
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDueDate(v);
            }
        });
        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrder(v);
            }
        });
        return view;
    }
    public void pickDueDate(View v){
        DatePickerDialog.OnDateSetListener myDatePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                Calendar newCalendar = Calendar.getInstance();
                newCalendar.set(arg1, arg2, arg3);
                date = newCalendar.getTime();
                edit.setText(DateFormat.getDateInstance().format(date));
            }
        };
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(date);
        DatePickerDialog datepicker = new DatePickerDialog(getActivity(),myDatePickerListener, newCalendar.get(Calendar.YEAR),newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));
        datepicker.show();
    }
    public void saveOrder(View v){
        getActivity().onBackPressed();
    }
}
