package com.androidpillars.covid19.custom_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpillars.covid19.pojo.GetDistrictDetailResponse;
import com.androidpillars.covid19.pojo.GetParentDataDetailResponse;
import com.androidpillars.covid_19.R;

import java.util.List;

/**
 * Created by Gowtham.R on 2020-04-06.
 */
public class DistrictScreenAdapter extends RecyclerView.Adapter<DistrictScreenAdapter.ViewHolder> {

    List<GetDistrictDetailResponse.DistrictDatum> mValues;
    Context mContext;
    protected DistrictScreenAdapter.ItemListener mListener;

    public DistrictScreenAdapter(Context context, List<GetDistrictDetailResponse.DistrictDatum> values, DistrictScreenAdapter.ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView txtHeader;
        public TextView textConfirmed;
        public TextView textActive;
        public TextView textRecovered;
        public TextView textDeath;
        public LinearLayout relativeLayout;
        GetDistrictDetailResponse.DistrictDatum item;
        public RelativeLayout mRelativeLayout;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            txtHeader = v.findViewById(R.id.txttHeader);
            textConfirmed = v.findViewById(R.id.textConfirmed);
            textActive = v.findViewById(R.id.textActive);
            textActive.setVisibility(View.GONE);
            textRecovered = v.findViewById(R.id.textRecovered);
            textRecovered.setVisibility(View.GONE);
            textDeath = v.findViewById(R.id.textDeath);
            textDeath.setVisibility(View.GONE);
            relativeLayout = v.findViewById(R.id.relativeLayout);
            mRelativeLayout = v.findViewById(R.id.cardView);

        }

        public void setData(GetDistrictDetailResponse.DistrictDatum item) {
                this.item = item;
                txtHeader.setText(item.getDistrict());
                textConfirmed.setText("Confirmed - "+item.getConfirmed()+" "+"[+"+item.getDelta().getConfirmed()+"]");

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public DistrictScreenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.district_screen_adapter, parent, false);

        return new DistrictScreenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DistrictScreenAdapter.ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(GetDistrictDetailResponse.DistrictDatum item);
    }

}
