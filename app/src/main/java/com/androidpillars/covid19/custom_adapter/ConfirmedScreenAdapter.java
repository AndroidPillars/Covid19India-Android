package com.androidpillars.covid19.custom_adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpillars.covid19.pojo.GetParentDataDetailResponse;
import com.androidpillars.covid_19.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gowtham.R on 2020-04-06.
 */
public class ConfirmedScreenAdapter extends RecyclerView.Adapter<ConfirmedScreenAdapter.ViewHolder> {

    List<GetParentDataDetailResponse.Statewise> mValues;
    Context mContext;
    protected ConfirmedScreenAdapter.ItemListener mListener;

    public ConfirmedScreenAdapter(Context context, List<GetParentDataDetailResponse.Statewise> values, ConfirmedScreenAdapter.ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView txtHeader;
        public TextView textConfirmed;
        public TextView textActive;
        public TextView textRecovered;
        public TextView textDeath;
        public Button mViewDistrictWise;
        public LinearLayout relativeLayout;
        GetParentDataDetailResponse.Statewise item;
        public RelativeLayout mRelativeLayout;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            txtHeader = v.findViewById(R.id.txttHeader);
            textConfirmed = v.findViewById(R.id.textConfirmed);
            textActive = v.findViewById(R.id.textActive);
            textRecovered = v.findViewById(R.id.textRecovered);
            textDeath = v.findViewById(R.id.textDeath);
            relativeLayout = v.findViewById(R.id.relativeLayout);
            mRelativeLayout = v.findViewById(R.id.cardView);
            mViewDistrictWise = v.findViewById(R.id.btnDistrict);

        }

        public void setData(final GetParentDataDetailResponse.Statewise item) {
            this.item = item;
            txtHeader.setText(item.getState());
            textConfirmed.setText("Confirmed - " + item.getConfirmed() + " " + "[+" + item.getDeltaconfirmed() + "]");
            textActive.setText("Active - " + item.getActive());
            textRecovered.setText("Recovered - " + item.getRecovered() + " " + "[+" + item.getDeltarecovered() + "]");
            textDeath.setText("Death - " + item.getDeaths() + " " + "[+" + item.getDeltadeaths() + "]");

            mViewDistrictWise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemClick(item);
                    }
                }
            });
        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public ConfirmedScreenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.confirmed_screen_adapter, parent, false);

        return new ConfirmedScreenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConfirmedScreenAdapter.ViewHolder Vholder, int position) {

        Vholder.setData(mValues.get(position));


    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(GetParentDataDetailResponse.Statewise item);
    }

}
