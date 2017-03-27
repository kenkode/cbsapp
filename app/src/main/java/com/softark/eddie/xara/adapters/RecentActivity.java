package com.softark.eddie.xara.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softark.eddie.xara.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/27/2017.
 */

public class RecentActivity extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    ArrayList<HashMap<String, String>> activities;

    public RecentActivity(Context context, ArrayList<HashMap<String, String>> activities) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.activities = activities;
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.recent_activity, null);
        }

        TextView detail = (TextView) view.findViewById(R.id.activity_details);
        TextView activity_date = (TextView) view.findViewById(R.id.activity_date);
        TextView amount = (TextView) view.findViewById(R.id.activity_amount);
        TextView transaction = (TextView) view.findViewById(R.id.transaction_no);

        HashMap<String, String> activity = activities.get(position);
        detail.setText(activity.get("detail"));
        activity_date.setText(activity.get("date"));
        if(activity.get("type").equals("credit")) {
            amount.setTextColor(context.getResources().getColor(R.color.colorRedAccent));
        }else {
            amount.setTextColor(context.getResources().getColor(R.color.colorGreenAccent));
        }
        amount.setText(activity.get("amount"));
        if(activity.get("transaction_no") != "null"){
            transaction.setText(activity.get("transaction_no"));
        }else {
            transaction.setText("Transaction");
        }

        return view;
    }
}
