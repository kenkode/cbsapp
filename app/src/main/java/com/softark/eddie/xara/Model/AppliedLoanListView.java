package com.softark.eddie.xara.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.softark.eddie.xara.Listeners.Listener;
import com.softark.eddie.xara.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/14/2017.
 */

public class AppliedLoanListView extends BaseAdapter {

    private ArrayList<HashMap<String, String>> appliedLoans;
    private LayoutInflater layoutInflater;
    private Context context;
    FragmentManager fragmentManager;

    public AppliedLoanListView(Context context, FragmentManager fragmentManager, ArrayList<HashMap<String, String>> appliedLoans) {
        this.context = context;
        this.appliedLoans = appliedLoans;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return this.appliedLoans.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return appliedLoans.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.applied_loans_list, null);
        }

        TextView appliedLoanDate = (TextView) view.findViewById(R.id.applied_loan_date);
        TextView appliedMonth = (TextView) view.findViewById(R.id.applied_loan_month);
        TextView loanBorrower = (TextView) view.findViewById(R.id.loan_borrower);
        TextView loanType = (TextView) view.findViewById(R.id.applied_loan_type);
        TextView loanAmount = (TextView) view.findViewById(R.id.applied_loan_amount);
        TextView loanDeadline= (TextView) view.findViewById(R.id.applied_loan_deadline);
        Button viewLoan = (Button) view.findViewById(R.id.view_applied_button);

        HashMap<String, String> appliedLoan = this.appliedLoans.get(position);
        appliedLoanDate.setText(appliedLoan.get("date"));
        appliedMonth.setText(appliedLoan.get("month"));
        loanBorrower.setText(appliedLoan.get("borrower"));
        loanType.setText(appliedLoan.get("type"));
        loanAmount.setText(appliedLoan.get("amount"));
        loanDeadline.setText(appliedLoan.get("deadline"));

        viewLoan.setOnClickListener(new Listener(context, fragmentManager));

        return view;

    }
}
