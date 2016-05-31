package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Domino on 29.05.2016.
 */
public class fragment_newMachine extends android.support.v4.app.Fragment{
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
            return view;
        }
        @Override
        public void onResume(){
            super.onResume();
            ((Activity_Main)getActivity()).setUpArrow(getString(R.string.newOrder));
        }
        public void saveMachine(View v){
            getActivity().onBackPressed();
        }
    }
