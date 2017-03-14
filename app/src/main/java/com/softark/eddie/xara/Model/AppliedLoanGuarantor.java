package com.softark.eddie.xara.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softark.eddie.xara.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eddie on 3/14/2017.
 */

public class AppliedLoanGuarantor extends BaseAdapter {

    private Context context;
    private ArrayList<HashMap<String, String>> guarantors;
    private LayoutInflater layoutInflater;

    public AppliedLoanGuarantor(Context context, ArrayList<HashMap<String, String>> guarantors) {
        this.context = context;
        this.guarantors = guarantors;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return guarantors.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.applied_loans_guarantor, null);
        }

        TextView guarantorName = (TextView) view.findViewById(R.id.applied_loan_guarantor_name);
        TextView guarantorApproval = (TextView) view.findViewById(R.id.applied_loan_guarantor_appr);

        HashMap<String, String> guarantor = guarantors.get(position);

        guarantorName.setText(guarantor.get("name"));
        guarantorApproval.setText(guarantor.get("state"));

        return view;

    }
}
