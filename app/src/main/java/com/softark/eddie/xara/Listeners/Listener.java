package com.softark.eddie.xara.listeners;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;

import com.softark.eddie.xara.R;

/**
 * Created by Eddie on 3/10/2017.
 */

public class Listener implements View.OnClickListener, View.OnTouchListener {

    private Context context;
    private ListenerAction listenerAction;
    FragmentManager fragmentManager;

    public Listener(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        listenerAction = new ListenerAction(context, fragmentManager);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.top_up_button:
                listenerAction.topUp();
                break;
            case R.id.loan_bp:
                listenerAction.toLoanDetails();
                break;
            case R.id.apply_button:
                listenerAction.confirmLoanApplication();
                break;
            case R.id.pay_now_button:
                listenerAction.payNow();
                break;
            case R.id.loans_button:
                listenerAction.toLoans();
                break;
            case R.id.view_applied_button:
                listenerAction.appliedLoanDetails();
                break;
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
