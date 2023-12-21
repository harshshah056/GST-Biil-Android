package com.appi.gstbill.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.appi.gstbill.R;
import com.appi.gstbill.adapter.CustomerDetailAdapter;
import com.appi.gstbill.adapter.ProductDetailAdapter;
import com.appi.gstbill.adapter.ProductListAdapter;
import com.appi.gstbill.dbhandler.DatabaseHandler;
import com.appi.gstbill.model.CustomerModel;
import com.appi.gstbill.model.ProductModel;

import java.util.ArrayList;

public class product_details extends AppCompatActivity {
    RecyclerView rcproduct;
    DatabaseHandler databaseHandler ;
    ArrayList<ProductModel> productlist = new ArrayList<>();
    ArrayList<ProductModel> tempproductlist = new ArrayList<>();
    ProductDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        databaseHandler = new DatabaseHandler(this);

        rcproduct = findViewById(R.id.list_product);

        productlist = databaseHandler.getProductdetail();
        tempproductlist.addAll(databaseHandler.getProductdetail());

        rcproduct.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new ProductDetailAdapter(this, tempproductlist, new ProductDetailAdapter.OnDeleteProduct() {
            @Override
            public void onDeleteProduct() {
                productlist.clear();
                tempproductlist.clear();
                productlist = databaseHandler.getProductdetail();
                tempproductlist.addAll(productlist);
                adapter.notifyDataSetChanged();
            }

        });
        rcproduct.setAdapter(adapter);
    }
}