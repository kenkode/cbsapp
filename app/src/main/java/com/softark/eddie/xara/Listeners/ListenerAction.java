package com.softark.eddie.xara.Listeners;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by Eddie on 3/10/2017.
 */

public class ListenerAction {

    Context context;

    public ListenerAction(Context context) {
        this.context = context;
    }

    public void topUp() {
        Toast.makeText(context, "This is a positive", Toast.LENGTH_LONG).show();
        return;
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("THis");
//        builder.setMessage("This is a message");
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(context, "This is a toast", Toast.LENGTH_LONG).show();
//            }
//        }).
//        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(context, "This is a positive", Toast.LENGTH_LONG).show();
//            }
//        });
//        builder.create();
    }

}