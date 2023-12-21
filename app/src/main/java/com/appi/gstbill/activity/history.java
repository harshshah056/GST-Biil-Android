package com.appi.gstbill.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appi.gstbill.R;
import com.appi.gstbill.adapter.CustomerDetailAdapter;
import com.appi.gstbill.adapter.HistoryCustomerList;
import com.appi.gstbill.adapter.HistoryDetailAdapter;
import com.appi.gstbill.dbhandler.DatabaseHandler;
import com.appi.gstbill.model.BillCustomerModl;
import com.appi.gstbill.model.CustomerModel;
import com.appi.gstbill.model.HistoryModel;

import java.util.ArrayList;

public class history extends AppCompatActivity {
    RecyclerView rvcustomername;
    DatabaseHandler databaseHandler ;
    ArrayList<HistoryModel> customerlist = new ArrayList<>();
    HistoryCustomerList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("History");

        rvcustomername = findViewById(R.id.history_rv_customername);
        databaseHandler = new DatabaseHandler(this);

        customerlist = databaseHandler.gethistorycustdetail();

        rvcustomername.setLayoutManager(new GridLayoutManager(this, 1));

        adapter = new HistoryCustomerList(this, customerlist);

        rvcustomername.setAdapter(adapter);
    }
}