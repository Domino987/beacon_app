package de.rwth_aachen.engel.beaconsforproduction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_detailview_order extends Fragment{
    order order;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_detailview_order, container, false);
        Intent i = getActivity().getIntent();
         order = (order) i.getSerializableExtra("order");
        ((TextView)view.findViewById(R.id.detailViewBeacon)).setText(order.getBeacon());
        ((TextView)view.findViewById(R.id.detailViewDatabaseMachine)).setText(order.getDatabaseMachine().getName());
        ((TextView)view.findViewById(R.id.detailViewDate)).setText(order.getDueDate().toString());
        ((TextView)view.findViewById(R.id.detailViewDescription)).setText(order.getDescription());
        ((TextView)view.findViewById(R.id.detailViewName)).setText(order.getName());
        ((TextView)view.findViewById(R.id.detailViewID)).setText(order.getID()+"");
        ((TextView)view.findViewById(R.id.detailViewScanMachine)).setText(order.getScanMachine().getName());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(order.getName());
    }
}
