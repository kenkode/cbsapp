package com.softark.eddie.xara.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.softark.eddie.xara.R;

/**
 * Created by Eddie on 3/14/2017.
 */

public class PayNowDialog extends DialogFragment {
    private AlertDialog.Builder dialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.pay_dialog, null);
        dialog.setView(view);
        dialog.setPositiveButton("Confirm", null)
                .setNegativeButton("Cancel", null);

        return dialog.create();
    }
}
