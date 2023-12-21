package com.appi.gstbill.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appi.gstbill.R;
import com.appi.gstbill.adapter.CustomerDetailAdapter;
import com.appi.gstbill.dbhandler.DatabaseHandler;
import com.appi.gstbill.model.CustomerModel;

import java.util.ArrayList;

public class customer_detail extends AppCompatActivity {

    RecyclerView rcuser;
    DatabaseHandler databaseHandler ;
    ArrayList<CustomerModel> customerlist = new ArrayList<>();
    ArrayList<CustomerModel> tempcustomerlist = new ArrayList<>();
    CustomerDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        setTitle("Customer Details");

        databaseHandler = new DatabaseHandler(this);

        rcuser = findViewById(R.id.list_customer);

        customerlist = databaseHandler.getCustomerdetail();
        tempcustomerlist.addAll(databaseHandler.getCustomerdetail());

        rcuser.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomerDetailAdapter(this, tempcustomerlist, new CustomerDetailAdapter.OnDeleteUser() {
            @Override
            public void onDeleteUser() {
                customerlist.clear();
                tempcustomerlist.clear();
                customerlist = databaseHandler.getCustomerdetail();
                tempcustomerlist.addAll(customerlist);
                adapter.notifyDataSetChanged();
            }
        });
        rcuser.setAdapter(adapter);
    }
}