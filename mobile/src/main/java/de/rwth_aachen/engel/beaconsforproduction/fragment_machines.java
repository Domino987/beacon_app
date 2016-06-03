package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_machines extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.beacon_list, container, false);

            final List<machine> machinesList =  ((Activity_Main)getActivity()).getMachineList();
            Adapter_Beacons.OnItemClickListener listener = new Adapter_Beacons.OnItemClickListener() {
                @Override
                public void onItemClick(machine item) {
                    Fragment fragment = new fragment_detailview();
                    getActivity().getIntent().putExtra(Activity_Main.INTENT_CLASS,0);
                    getActivity().getIntent().putExtra(Activity_Main.INDEX,(machinesList.indexOf(item)));
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

            if(machinesList != null){
                RecyclerView inReachList= (RecyclerView) view.findViewById(R.id.beaconsInReachList);
                inReachList.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                Adapter_Beacons inAdapter = new Adapter_Beacons(machinesList, machinesList, listener,getString(R.string.machinesInReach),getString(R.string.otherMachines));
                inReachList.setAdapter(inAdapter);
            }
            else{
                Snackbar.make(view,getString(R.string.noConnection),Snackbar.LENGTH_SHORT);
            }
            return view;
        }
    @Override
    public void onResume() {
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(getString(R.string.machines));
    }
}
