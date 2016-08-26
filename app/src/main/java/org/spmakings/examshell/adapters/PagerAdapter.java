package org.spmakings.examshell.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.spmakings.examshell.Models.Subject;
import org.spmakings.examshell.R;
import org.spmakings.examshell.customview.AvenirRegular;

import java.util.LinkedList;


/**
 * Created by apple on 05/05/16.
 */

public class PagerAdapter extends android.support.v4.view.PagerAdapter {


    LinkedList<Subject> subName;
    LinkedList<String> images;
    Context mContext = null;

    public PagerAdapter(Context mContext, LinkedList<Subject> subName, LinkedList<String> images) {
        super();
        this.subName = subName;
        this.images = images;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return subName.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.items_pager_subjects, null);

        ((AvenirRegular) view.findViewById(R.id.sub_name_main)).setText(subName.get(position).getSubName());
        Picasso.with(mContext).load(images.get(position)).fit().centerCrop().into((ImageView) view.findViewById(R.id.sub_main_image));

        Log.i("landingPager", images.get(position));
        // Glide.with(mContext).load("http://images.designtrends.com/wp-content/uploads/2016/03/16033524/cityscapes-cities-architecture-buildings-skyscrapers-night-lights-hdr-wallpaper-1.jpg").centerCrop().into((ImageView) view.findViewById(R.id.header_image));
//        Picasso.with(mContext).load(imageList[position]).fit().centerCrop().into((ImageView) view.findViewById(R.id.header_image));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ViewGroup) object);
    }


}