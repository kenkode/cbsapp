package com.softark.eddie.xara.Listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.softark.eddie.xara.Dialogs.PayNowDialog;
import com.softark.eddie.xara.Dialogs.PaymentSummaryDialog;
import com.softark.eddie.xara.Dialogs.TopUpDialog;
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
}
