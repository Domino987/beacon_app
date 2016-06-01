package de.rwth_aachen.engel.beaconsforproduction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Domino on 01.06.2016.
 */
public class fragment_detailview  extends Fragment{
    ViewPager viewPager;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.fragment_detailview, container, false);
            Intent i = getActivity().getIntent();
            i.getIntExtra(Activity_Main.INTENT_CLASS,0);
            viewPager = (ViewPager) view.findViewById(R.id.fragment_viewPager);
            int position = i.getIntExtra(Activity_Main.INDEX,0);
            ScreenSlidePagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager(),((Activity_Main)getActivity()).getMachineList());
            viewPager.setAdapter(mPagerAdapter);
            viewPager.setCurrentItem(position);
            return view;
        }
        @Override
        public void onResume() {
            super.onResume();
        }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        List<machine> machines;
        public ScreenSlidePagerAdapter(FragmentManager fm,List<machine> machines) {
            super(fm);
            this.machines = machines;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return machines.get(position).getName();
        }

        @Override
        public int getItemPosition(Object object) {
            return machines.indexOf(object);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new fragment_detailview_machine();
            Bundle bundle = new Bundle();
            bundle.putSerializable("position", machines.get(position));
            fragment.setArguments(bundle);
            return fragment;
        }
        @Override
        public int getCount() {
            return machines.size();
        }
    }
}