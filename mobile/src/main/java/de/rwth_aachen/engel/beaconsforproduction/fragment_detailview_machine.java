package de.rwth_aachen.engel.beaconsforproduction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_detailview_machine extends Fragment{
    machine machine;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_detailview_machine, container, false);
        Intent i = getActivity().getIntent();
        machine = (machine) i.getSerializableExtra("machine");
        ((TextView)view.findViewById(R.id.detailViewBeacon)).setText(machine.getBeacon());
        ((TextView)view.findViewById(R.id.detailViewDescription)).setText(machine.getDescription());
        ((TextView)view.findViewById(R.id.detailViewName)).setText(machine.getName());
        ((TextView)view.findViewById(R.id.detailViewID)).setText(machine.getID()+"");
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(machine.getName());
    }
}
