package com.appi.gstbill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appi.gstbill.R;
import com.appi.gstbill.dbhandler.DatabaseHandler;
import com.appi.gstbill.model.PurchaseModel;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.UserViewHolder> {

    Context context;
    ArrayList<PurchaseModel> puurchaselist;


    public BillAdapter(Context context, ArrayList<PurchaseModel> puurchaselist){
        this.context = context;
        this.puurchaselist = puurchaselist;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BillAdapter.UserViewHolder(LayoutInflater.from(context).inflate(R.layout.view_row_bill, null));
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bill_productname.setText(puurchaselist.get(position).getProductName());
        holder.bill_quantity.setText(puurchaselist.get(position).getPurchaseQuantity());
        holder.bill_price.setText(puurchaselist.get(position).getPurchaseTotalPrice());
        holder.bill_gst.setText(puurchaselist.get(position).getProductGSTPercent());
    }



    @Override
    public int getItemCount() {
        return puurchaselist.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView bill_productname, bill_quantity, bill_price, bill_gst;

        public UserViewHolder(View v)
        {
            super(v);
            bill_productname = v.findViewById(R.id.bill_productname);
            bill_quantity = v.findViewById(R.id.bill_quantity);
            bill_price = v.findViewById(R.id.bill_price);
            bill_gst = v.findViewById(R.id.bill_gst);
        }

    }
}
