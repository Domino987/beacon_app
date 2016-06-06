package de.rwth_aachen.engel.beaconsforproduction;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dominik Engel on 27.05.2016.
 */
public class fragment_newOrder extends Fragment {
    EditText editDate,editId,editName,editDescription;
    Spinner beaconSpinner;
    Date date;
    Beacon order;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_new_order, container, false);
        DateFormat df = DateFormat.getDateInstance();
        setHasOptionsMenu(true);
        editDate = (EditText)view.findViewById(R.id.detailViewDate);
        editId = (EditText) view.findViewById(R.id.detailViewID);
        editName = (EditText) view.findViewById(R.id.detailViewName);
        editDescription = (EditText) view.findViewById(R.id.detailViewDescription);
        date = Calendar.getInstance().getTime();
        editDate.setText(df.format(date));
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDueDate(v);
            }
        });
        beaconSpinner = (Spinner) view.findViewById(R.id.detailViewBeacon);
        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrder(v);
            }
        });
        setHasOptionsMenu(true);
        return view;
    }
    public void pickDueDate(View v){
        DatePickerDialog.OnDateSetListener myDatePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                Calendar newCalendar = Calendar.getInstance();
                newCalendar.set(arg1, arg2, arg3);
                date = newCalendar.getTime();
                editDate.setText(DateFormat.getDateInstance().format(date));
            }
        };
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(date);
        DatePickerDialog datepicker = new DatePickerDialog(getActivity(),myDatePickerListener, newCalendar.get(Calendar.YEAR),newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));
        datepicker.show();
    }
    public void saveOrder(View v){
        order = new Beacon();
        order.setKind(true);
        if(!editName.getText().toString().equals("")  && !editId.getText().toString().equals("")){
            order.setName(editName.getText().toString());
            order.setDescription(editDescription.getText().toString());
            order.setID(editId.getText().toString());
            order.setDueDate(date);
            if(beaconSpinner.getSelectedItem()!= null ){
                order.setBeacon((String) beaconSpinner.getSelectedItem());
            }
            else{
                order.setBeacon("null");
            }
            ((Activity_Main)getActivity()).addOrder(order);
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            getActivity().onBackPressed();
        }
        else{
            Snackbar.make(getActivity().findViewById(android.R.id.content),R.string.noEnoughInformation,Snackbar.LENGTH_SHORT).show();
        }
    }
}
