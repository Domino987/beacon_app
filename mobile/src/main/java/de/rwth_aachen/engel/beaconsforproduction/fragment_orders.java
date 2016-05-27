package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_orders extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.beacon_list, container, false);
            ((TextView)view.findViewById(R.id.beaconsInReach)).setText(getString(R.string.ordersInReach));
            ((TextView)view.findViewById(R.id.otherBeacons)).setText(getString(R.string.otherOrders));
            List<order> beaconsInReach = new LinkedList<>();
            order order = new order();
            order.setName("Schönes Teil");
            order.setBeacon("beacon1");
            machine machine = new machine();
            machine.setName("Drehen 1");
            order.setDatabaseMachine(machine);
            order.setDescription("Diese schöne Scheiben-sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssmatrize sollten wir genau im Auge behalten");
            order.setDueDate(new Date());
            order.setScanMachine(machine);
            order.setID(1254);
            order.setTemperatur(100);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconsInReach.add(order);
            beaconAdapter.OnItemClickListener listener = new beaconAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(machine item) {
                }
                @Override
                public void onItemClick(order item) {
                    Fragment fragment = new fragment_detailview_order();
                    getActivity().getIntent().putExtra("order",item);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentMainContainer, fragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            };
            RecyclerView inReachList= (RecyclerView) view.findViewById(R.id.beaconsInReachList);
            inReachList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            beaconAdapter inAdapter = new beaconAdapter(beaconsInReach, listener);
            inReachList.setAdapter(inAdapter);
            RecyclerView otherList= (RecyclerView) view.findViewById(R.id.otherBeaconsList);
            otherList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            inAdapter = new beaconAdapter(beaconsInReach, listener);
            otherList.setAdapter(inAdapter);
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(getString(R.string.orders));
    }
}
