package com.appi.gstbill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appi.gstbill.R;
import com.appi.gstbill.model.ProductModel;

import java.util.ArrayList;

public class ProductListAdapter extends BaseAdapter {

    ArrayList<ProductModel> productlist;
    Context context;

    public ProductListAdapter (Context context, ArrayList<ProductModel> productlist){
        this.productlist = productlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productlist.size();
    }

    @Override
    public Object getItem(int position) {
        return productlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_row_product_name, null);
        TextView tvproductname = v.findViewById(R.id.tvproductname);
        tvproductname.setText(productlist.get(position).getProductName());
        return v;
    }
}
