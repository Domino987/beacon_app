package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_orders extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.beacon_list, container, false);
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
        ((Activity_Main)getActivity()).setUpArrow(getString(R.string.orders));
    }
}
