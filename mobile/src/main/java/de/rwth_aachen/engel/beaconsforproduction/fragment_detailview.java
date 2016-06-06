package de.rwth_aachen.engel.beaconsforproduction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Domino on 01.06.2016.
 */
public class fragment_detailview  extends Fragment{
    ViewPager viewPager;
    List items;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(
                    R.layout.fragment_detailview, container, false);
            Intent i = getActivity().getIntent();
            setHasOptionsMenu(true);
            int beaconKind = i.getIntExtra(Activity_Main.INTENT_CLASS,0);
            viewPager = (ViewPager) view.findViewById(R.id.fragment_viewPager);
            int position = i.getIntExtra(Activity_Main.INDEX,0);
            ScreenSlidePagerAdapter mPagerAdapter;
            if(beaconKind == 0){
                items = ((Activity_Main)getActivity()).getMachineList();
            }
            else{
                items = ((Activity_Main)getActivity()).getOrderList();
            }
            mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager(),items);
            viewPager.setPageTransformer(true, new DepthPageTransformer());
            viewPager.setAdapter(mPagerAdapter);
            viewPager.setCurrentItem(position);
            if(beaconKind == 0){
                ((Activity_Main) getActivity()).setUpArrow(((Machine)items.get(position)).getName());
            }
            else{

                ((Activity_Main) getActivity()).setUpArrow(((Order)items.get(position)).getName());
            }
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                        @Override
                        public void onPageSelected(int position) {
                            // TODO Auto-generated method stub
                            viewPager.setNextFocusRightId(position);
                            ((Activity_Main) getActivity()).setUpArrow(((Activity_Main)getActivity()).getOrderList().get(position).getName());
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
            return view;
        }
        @Override
        public void onResume() {
            super.onResume();
        }
        private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        List items;
        public ScreenSlidePagerAdapter(FragmentManager fm,List items) {
            super(fm);
            this.items = items;
        }

        @Override
        public int getItemPosition(Object object) {
            return items.indexOf(object);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new Fragment();
            Bundle bundle = new Bundle();
            if(items.get(0).getClass() == (new Machine()).getClass()) {
                fragment = new fragment_detailview_machine();
                bundle.putSerializable("position",(Machine) items.get(position));
            }
            else if(items.get(0).getClass() == (new Order()).getClass()) {
                fragment = new fragment_detailview_order();
                bundle.putSerializable("position",(Order) items.get(position));
            }
            fragment.setArguments(bundle);
            return fragment;
        }
        @Override
        public int getCount() {
            return items.size();
        }
    }
}
class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}