package com.appi.gstbill.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appi.gstbill.R;
import com.appi.gstbill.dbhandler.DatabaseHandler;

public class add_prod extends AppCompatActivity {
    EditText etName, etPer, etPrice, etqty;
    Button btnadd;
    DatabaseHandler databasehandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prod);
        etName = findViewById(R.id.add_product_name);
        etPrice = findViewById(R.id.add_product_price);
        etPer = findViewById(R.id.add_product_tax);
        etqty = findViewById(R.id.add_product_qut);
        btnadd = (Button)findViewById(R.id.add_product_add);
        databasehandler = new DatabaseHandler(this);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid()) {
                    insert();
                    Intent in = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(in);
                    finish();
                }
            }
        });

    }
    boolean valid(){
        if(TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError("Enter name");
            return false;
        }
        if(TextUtils.isEmpty(etPrice.getText().toString())) {
            etPrice.setError("Enter Price");
            return false;
        }
        if(TextUtils.isEmpty(etqty.getText().toString())) {
            etqty.setError("Enter Quantity");
            return false;
        }
        if(TextUtils.isEmpty(etPer.getText().toString())) {
            etPer.setError("Enter Tax Percent");
            return false;
        }
        return true;
    }
    void insert(){
        ContentValues c = new ContentValues();
        c.put("ProductName",etName.getText().toString());
        c.put("ProductPrice", etPrice.getText().toString());
        c.put("ProductQuantity",etqty.getText().toString());
        c.put("ProductGSTPercent",etPer.getText().toString());


        if (databasehandler.insertproductdetail(c) > 0 ) {
            Toast.makeText(this,"Product Added Successfully",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }

}