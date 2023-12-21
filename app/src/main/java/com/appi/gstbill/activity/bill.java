package com.appi.gstbill.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appi.gstbill.Canvas.DisplayCanvas;
import com.appi.gstbill.R;
import com.appi.gstbill.adapter.BillAdapter;
import com.appi.gstbill.dbhandler.DatabaseHandler;
import com.appi.gstbill.model.PurchaseModel;
import com.appi.gstbill.activity.purchase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class bill extends AppCompatActivity {
    RecyclerView rvbill;
    EditText etamount;
    Switch swPayed;
    Button btnSave;
    ArrayList<PurchaseModel> purchase = new ArrayList<>();
    DatabaseHandler databasehandler;
    BillAdapter adapter;
    public static int exe = 0, id;
    ContentValues c1 = new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        initialize();


        String a = getIntent().getStringExtra("CustomerID");
        Bundle myBundle = getIntent().getExtras();
        id = Integer.parseInt(a);

        purchase = databasehandler.getPurchaseDetail(id);
        rvbill.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new BillAdapter(this, purchase);
        rvbill.setAdapter(adapter);

        float temp = databasehandler.gettotal(id);
        etamount.setText(String.valueOf(temp));

        btnSave = findViewById(R.id.save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    insert(id);
                    for (int j = 0; j < adapter.getItemCount(); j++){
                        int iii = databasehandler.getBillID();
                        System.out.println(iii);
                        c1.put("CustomerID", id);
                        c1.put("ProductID",purchase.get(j).getProductID());
                        c1.put("HistoryQuantity",String.valueOf(purchase.get(j).getPurchaseQuantity()));
                        c1.put("BillID",iii);
                        c1.put("HistoryTotal", String.valueOf(purchase.get(j).getPurchaseTotalPrice()));

                        if (databasehandler.insertHistory(c1) > 0 ) {
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                        }
                    }


                    Intent in = new Intent(getApplicationContext(), DisplayCanvas.class);
                    in.putExtra("iid", a);
                    startActivity(in);
                    finish();
            }
        });

    }


    public void initialize(){
        rvbill = findViewById(R.id.bill_rv_list);
        databasehandler = new DatabaseHandler(this);
        etamount = findViewById(R.id.bill_amount);
    }
    void insert(int id){
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        String data = String.valueOf(s);
        System.out.println(data);
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        ContentValues c = new ContentValues();
        c.put("TotalBill",etamount.getText().toString());
        c.put("BillDate", data);
        c.put("IsPaid","YES");
        c.put("CustomerID",String.valueOf(id));
        c.put("BillTime",currentTime);

        if (databasehandler.insertbill(c) > 0 ) {
        }
        else {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }
}