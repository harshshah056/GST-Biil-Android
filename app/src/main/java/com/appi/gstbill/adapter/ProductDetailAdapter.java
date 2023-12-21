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
import com.appi.gstbill.model.ProductModel;

import java.util.ArrayList;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.UserViewHolder> {
    Context context;
    ArrayList<ProductModel> productlist;
    DatabaseHandler dbhandler;
    OnDeleteProduct onDeleteProduct;

    public ProductDetailAdapter(Context context, ArrayList<ProductModel> productlist, ProductDetailAdapter.OnDeleteProduct onDeleteProduct){
        this.context = context;
        this.productlist = productlist;
        dbhandler = new DatabaseHandler(context);
        this.onDeleteProduct = onDeleteProduct;
    }
    @NonNull
    @Override
    public ProductDetailAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductDetailAdapter.UserViewHolder(LayoutInflater.from(context).inflate(R.layout.view_row_product, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailAdapter.UserViewHolder holder, int position) {
        holder.tv_productname.setText(productlist.get(position).getProductName());
        holder.tv_productprice.setText(productlist.get(position).getProductPrice());
        holder.tv_productqty.setText(productlist.get(position).getAvailableQuantity());
        holder.tv_producttax.setText(productlist.get(position).getProductGSTPercent());
        String b = productlist.get(position).getProductName();
        System.out.println(b);
        int x = Integer.parseInt(String.valueOf(productlist.get(position).getProductID()));
        System.out.println(x);
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(dbhandler.deleproduct(Integer.parseInt(productlist.get(position).getProductID())) > 0)
                                {
                                    if (onDeleteProduct != null)
                                    {
                                        onDeleteProduct.onDeleteProduct();
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

    @Override
    public int getItemCount() {
        return productlist.size();
    }

    public interface OnDeleteProduct {
        void onDeleteProduct();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tv_productname, tv_producttax, tv_productprice, tv_productqty;
        ImageView iv_delete;

        public UserViewHolder(View v)
        {
            super(v);
            tv_productname = v.findViewById(R.id.tv_productname);
            tv_producttax = v.findViewById(R.id.tv_producttax);
            tv_productqty = v.findViewById(R.id.tv_productqty);
            tv_productprice = v.findViewById(R.id.tv_productprice);
            iv_delete = v.findViewById(R.id.iv_delete);
        }

    }
}
