package com.softark.eddie.xara.Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.softark.eddie.xara.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/8/2017.
 */

public class MemberListView extends BaseAdapter {

    private Context context;
    private ArrayList<HashMap<String, String>> userDetails;
    private static LayoutInflater layoutInflater = null;

    public MemberListView(Context context, ArrayList<HashMap<String, String>> userDetails) {
        this.context = context;
        this.userDetails = userDetails;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return userDetails.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.accounts_list, null);
        }

        TextView username = (TextView) view.findViewById(R.id.username);
        TextView rank = (TextView) view.findViewById(R.id.position);
        Button actAdmin = (Button) view.findViewById(R.id.action);

        HashMap<String, String> user = userDetails.get(position);

        username.setText(user.get("username").toString());
        rank.setText(user.get("rank").toString());

        return view;
    }
}
