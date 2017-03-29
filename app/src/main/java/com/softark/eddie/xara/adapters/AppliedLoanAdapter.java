package com.softark.eddie.xara.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.softark.eddie.xara.listeners.Listener;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.Loan;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/14/2017.
 */

public class AppliedLoanAdapter extends RecyclerView.Adapter<AppliedLoanAdapter.ViewHolder> {

    private ArrayList<Loan> appliedLoans;
    private LayoutInflater layoutInflater;
    private Context context;
    FragmentManager fragmentManager;

    public AppliedLoanAdapter(Context context, FragmentManager fragmentManager, ArrayList<Loan> appliedLoans) {
        this.context = context;
        this.appliedLoans = appliedLoans;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemCount() {
        return appliedLoans.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.applied_loans_list, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AppliedLoanAdapter.ViewHolder holder, int position) {
        Loan appliedLoan = appliedLoans.get(position);
        holder.loanUser.setText(appliedLoan.getUser().getUserName());
        holder.loanType.setText(appliedLoan.getLoanType());
        holder.loanAmount.setText(String.valueOf(appliedLoan.getLoanAmount()));
        holder.loanReturnPeriod.setText(appliedLoan.getRepaymentPeriod() + " month(s)");
        holder.loanAppDay.setText(appliedLoan.getLoanAppDay());
        holder.loanAppMonth.setText(appliedLoan.getLoanAppMonth());
        holder.viewLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.loanUser.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView loanUser;
        public TextView loanType;
        public TextView loanAmount;
        public TextView loanReturnPeriod;
        public Button viewLoan;
        public TextView loanAppDay;
        public TextView loanAppMonth;

        public ViewHolder(View itemView) {
            super(itemView);

            loanAppDay = (TextView) itemView.findViewById(R.id.applied_loan_date);
            loanAppMonth = (TextView) itemView.findViewById(R.id.applied_loan_month);
            loanUser = (TextView) itemView.findViewById(R.id.loan_borrower);
            loanType = (TextView) itemView.findViewById(R.id.applied_loan_type);
            loanAmount = (TextView) itemView.findViewById(R.id.applied_loan_amount);
            loanReturnPeriod = (TextView) itemView.findViewById(R.id.applied_loan_deadline);
            viewLoan = (Button) itemView.findViewById(R.id.view_applied_button);
        }

    }



}
