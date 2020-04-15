package com.androidpillars.covid19.custom_adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpillars.covid19.pojo.GetDistrictDetailResponse;
import com.androidpillars.covid19.pojo.GetUpdateListResponse;
import com.androidpillars.covid_19.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gowtham.R on 2020-04-06.
 */
public class UpdateLogsScreenAdapter extends RecyclerView.Adapter<UpdateLogsScreenAdapter.ViewHolder> {

    List<GetUpdateListResponse> mValues;
    Context mContext;
    protected UpdateLogsScreenAdapter.ItemListener mListener;

    public UpdateLogsScreenAdapter(Context context, List<GetUpdateListResponse> values, UpdateLogsScreenAdapter.ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView txtHeader;
        public TextView textConfirmed;
        public LinearLayout relativeLayout;
        GetUpdateListResponse item;
        public RelativeLayout mRelativeLayout;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            txtHeader = v.findViewById(R.id.txttHeader);
            textConfirmed = v.findViewById(R.id.textConfirmed);
            relativeLayout = v.findViewById(R.id.relativeLayout);
            mRelativeLayout = v.findViewById(R.id.cardView);

        }

        public void setData(GetUpdateListResponse item) {
            this.item = item;
            txtHeader.setText(item.getUpdate());
            String mDate = getDate(item.getTimestamp());
            String mFilterTime = covertTimeToText(mDate);
            textConfirmed.setText(mFilterTime);

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public UpdateLogsScreenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.update_logs_screen_adapter, parent, false);

        return new UpdateLogsScreenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UpdateLogsScreenAdapter.ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {

        if (mValues.size() >= 10) {
            return 10;
        } else {
            return mValues.size();
        }

    }

    public interface ItemListener {
        void onItemClick(GetUpdateListResponse item);
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("yyyy-MM-dd'T'HH:mm:ss", cal).toString();
        return date;
    }

    public String covertTimeToText(String dataDate) {

        String convTime = null;

        String prefix = "";
        String suffix = "Ago";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date pasTime = dateFormat.parse(dataDate);

            Date nowTime = new Date();

            long dateDiff = nowTime.getTime() - pasTime.getTime();

            long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
            long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
            long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
            long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

            if (second < 60) {
                convTime = second + " Seconds " + suffix;
            } else if (minute < 60) {
                convTime = minute + " Minutes " + suffix;
            } else if (hour < 24) {
                convTime = hour + " Hours " + suffix;
            } else if (day >= 7) {
                if (day > 360) {
                    convTime = (day / 30) + " Years " + suffix;
                } else if (day > 30) {
                    convTime = (day / 360) + " Months " + suffix;
                } else {
                    convTime = (day / 7) + " Week " + suffix;
                }
            } else if (day < 7) {
                convTime = day + " Days " + suffix;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("ConvTimeE", e.getMessage());
        }

        return convTime;
    }

}
