package com.softark.eddie.xara.Requests;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Eddie on 3/27/2017.
 */

public class XSingleton {
//    private
    private static Context mContext;
    private static XSingleton xRequestQueue;
    private RequestQueue requestQueue;

    private XSingleton(Context context) {
        mContext = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized XSingleton getInstance(Context context) {
        if(xRequestQueue == null) {
            xRequestQueue = new XSingleton(context);
        }
        return xRequestQueue;
    }

    public<T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }


}
