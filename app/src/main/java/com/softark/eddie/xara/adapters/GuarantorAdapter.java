package com.softark.eddie.xara.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/10/2017.
 */

public class GuarantorAdapter extends BaseAdapter {

    private ArrayList<User> guarantors;
    private LayoutInflater  layoutInflater;

    public GuarantorAdapter(Context context, ArrayList<User> guarantors) {
        this.guarantors = guarantors;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return guarantors.size();
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
            view = layoutInflater.inflate(R.layout.guarantors_list, null);
        }

        User guarantor = guarantors.get(position);

        TextView guarantorName = (TextView) view.findViewById(R.id.guarantor_name);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.guarantor_check);
        guarantorName.setText(guarantor.getUserName());

        return view;
    }
}



