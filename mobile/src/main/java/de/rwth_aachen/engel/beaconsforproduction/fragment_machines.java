package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
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
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.purple));
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newMachine(v);
                }
            });
            List<machine> beaconsInReach = new LinkedList<>();
            machine machineItem = new machine();
            machineItem.setName("asd");
            machineItem.setBeacon("beacon 1");
            machineItem.setDescription("asddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
            machineItem.setID(1546);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            beaconsInReach.add(machineItem);
            Adapter_Beacons.OnItemClickListener listener = new Adapter_Beacons.OnItemClickListener() {
                @Override
                public void onItemClick(machine item) {
                    Fragment fragment = new fragment_detailview_machine();
                    getActivity().getIntent().putExtra("machine",item);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentMainContainer, fragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                }

                @Override
                public void onItemClick(order item) {
                }
            };
            RecyclerView inReachList= (RecyclerView) view.findViewById(R.id.beaconsInReachList);
            inReachList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            Adapter_Beacons inAdapter = new Adapter_Beacons(beaconsInReach, listener);
            inReachList.setAdapter(inAdapter);
            RecyclerView otherList= (RecyclerView) view.findViewById(R.id.otherBeaconsList);
            otherList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            inAdapter = new Adapter_Beacons(beaconsInReach,listener);
            otherList.setAdapter(inAdapter);
            return view;
        }
    @Override
    public void onResume() {
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(getString(R.string.machines));
    }
    public void newMachine(View v){
        Fragment fragment = new fragment_newMachine();
        android.support.v4.app.FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentMainContainer,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
