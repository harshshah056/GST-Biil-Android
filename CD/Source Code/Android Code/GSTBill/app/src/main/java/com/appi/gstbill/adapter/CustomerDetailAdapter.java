package com.appi.gstbill.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appi.gstbill.R;
import com.appi.gstbill.dbhandler.DatabaseHandler;
import com.appi.gstbill.model.CustomerModel;

import java.util.ArrayList;

public class CustomerDetailAdapter extends RecyclerView.Adapter<CustomerDetailAdapter.UserViewHolder> {

    Context context;
    ArrayList<CustomerModel> customerlist;
    DatabaseHandler dbhandler;
    int a;

    OnDeleteUser onDeleteUser;

    public CustomerDetailAdapter(Context context, ArrayList<CustomerModel> customerlist, OnDeleteUser onDeleteUser){
        this.context = context;
        this.customerlist = customerlist;
        dbhandler = new DatabaseHandler(context);
        this.onDeleteUser = onDeleteUser;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.view_row_customer, null));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tv_customername.setText(customerlist.get(position).getCustomerName());
        holder.tv_customercity.setText(customerlist.get(position).getCustomerCIty());
        holder.tv_customernumber.setText(customerlist.get(position).getCustomerContactNumber());
        String b = customerlist.get(position).getCustomerName();
        System.out.println(b);
        int x = Integer.parseInt(String.valueOf(customerlist.get(position).getCustomerID()));
        System.out.println(x);
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int a = customerlist.get(position).getCustomerID();

                                if(dbhandler.deletecustomer(customerlist.get(position).getCustomerID()) > 0)
                                {
                                    if (onDeleteUser != null)
                                    {
                                        onDeleteUser.onDeleteUser();
                                    }
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }

        });

    }

    public interface OnDeleteUser{
        void onDeleteUser();
    }

    @Override
    public int getItemCount(){
        return customerlist.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView tv_customername, tv_customercity, tv_customernumber;
        ImageView iv_delete;

        public UserViewHolder(View v)
        {
            super(v);
            tv_customername = v.findViewById(R.id.tv_customername);
            tv_customercity = v.findViewById(R.id.tv_customercity);
            tv_customernumber = v.findViewById(R.id.tv_customernumber);
            iv_delete = v.findViewById(R.id.iv_delete);
        }

    }
}
