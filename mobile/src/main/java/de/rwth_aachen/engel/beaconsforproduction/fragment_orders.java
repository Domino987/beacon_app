package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_orders extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.beacon_list, container, false);
            Adapter_Beacons.OnItemClickListener listener = new Adapter_Beacons.OnItemClickListener() {
                @Override
                public void onItemClick(machine item) {
                }
                @Override
                public void onItemClick(order item) {
                    Fragment fragment = new fragment_detailview();
                    getActivity().getIntent().putExtra(Activity_Main.INTENT_CLASS,1);
                    getActivity().getIntent().putExtra(Activity_Main.INDEX,((Activity_Main)getActivity()).getOrderList().indexOf(item));
                    setHasOptionsMenu(true);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentMainContainer, fragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            };
            if(((Activity_Main)getActivity()).getMachineList() != null){
                RecyclerView inReachList= (RecyclerView) view.findViewById(R.id.beaconsInReachList);
                inReachList.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                Adapter_Beacons inAdapter = new Adapter_Beacons(((Activity_Main)getActivity()).getOrderList(),((Activity_Main)getActivity()).getOrderList(), listener,getString(R.string.ordersInReach),getString(R.string.otherOrders));
                inReachList.setAdapter(inAdapter);
            }
            else{
                Toast.makeText(getActivity(), getString(R.string.noConnection), Toast.LENGTH_SHORT).show();
            }
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(getString(R.string.orders));
    }
}

