package de.rwth_aachen.engel.beaconsforproduction;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fragment_main extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_main, container, false);
        android.support.v7.widget.CardView machines = (android.support.v7.widget.CardView) view.findViewById(R.id.machinesButton);
        android.support.v7.widget.CardView orders = (android.support.v7.widget.CardView) view.findViewById(R.id.ordersButton);
        machines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fragment_machines();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentMainContainer, fragment,"FRAGMENT_MACHINES");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fragment_orders();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentMainContainer, fragment,"FRAGMENT_ORDERS");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(getString(R.string.WZL));
    }
    @Override
    public void onClick(View view) {

    }
}
