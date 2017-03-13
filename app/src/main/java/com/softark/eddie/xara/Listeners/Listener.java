package com.softark.eddie.xara.Listeners;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.softark.eddie.xara.R;

/**
 * Created by Eddie on 3/10/2017.
 */

public class Listener implements View.OnClickListener, View.OnTouchListener {

    private Context context;
    private ListenerAction listenerAction;

    public Listener(Context context) {
        this.context = context;
        listenerAction = new ListenerAction(context);
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
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
