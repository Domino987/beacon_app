package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dominik Engel on 27.05.2016.
 */
public class fragmentNewOrder extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.fragment_new_order, container, false);

            return view;
        }
        @Override
        public void onResume(){
            super.onResume();
            ((Activity_Main)getActivity()).setUpArrow(getString(R.string.newOrder));
        }
    }
