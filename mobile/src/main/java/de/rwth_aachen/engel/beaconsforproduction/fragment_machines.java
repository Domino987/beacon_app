package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_machines extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.beacon_list, container, false);
            ((TextView)view.findViewById(R.id.beaconsInReach)).setText(getString(R.string.machinesInReach));
            ((TextView)view.findViewById(R.id.otherBeacons)).setText(getString(R.string.otherMachnies));
            List<machine> beaconsInReach = new LinkedList<>();
            machine machineItem = new machine();
            machineItem.setName("asd");
            beaconsInReach.add(machineItem);
            RecyclerView inReachList= (RecyclerView) view.findViewById(R.id.beaconsInReachList);
            inReachList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            beaconAdapter inAdapter = new beaconAdapter(beaconsInReach,android.R.layout.simple_list_item_1);
            inReachList.setAdapter(inAdapter);
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(getString(R.string.machines));
    }
}
