package de.rwth_aachen.engel.beaconsforproduction;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;

/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_detailview_order extends Fragment{
    order order;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_detailview_order, container, false);
        order = (de.rwth_aachen.engel.beaconsforproduction.order) getArguments().getSerializable("position");
        setHasOptionsMenu(true);
        ((TextView)view.findViewById(R.id.detailViewBeacon)).setText(order.getBeacon());
        if(order.getDatabaseMachine() != null && order.getScanMachine() != null){
            ((TextView)view.findViewById(R.id.detailViewDatabaseMachine)).setText(order.getDatabaseMachine().getName());
            ((TextView)view.findViewById(R.id.detailViewScanMachine)).setText(order.getScanMachine().getName());
        }
        ((TextView)view.findViewById(R.id.detailViewDate)).setText(DateFormat.getDateInstance().format(order.getDueDate()));
        ((TextView)view.findViewById(R.id.detailViewDescription)).setText(order.getDescription());
        ((TextView)view.findViewById(R.id.detailViewName)).setText(order.getName());
        ((TextView)view.findViewById(R.id.detailViewID)).setText(order.getID()+"");
        return view;
    }
}
