package com.softark.eddie.xara.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.softark.eddie.xara.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/10/2017.
 */

public class LoanListView extends BaseAdapter {

    private ArrayList<HashMap<String, String>> loans;
    LayoutInflater layoutInflater;

    public LoanListView(Context context, ArrayList<HashMap<String, String>> loans) {
        this.loans = loans;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return loans.size();
    }

    @Override
    public Object getItem(int position) {
        return loans.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.loan_list, null);
        }

        TextView dateText = (TextView) view.findViewById(R.id.loan_date);
        TextView monthText = (TextView) view.findViewById(R.id.loan_month);
        TextView loanType = (TextView) view.findViewById(R.id.loan_type_name);
        TextView loanStatus = (TextView) view.findViewById(R.id.loan_status);
        TextView loanInterest = (TextView) view.findViewById(R.id.loan_interest);
        Button topUpButton= (Button) view.findViewById(R.id.top_up_button);

        HashMap<String, String> loan = loans.get(position);

        dateText.setText(loan.get("date").toString());
        monthText.setText(loan.get("month").toString());
        loanType.setText(loan.get("loan").toString());
        loanStatus.setText(loan.get("status").toString());
        loanInterest.setText(loan.get("interest").toString());

        return view;
    }
}
