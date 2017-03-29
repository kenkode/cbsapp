package com.softark.eddie.xara.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.softark.eddie.xara.R;
import com.softark.eddie.xara.helpers.SessionManager;
import com.softark.eddie.xara.model.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/8/2017.
 */

public class AccountsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> userDetails;
    SessionManager session;
    private static LayoutInflater layoutInflater = null;

    public AccountsAdapter(Context context, ArrayList<User> userDetails) {
        this.context = context;
        session = new SessionManager(context);
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

        User user = userDetails.get(position);

        if(session.getUser().get(SessionManager.USER_TYPE).equals("member")) {
            actAdmin.setVisibility(View.INVISIBLE);
        }

        username.setText(user.getUserName());
        rank.setText(user.getUserType());

        return view;
    }
}
