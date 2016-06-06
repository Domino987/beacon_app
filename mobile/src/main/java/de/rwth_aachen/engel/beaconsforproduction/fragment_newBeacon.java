package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Domino on 02.06.2016.
 */
public class fragment_newBeacon extends Fragment {
    ViewPager mViewPager;
    TabLayout tabLayout;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_new_beacon, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.new_beacon_pager);
        setUpViewPager();
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.setupWithViewPager(mViewPager);
        getActivity().findViewById(R.id.fab).setVisibility(View.GONE);
        ((Activity_Main) getActivity()).setUpArrow(getString(R.string.newOrder));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tabLayout.setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.fab).setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        tabLayout.setVisibility(View.GONE);
        getActivity().findViewById(R.id.fab).setVisibility(View.VISIBLE);
    }

    public void setUpViewPager(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new fragment_newOrder(), getString(R.string.newOrder));
        adapter.addFragment(new fragment_newMachine(), getString(R.string.newMachine));
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                mViewPager.setNextFocusRightId(position);
                if(position == 0){
                    ((Activity_Main) getActivity()).setUpArrow(getString(R.string.newOrder));
                }
                else{
                    ((Activity_Main) getActivity()).setUpArrow(getString(R.string.newMachine));
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int pos) {
                // TODO Auto-generated method stub

            }
        });
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
