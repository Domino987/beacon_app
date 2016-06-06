package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_orders extends Fragment{
    Adapter_Beacons inAdapter;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.beacon_list, container, false);

            final List<Order> orderList = ((Activity_Main)getActivity()).getOrderList();

            Adapter_Beacons.OnItemClickListener listener = new Adapter_Beacons.OnItemClickListener() {
                @Override
                public void onItemClick(Machine item) {
                }
                @Override
                public void onItemClick(Order item) {
                    Fragment fragment = new fragment_detailview();
                    getActivity().getIntent().putExtra(Activity_Main.INTENT_CLASS,1);
                    getActivity().getIntent().putExtra(Activity_Main.INDEX,(orderList.indexOf(item)));
                    setHasOptionsMenu(true);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentMainContainer, fragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            };
            if(orderList != null){
                RecyclerView inReachList= (RecyclerView) view.findViewById(R.id.beaconsInReachList);
                inReachList.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                inAdapter = new Adapter_Beacons(orderList, orderList, listener,getString(R.string.ordersInReach),getString(R.string.otherOrders));
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
        ((Activity_Main)getActivity()).setUpArrow(getString(R.string.orders));
    }
    public Adapter_Beacons getAdapter(){
        return inAdapter;
    }
}

