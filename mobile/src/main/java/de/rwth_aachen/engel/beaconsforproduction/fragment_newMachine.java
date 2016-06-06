package de.rwth_aachen.engel.beaconsforproduction;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Domino on 29.05.2016.
 */
public class fragment_newMachine extends android.support.v4.app.Fragment{
    EditText editName,editId,editDescription;
    Spinner beacon;
    Machine machine;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.fragment_new_machine, container, false);
            view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveMachine(v);
                }
            });
            editId = (EditText) view.findViewById(R.id.detailViewID);
            editName = (EditText) view.findViewById(R.id.detailViewName);
            editDescription = (EditText) view.findViewById(R.id.detailViewDescription);
            beacon = (Spinner)view.findViewById(R.id.detailViewBeacon);
            setHasOptionsMenu(true);
            return view;
        }
    public void saveMachine(View v){
        machine = new Machine();
        if(!editName.getText().toString().equals("")  && !editId.getText().toString().equals("")){
            machine.setName(editName.getText().toString());
            machine.setDescription(editDescription.getText().toString());
            machine.setID(editId.getText().toString());
            if(beacon.getSelectedItem()!= null ){
                machine.setBeacon((String) beacon.getSelectedItem());
            }
            else{
                machine.setBeacon("null");
            }
            ((Activity_Main)getActivity()).addMachine(machine);
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            getActivity().onBackPressed();
        }
        else{
            Snackbar.make(getActivity().findViewById(android.R.id.content),R.string.noEnoughInformation,Snackbar.LENGTH_SHORT).show();
        }
    }
}
