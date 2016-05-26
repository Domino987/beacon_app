package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Domino on 26.05.2016.
 */

public class fragment_machines extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.beacon_list, container, false);
            ((TextView)view.findViewById(R.id.beaconsInReach)).setText(getString(R.string.machinesInReach));
            ((TextView)view.findViewById(R.id.otherBeacons)).setText(getString(R.string.otherMachnies));
            return view;
        }
    }
