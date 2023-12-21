package com.appi.gstbill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appi.gstbill.R;
import com.appi.gstbill.model.CanvasModel;
import com.appi.gstbill.model.PurchaseModel;

import java.util.ArrayList;

public class CanvasAdapter extends RecyclerView.Adapter<CanvasAdapter.UserViewHolder> {

    Context context;
    ArrayList<CanvasModel> canvasModelList;


    public CanvasAdapter(Context context, ArrayList<CanvasModel> canvasModelList){
        this.context = context;
        this.canvasModelList = canvasModelList;
    }


    @NonNull
    @Override
    public CanvasAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CanvasAdapter.UserViewHolder(LayoutInflater.from(context).inflate(R.layout.view_row_canvas_item, null));
    }


    @Override
    public void onBindViewHolder(@NonNull CanvasAdapter.UserViewHolder holder, int position) {
        holder.itemname.setText(canvasModelList.get(position).getProductName());
        holder.itemgst.setText(canvasModelList.get(position).getProductGSTPercent());
        holder.itemprice.setText(canvasModelList.get(position).getProductPrice());
        holder.itemquantity.setText(canvasModelList.get(position).getPurchaseQuantity());
        holder.itemtotalprice.setText(canvasModelList.get(position).getPurchaseTotalPrice());
    }



    @Override
    public int getItemCount() {
        return canvasModelList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView itemname, itemgst, itemprice, itemquantity,itemtotalprice;

        public UserViewHolder(View v)
        {
            super(v);
            itemname = v.findViewById(R.id.itemname);
            itemgst = v.findViewById(R.id.itemgst);
            itemprice = v.findViewById(R.id.itemprice);
            itemquantity = v.findViewById(R.id.itemquantity);
            itemtotalprice = v.findViewById(R.id.itemtotalprice);
        }

    }
}
