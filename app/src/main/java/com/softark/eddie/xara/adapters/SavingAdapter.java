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
import com.softark.eddie.xara.model.Constant;
import com.softark.eddie.xara.model.Loan;
import com.softark.eddie.xara.model.Saving;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/10/2017.
 */

public class SavingAdapter extends RecyclerView.Adapter<SavingAdapter.ViewHolder> {

    private ArrayList<Saving> savings;
    private LayoutInflater layoutInflater;
    private Context context;
    private FragmentManager fragmentManager;

    public SavingAdapter(Context context, FragmentManager fragmentManager, ArrayList<Saving> savings) {
        this.savings = savings;
        this.context = context;
        this.fragmentManager = fragmentManager;
        layoutInflater =  LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.saving_list, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SavingAdapter.ViewHolder holder, int position) {
        final Saving mySaving = savings.get(position);

        holder.dateText.setText(mySaving.getSavingAppDay());
        holder.monthText.setText(mySaving.getSavingAppMonth());
        holder.savingType.setText(mySaving.getProduct());
        if(mySaving.getTransaction().equals("credit")) {
            holder.transaction.setText("Deposit");
        }else {
            holder.transaction.setText("Withdrawal");
        }
        holder.amount.setText(mySaving.getCurrency()+" "+mySaving.getSavingAmount());

//        Progressbar

        int progress = 5;

        holder.progressBar.setProgress(progress);



        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoanDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constant.LOAN, mySaving);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return savings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView dateText;
        public TextView monthText;
        public TextView savingType;
        public TextView transaction;
        public TextView amount;
        public Button transact;
        public RelativeLayout savingRelativeLayout;
        public ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            dateText = (TextView) itemView.findViewById(R.id.saving_date);
            monthText = (TextView) itemView.findViewById(R.id.saving_month);
            savingType = (TextView) itemView.findViewById(R.id.saving_type_name);
            transaction = (TextView) itemView.findViewById(R.id.transaction);
            amount = (TextView) itemView.findViewById(R.id.amount);
            progressBar= (ProgressBar) itemView.findViewById(R.id.saving_time_elapsed);
        }
    }
}
