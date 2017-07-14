package com.softark.eddie.xara.listeners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.softark.eddie.xara.activities.AppliedLoanDetails;
import com.softark.eddie.xara.dialogs.PayNowDialog;
import com.softark.eddie.xara.dialogs.PaymentSummaryDialog;
import com.softark.eddie.xara.dialogs.TopUpDialog;
import com.softark.eddie.xara.activities.SummaryActivity;
import com.softark.eddie.xara.activities.LoanActivity;
import com.softark.eddie.xara.activities.SavingsActivity;
import com.softark.eddie.xara.activities.LoanDetailsActivity;
import com.softark.eddie.xara.model.User;

/**
 * Created by Eddie on 3/10/2017.
 */

public class ListenerAction {

    Context context;
    FragmentManager fragmentManager;

    public ListenerAction(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public void topUp() {
        TopUpDialog topUpDialog = new TopUpDialog();
        topUpDialog.show(fragmentManager, "TopUpDialog");
    }

    public void toLoanDetails() {

    }

    public void confirmLoanApplication() {
        PaymentSummaryDialog dialog = new PaymentSummaryDialog();
        dialog.show(fragmentManager, "PaymentConfirm");
    }

    public void payNow() {
        PayNowDialog dialog = new PayNowDialog();
        dialog.show(fragmentManager, "PayDialog");
    }

    public void toLoans() {
        Intent intent = new Intent(context, LoanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void appliedLoanDetails() {
        Intent intent = new Intent(context, AppliedLoanDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void savingsDetails(){
        Intent intent = new Intent(context, SavingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
