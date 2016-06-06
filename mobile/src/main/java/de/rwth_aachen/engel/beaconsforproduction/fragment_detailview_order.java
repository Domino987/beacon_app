package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;

/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_detailview_order extends Fragment{
    Order Order;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_detailview_order, container, false);
        Order = (Order) getArguments().getSerializable("position");
        setHasOptionsMenu(true);
        ((TextView)view.findViewById(R.id.detailViewBeacon)).setText(Order.getBeacon());
        if(Order.getDatabaseMachine() != null) {
            ((TextView)view.findViewById(R.id.detailViewDatabaseMachine)).setText(Order.getDatabaseMachine().getName());
        }
        if(Order.getScanMachine() != null) {
            ((TextView)view.findViewById(R.id.detailViewScanMachine)).setText(Order.getScanMachine().getName());
        }
        ((TextView)view.findViewById(R.id.detailViewDate)).setText(DateFormat.getDateInstance().format(Order.getDueDate()));
        ((TextView)view.findViewById(R.id.detailViewDescription)).setText(Order.getDescription());
        ((TextView)view.findViewById(R.id.detailViewName)).setText(Order.getName());
        ((TextView)view.findViewById(R.id.detailViewID)).setText(Order.getID());
        return view;
    }
}
