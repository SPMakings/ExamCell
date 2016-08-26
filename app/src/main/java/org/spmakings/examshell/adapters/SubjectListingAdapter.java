package org.spmakings.examshell.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.spmakings.examshell.Models.Subject;
import org.spmakings.examshell.R;
import org.spmakings.examshell.customview.AvenirHeavy;

import java.util.LinkedList;

/**
 * Created by apple on 17/07/16.
 */

public class SubjectListingAdapter extends RecyclerView.Adapter<SubjectListingAdapter.ViewHolder> {

    private LinkedList<Subject> mainArray;
    private Context mContext;
    private LayoutInflater inflater = null;
    private onItemClickListener callback = null;
    private int rowHight = 0;
    private Subject currentSelected = null;

    public SubjectListingAdapter(Context mContext, LinkedList<Subject> mainArray, float screenWidth) {
        super();
        this.mainArray = mainArray;
        this.mContext = mContext;
        rowHight = rowHight(screenWidth, 2.0f);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.items_subject_listing, parent, false);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rowHight));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {

            holder.subName.setText(mainArray.get(position).getSubName());

//================== Selection Management===
            if (currentSelected != null) {
                if (mainArray.indexOf(currentSelected) < 0) {
                    holder.afterSelectBox.setVisibility(View.GONE);
                } else {
                    holder.afterSelectBox.setVisibility(View.GONE);
                }
            } else {
                holder.afterSelectBox.setVisibility(View.GONE);
            }
//==========================================


            holder.selectionView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    if (currentSelected != null) {
//                        if (mainArray.indexOf(currentSelected) < 0) {
//                            currentSelected = mainArray.get(position);
//                        } else {
//                            currentSelected = null;
//                        }
//                    } else {
//                        currentSelected = mainArray.get(position);
//                    }

                    currentSelected = mainArray.get(position);
                    notifyDataSetChanged();
                }
            });


            holder.afterSelectBox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Working on....", Toast.LENGTH_SHORT).show();
                    //callback.onItemClickd(mainArray.get(position));
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mainArray.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        AvenirHeavy subName;
        View selectionView, afterSelectBox;


        public ViewHolder(View itemView) {
            super(itemView);
            selectionView = itemView;
            subName = (AvenirHeavy) itemView.findViewById(R.id.sub_name);
            afterSelectBox = itemView.findViewById(R.id.selected_box);

        }
    }


    public void setOnItemClickListener(onItemClickListener callback) {
        this.callback = callback;

    }

    public interface onItemClickListener {

        void onItemClickd(Subject data);

    }

    public int rowHight(final float screenWidth, final float noOfRows) {
        int hight = Math.round(screenWidth / noOfRows);
        return hight;
    }
}