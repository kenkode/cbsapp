package com.softark.eddie.xara.Listeners;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.softark.eddie.xara.AppliedLoanDetails;
import com.softark.eddie.xara.Dialogs.PayNowDialog;
import com.softark.eddie.xara.Dialogs.PaymentSummaryDialog;
import com.softark.eddie.xara.Dialogs.TopUpDialog;
import com.softark.eddie.xara.SummaryActivity;
import com.softark.eddie.xara.LoanActivity;
import com.softark.eddie.xara.LoanDetailsActivity;

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
        Intent intent = new Intent(context, LoanDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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

    public void login() {
        Intent intent = new Intent(context, SummaryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void appliedLoanDetails() {
        Intent intent = new Intent(context, AppliedLoanDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
