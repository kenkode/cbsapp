package com.softark.eddie.xara.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.softark.eddie.xara.activities.LoanDetailsActivity;
import com.softark.eddie.xara.dialogs.TopUpDialog;
import com.softark.eddie.xara.listeners.Listener;
import com.softark.eddie.xara.R;
import com.softark.eddie.xara.model.Loan;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/10/2017.
 */

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.ViewHolder> {

    public static final String LOAN = "loan";
    private ArrayList<Loan> loans;
    private LayoutInflater layoutInflater;
    private Context context;
    private FragmentManager fragmentManager;

    public LoanAdapter(Context context, FragmentManager fragmentManager, ArrayList<Loan> loans) {
        this.loans = loans;
        this.context = context;
        this.fragmentManager = fragmentManager;
        layoutInflater =  LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.loan_list, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LoanAdapter.ViewHolder holder, int position) {
        final Loan myLoan = loans.get(position);

        holder.dateText.setText(myLoan.getLoanAppDay());
        holder.monthText.setText(myLoan.getLoanAppMonth());
        holder.loanType.setText(myLoan.getLoanType());
        if(myLoan.getLoanStatus() == 1) {
            holder.loanStatus.setText("Approved");
        }else {
            holder.loanStatus.setText("Pending");
        }
        holder.loanInterest.setText(myLoan.getLoanInterest() + "%");

//        Progressbar
        DateTime startDate = new DateTime(myLoan.getLoanStartDate());
        int months = Months.monthsBetween(startDate, new DateTime()).getMonths();
        double totalMonths = Double.parseDouble(myLoan.getRepaymentPeriod());
        double elapsedPerc = (months / totalMonths) * 100;
        int progress = (int) elapsedPerc;

        myLoan.setPeriodElapsed(months);
        myLoan.setRemainingPeriod((int) totalMonths - months);
        holder.progressBar.setProgress(progress);

        holder.topUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUpDialog topUpDialog = new TopUpDialog();
                topUpDialog.show(fragmentManager, "TopUpDialog");
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoanDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(LOAN, myLoan);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView dateText;
        public TextView monthText;
        public TextView loanType;
        public TextView loanStatus;
        public TextView loanInterest;
        public Button topUpButton;
        public RelativeLayout loanRelativeLayout;
        public ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            dateText = (TextView) itemView.findViewById(R.id.loan_date);
            monthText = (TextView) itemView.findViewById(R.id.loan_month);
            loanType = (TextView) itemView.findViewById(R.id.loan_type_name);
            loanStatus = (TextView) itemView.findViewById(R.id.loan_status);
            loanInterest = (TextView) itemView.findViewById(R.id.loan_interest);
            topUpButton= (Button) itemView.findViewById(R.id.top_up_button);
            progressBar= (ProgressBar) itemView.findViewById(R.id.loan_time_elapsed);
        }
    }
}
