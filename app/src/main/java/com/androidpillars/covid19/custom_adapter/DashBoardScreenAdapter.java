package com.androidpillars.covid19.custom_adapter;

/**
 * Created by Gowtham.R on 2020-04-05.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpillars.covid19.Model.DashBoardScreenModel;
import com.androidpillars.covid_19.R;

import java.util.ArrayList;


public class DashBoardScreenAdapter extends RecyclerView.Adapter<DashBoardScreenAdapter.ViewHolder> {

    ArrayList<DashBoardScreenModel> mValues;
    Context mContext;
    protected ItemListener mListener;

    public DashBoardScreenAdapter(Context context, ArrayList<DashBoardScreenModel> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public TextView imageView;
        public RelativeLayout relativeLayout;
        DashBoardScreenModel item;
        public CardView mCardView;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = v.findViewById(R.id.textView);
            imageView = v.findViewById(R.id.txttHeader);
            relativeLayout = v.findViewById(R.id.relativeLayout);
            mCardView = v.findViewById(R.id.cardView);
        }

        public void setData(DashBoardScreenModel item) {
            this.item = item;

            textView.setText(item.text);
            imageView.setText(item.desc);
            mCardView.setCardBackgroundColor(Color.parseColor(item.color));

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public DashBoardScreenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.dash_board_screen_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position ));

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(DashBoardScreenModel item);
    }
}
