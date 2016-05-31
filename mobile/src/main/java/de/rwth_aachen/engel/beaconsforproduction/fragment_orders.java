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

import java.util.ArrayList;
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
            view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newOrder(v);
                }
            });
            Adapter_Beacons.OnItemClickListener listener = new Adapter_Beacons.OnItemClickListener() {
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
            inReachList.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            Adapter_Beacons inAdapter = new Adapter_Beacons(getOrdersInReach(),getOtherOrders(), listener,getString(R.string.ordersInReach),getString(R.string.otherOrders));
            inReachList.setAdapter(inAdapter);
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(getString(R.string.orders));
    }
    public void newOrder(View v){
        Fragment fragment = new fragment_newOrder();
        android.support.v4.app.FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentMainContainer,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public  List<order> getOrdersInReach(){
        List<order> listItems = new ArrayList<>();
        for (int i = 0; i<5; i++) {
            order order = new order();
            order.setName("inReach"+i);
            order.setBeacon("beacon1");
            machine machine = new machine();
            machine.setName("Drehen 1");
            order.setDatabaseMachine(machine);
            order.setDescription("Diese schöne Scheiben-sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssmatrize sollten wir genau im Auge behalten");
            order.setDueDate(new Date());
            order.setScanMachine(machine);
            order.setID(1254);
            listItems.add(order);
        }
        return listItems;
    }
    public  List<order> getOtherOrders(){
        List<order> listItems = new ArrayList<>();
        for (int i = 0; i<5; i++) {
            order order = new order();
            order.setName("other"+i);
            order.setBeacon("beacon1");
            machine machine = new machine();
            machine.setName("Drehen 1");
            order.setDatabaseMachine(machine);
            order.setDescription("Diese schöne Scheiben-sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssmatrize sollten wir genau im Auge behalten");
            order.setDueDate(new Date());
            order.setScanMachine(machine);
            order.setID(1254);
            listItems.add(order);
        }
        return listItems;
    }
}

