package com.softark.eddie.xara.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.softark.eddie.xara.R;

/**
 * Created by Eddie on 3/13/2017.
 */

public class PaymentSummaryDialog extends DialogFragment {
    private AlertDialog.Builder dialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Payment summary");
        LayoutInflater inflater = getLayoutInflater(savedInstanceState);
        View view = inflater.inflate(R.layout.payment_summary_layout, null);
        dialog.setView(view);

        return dialog.create();
    }
}
