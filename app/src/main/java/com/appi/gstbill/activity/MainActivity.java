package com.appi.gstbill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.appi.gstbill.R;

public class MainActivity extends AppCompatActivity {
    Button btnadd, btnhistory, btnpurchase, btncustomer, btnprod, btnproddetail ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

//        ImageView ivdv = (ImageView) findViewById(R.id.iv_dv);
//
//        ivdv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(MainActivity.this, developer.class);
//                startActivity(in);
//            }
//        });

        btnadd = (Button) findViewById(R.id.main_add);
        btnhistory = (Button) findViewById(R.id.main_history);
        btnpurchase = (Button) findViewById(R.id.main_purchase);
        btncustomer = (Button) findViewById(R.id.main_customer);
        btnprod = (Button)findViewById(R.id.main_product);
        btnproddetail = (Button)findViewById(R.id.main_productDetail);

        btnprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, add_prod.class);
                startActivity(in);
            }
        });
        btnproddetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, product_details.class);
                startActivity(in);
            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, add_customer.class);
                startActivity(in);
            }
        });

        btnpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, purchase.class);
                startActivity(in);
            }
        });

        btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, customer_detail.class);
                startActivity(in);
            }
        });

        btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, history.class);
                startActivity(in);
            }
        });



    }
}