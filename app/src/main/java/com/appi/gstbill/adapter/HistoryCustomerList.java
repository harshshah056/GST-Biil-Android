package com.appi.gstbill.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appi.gstbill.activity.HistoryDetail;
import com.appi.gstbill.R;
import com.appi.gstbill.dbhandler.DatabaseHandler;
import com.appi.gstbill.model.BillCustomerModl;
import com.appi.gstbill.model.HistoryModel;

import java.util.ArrayList;

public class HistoryCustomerList extends RecyclerView.Adapter<HistoryCustomerList.UserViewHolder>{

    Context context;
    ArrayList<HistoryModel> customernamelist;
    DatabaseHandler dbhandler;

    public HistoryCustomerList(Context context, ArrayList<HistoryModel> customernamelist){
        this.context = context;
        this.customernamelist = customernamelist;
        dbhandler = new DatabaseHandler(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryCustomerList.UserViewHolder(LayoutInflater.from(context).inflate(R.layout.view_row_history_customer_name, null));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tv_history_customername.setText(customernamelist.get(position).getCustomerName());
        holder.tv_history_date.setText(customernamelist.get(position).getBillDate());
        holder.tv_history_time.setText(customernamelist.get(position).getBillTime());
        holder.histry_cust_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String a = String.valueOf(customernamelist.get(position).getBillID());
                Intent in = new Intent(context, HistoryDetail.class);
                in.putExtra("id",a);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customernamelist.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tv_history_customername, tv_history_date, tv_history_time;
        LinearLayout histry_cust_layout;
        public UserViewHolder(@NonNull View v) {
            super(v);
            tv_history_customername = v.findViewById(R.id.tv_history_customername);
            tv_history_date = v.findViewById(R.id.tv_history_date);
            tv_history_time = v.findViewById(R.id.tv_history_time);
            histry_cust_layout = v.findViewById(R.id.histry_cust_layout);
        }
    }
}
