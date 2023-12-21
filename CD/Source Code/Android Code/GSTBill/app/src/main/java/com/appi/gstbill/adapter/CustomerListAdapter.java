package com.appi.gstbill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appi.gstbill.R;
import com.appi.gstbill.model.CustomerModel;

import java.util.ArrayList;

public class CustomerListAdapter extends BaseAdapter  {
    ArrayList<CustomerModel> customerlist;
    Context context;

    public CustomerListAdapter (Context context, ArrayList<CustomerModel> customerlist){
        this.customerlist = customerlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return customerlist.size();
    }

    @Override
    public Object getItem(int position) {
        return customerlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_row_customer_name, null);
        TextView tvcustomername = v.findViewById(R.id.tvcustomername);
        tvcustomername.setText(customerlist.get(position).getCustomerName());
        return v;
    }

}
