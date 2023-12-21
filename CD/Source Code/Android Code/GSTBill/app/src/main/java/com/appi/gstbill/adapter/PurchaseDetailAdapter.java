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
import com.appi.gstbill.model.PurchaseModel;

import java.util.ArrayList;

public class PurchaseDetailAdapter extends RecyclerView.Adapter<PurchaseDetailAdapter.UserViewHolder>{

    Context context;
    ArrayList<PurchaseModel> purchaselist;
    DatabaseHandler dbhandler;
    PurchaseDetailAdapter.OnDeletePurchase onDeletePurchase;
    int x = 0;
    public static int ddd;

    public PurchaseDetailAdapter(Context context, ArrayList<PurchaseModel> purchaselist, PurchaseDetailAdapter.OnDeletePurchase onDeletePurchase){
        this.context = context;
        this.purchaselist = purchaselist;
        dbhandler = new DatabaseHandler(context);
        this.onDeletePurchase = onDeletePurchase;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PurchaseDetailAdapter.UserViewHolder(LayoutInflater.from(context).inflate(R.layout.view_row_purchase_detail, null));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.purchase_cProductname.setText(purchaselist.get(position).getProductName());
        holder.purchase_cQuantity.setText(purchaselist.get(position).getPurchaseQuantity());
        holder.purchase_cPrice.setText(purchaselist.get(position).getPurchaseTotalPrice());
        holder.purchase_cGst.setText(purchaselist.get(position).getProductGSTPercent());
        holder.purchase_cdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                x = 1;
                                int a = purchaselist.get(position).getPurchaseID();

                                if(dbhandler.deletepurchase(purchaselist.get(position).getPurchaseID()) > 0)
                                {
                                    if (onDeletePurchase != null)
                                    {
                                        onDeletePurchase.onDeletePurchase();
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

    public interface OnDeletePurchase{
        void onDeletePurchase();
    }

    @Override
    public int getItemCount() {
        return purchaselist.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView purchase_cProductname, purchase_cQuantity, purchase_cPrice, purchase_cGst,id,temp;
        ImageView purchase_cdelete;

        public UserViewHolder(@NonNull View v) {
            super(v);
            purchase_cProductname = v.findViewById(R.id.purchase_cProductname);
            purchase_cQuantity = v.findViewById(R.id.purchase_cQuantity);
            purchase_cPrice = v.findViewById(R.id.purchase_cPrice);
            purchase_cGst = v.findViewById(R.id.purchase_cGst);
            purchase_cdelete = v.findViewById(R.id.iv_purchase_delete);
        }
    }
}
