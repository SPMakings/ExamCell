package org.spmakings.examshell.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.spmakings.examshell.Models.Subject;
import org.spmakings.examshell.R;
import org.spmakings.examshell.adapters.PagerAdapter;
import org.spmakings.examshell.customview.AvenirRegular;

import java.util.LinkedList;

/**
 * Created by apple on 17/07/16.
 */

public class HomeFragments extends Fragment {

    private LinkedList<Subject> subListingArray = null;

    //=============header pager management

    private LinkedList<String> imageID = null;
    private ViewPager landingPager = null;

    //=============Subject management

    AvenirRegular JEST, HONS, MSC, GET, TIFR, NET, JAM;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragments_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        landingPager = (ViewPager) view.findViewById(R.id.landing_pager);

        JEST = (AvenirRegular) view.findViewById(R.id.jest);
        HONS = (AvenirRegular) view.findViewById(R.id.hons);
        MSC = (AvenirRegular) view.findViewById(R.id.msc);
        GET = (AvenirRegular) view.findViewById(R.id.get);
        TIFR = (AvenirRegular) view.findViewById(R.id.tifr);
        NET = (AvenirRegular) view.findViewById(R.id.net);
        JAM = (AvenirRegular) view.findViewById(R.id.jam);

        if (subListingArray == null) {
            feedData();
        }
        if (imageID == null) {
            initImageData();
        }


        landingPager.setAdapter(new PagerAdapter(getActivity(), subListingArray, imageID));


        landingPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                if (position == 0) {
                    JEST.setBackgroundResource(R.drawable.rounded_corner_cyan_fill);
                    HONS.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    MSC.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    GET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    TIFR.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    NET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    JAM.setBackgroundResource(R.drawable.rounded_corner_white_ring);

                    JEST.setTextColor(Color.WHITE);
                    HONS.setTextColor(Color.parseColor("#6B6B6B"));
                    MSC.setTextColor(Color.parseColor("#6B6B6B"));
                    GET.setTextColor(Color.parseColor("#6B6B6B"));
                    TIFR.setTextColor(Color.parseColor("#6B6B6B"));
                    NET.setTextColor(Color.parseColor("#6B6B6B"));
                    JAM.setTextColor(Color.parseColor("#6B6B6B"));
                } else if (position == 1) {
                    JEST.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    HONS.setBackgroundResource(R.drawable.rounded_corner_cyan_fill);
                    MSC.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    GET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    TIFR.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    NET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    JAM.setBackgroundResource(R.drawable.rounded_corner_white_ring);

                    JEST.setTextColor(Color.parseColor("#6B6B6B"));
                    HONS.setTextColor(Color.WHITE);
                    MSC.setTextColor(Color.parseColor("#6B6B6B"));
                    GET.setTextColor(Color.parseColor("#6B6B6B"));
                    TIFR.setTextColor(Color.parseColor("#6B6B6B"));
                    NET.setTextColor(Color.parseColor("#6B6B6B"));
                    JAM.setTextColor(Color.parseColor("#6B6B6B"));
                } else if (position == 2) {
                    JEST.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    HONS.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    MSC.setBackgroundResource(R.drawable.rounded_corner_cyan_fill);
                    GET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    TIFR.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    NET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    JAM.setBackgroundResource(R.drawable.rounded_corner_white_ring);

                    JEST.setTextColor(Color.parseColor("#6B6B6B"));
                    HONS.setTextColor(Color.parseColor("#6B6B6B"));
                    MSC.setTextColor(Color.WHITE);
                    GET.setTextColor(Color.parseColor("#6B6B6B"));
                    TIFR.setTextColor(Color.parseColor("#6B6B6B"));
                    NET.setTextColor(Color.parseColor("#6B6B6B"));
                    JAM.setTextColor(Color.parseColor("#6B6B6B"));
                } else if (position == 3) {
                    JEST.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    HONS.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    MSC.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    GET.setBackgroundResource(R.drawable.rounded_corner_cyan_fill);
                    TIFR.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    NET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    JAM.setBackgroundResource(R.drawable.rounded_corner_white_ring);

                    JEST.setTextColor(Color.parseColor("#6B6B6B"));
                    HONS.setTextColor(Color.parseColor("#6B6B6B"));
                    MSC.setTextColor(Color.parseColor("#6B6B6B"));
                    GET.setTextColor(Color.WHITE);
                    TIFR.setTextColor(Color.parseColor("#6B6B6B"));
                    NET.setTextColor(Color.parseColor("#6B6B6B"));
                    JAM.setTextColor(Color.parseColor("#6B6B6B"));
                } else if (position == 4) {
                    JEST.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    HONS.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    MSC.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    GET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    TIFR.setBackgroundResource(R.drawable.rounded_corner_cyan_fill);
                    NET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    JAM.setBackgroundResource(R.drawable.rounded_corner_white_ring);

                    JEST.setTextColor(Color.parseColor("#6B6B6B"));
                    HONS.setTextColor(Color.parseColor("#6B6B6B"));
                    MSC.setTextColor(Color.parseColor("#6B6B6B"));
                    GET.setTextColor(Color.parseColor("#6B6B6B"));
                    TIFR.setTextColor(Color.WHITE);
                    NET.setTextColor(Color.parseColor("#6B6B6B"));
                    JAM.setTextColor(Color.parseColor("#6B6B6B"));
                } else if (position == 5) {
                    JEST.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    HONS.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    MSC.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    GET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    TIFR.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    NET.setBackgroundResource(R.drawable.rounded_corner_cyan_fill);
                    JAM.setBackgroundResource(R.drawable.rounded_corner_white_ring);

                    JEST.setTextColor(Color.parseColor("#6B6B6B"));
                    HONS.setTextColor(Color.parseColor("#6B6B6B"));
                    MSC.setTextColor(Color.parseColor("#6B6B6B"));
                    GET.setTextColor(Color.parseColor("#6B6B6B"));
                    TIFR.setTextColor(Color.parseColor("#6B6B6B"));
                    NET.setTextColor(Color.WHITE);
                    JAM.setTextColor(Color.parseColor("#6B6B6B"));
                } else {
                    JEST.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    HONS.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    MSC.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    GET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    TIFR.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    NET.setBackgroundResource(R.drawable.rounded_corner_white_ring);
                    JAM.setBackgroundResource(R.drawable.rounded_corner_cyan_fill);

                    JEST.setTextColor(Color.parseColor("#6B6B6B"));
                    HONS.setTextColor(Color.parseColor("#6B6B6B"));
                    MSC.setTextColor(Color.parseColor("#6B6B6B"));
                    GET.setTextColor(Color.parseColor("#6B6B6B"));
                    TIFR.setTextColor(Color.parseColor("#6B6B6B"));
                    NET.setTextColor(Color.parseColor("#6B6B6B"));
                    JAM.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


//    public float getScreenWidth() {
//        DisplayMetrics metrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        return metrics.widthPixels;
//    }


    private void feedData() {
        subListingArray = new LinkedList<Subject>();
        Subject temp_ = null;

        temp_ = new Subject();
        temp_.setSubID("10");
        temp_.setSubName("JEST");
        subListingArray.add(temp_);

        temp_ = new Subject();
        temp_.setSubID("7");
        temp_.setSubName("Honors Exam");
        subListingArray.add(temp_);

        temp_ = new Subject();
        temp_.setSubID("6");
        temp_.setSubName("Entrance Test for M.sc");
        subListingArray.add(temp_);

        temp_ = new Subject();
        temp_.setSubID("5");
        temp_.setSubName("GATE");
        subListingArray.add(temp_);

        temp_ = new Subject();
        temp_.setSubID("4");
        temp_.setSubName("NET");
        subListingArray.add(temp_);


        temp_ = new Subject();
        temp_.setSubID("3");
        temp_.setSubName("TIFR");
        subListingArray.add(temp_);

        temp_ = new Subject();
        temp_.setSubID("1");
        temp_.setSubName("JAM");
        subListingArray.add(temp_);
    }


    public void initImageData() {

        imageID = new LinkedList<String>();
        imageID.add("http://4.bp.blogspot.com/_zbSMf0jJ03A/TFEyYJMQX2I/AAAAAAAAFIU/uO6OrAGiQkg/s1600/serioustabule.jpg");

        imageID.add("https://i.kinja-img.com/gawker-media/image/upload/s--bS9u_5r1--/c_scale,fl_progressive,q_80,w_800/iexoxvmweqa5vteljpof.jpg");

        imageID.add("http://davidpalmerstudio.com/images_merchandise/DavidPalmer_ROLLOUT_4.jpg");

        imageID.add("http://www.smashingbuzz.com/wp-content/uploads/2015/02/final-exam-wallpaper.jpg");

        imageID.add("http://www.walldevil.com/wallpapers/a68/earth-planet-globe-satellite-night-light.jpg");

        imageID.add("http://wallpapercave.com/wp/doAHAI6.jpg");

        imageID.add("http://geniusquotes.org/wp-content/uploads/2014/05/Keep-calm-and-study-hd-quote-wallpaper.jpg");
    }


}
