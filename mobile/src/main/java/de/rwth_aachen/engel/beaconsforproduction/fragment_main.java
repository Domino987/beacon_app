package de.rwth_aachen.engel.beaconsforproduction;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class fragment_main extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_main, container, false);
        RelativeLayout machines = (RelativeLayout) view.findViewById(R.id.machinesLayout);
        RelativeLayout orders = (RelativeLayout) view.findViewById(R.id.ordersLayout);
        machines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fragment_orders();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentMainContainer, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fragment_machines();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentMainContainer, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
