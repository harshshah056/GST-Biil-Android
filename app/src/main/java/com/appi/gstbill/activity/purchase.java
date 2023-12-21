package com.appi.gstbill.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appi.gstbill.R;
import com.appi.gstbill.adapter.CustomerListAdapter;
import com.appi.gstbill.adapter.ProductListAdapter;
import com.appi.gstbill.adapter.PurchaseDetailAdapter;
import com.appi.gstbill.dbhandler.DatabaseHandler;
import com.appi.gstbill.model.CustomerModel;
import com.appi.gstbill.model.ProductModel;
import com.appi.gstbill.model.PurchaseModel;

import java.util.ArrayList;

public class purchase extends AppCompatActivity {

    DatabaseHandler databasehandler;
    RecyclerView rvpurchase;
    ArrayList<ProductModel> productlist = new ArrayList<>();
    ArrayList<CustomerModel> customerlist = new ArrayList<>();
    ArrayList<PurchaseModel> purchase = new ArrayList<>();
    ArrayList<PurchaseModel> temppurchase = new ArrayList<>();
    Spinner custname, proname;
    EditText etgst, etprice, etquantity;
    Button btnadd, btnfinish;
    public static PurchaseDetailAdapter adapter;


    int price, gst, quantity;
    float totalprice;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        setTitle("Purchase");
        initialise();

        databasehandler = new DatabaseHandler(this);
        productlist = databasehandler.getProduct();
        proname.setAdapter(new ProductListAdapter(this, productlist));

        customerlist = databasehandler.getCustomer();
        custname.setAdapter(new CustomerListAdapter(this, customerlist));



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etquantity.getText().toString())){
                    etquantity.setError("Enter Quantity");
                }
                else{
                    additem();
                }
            }
        });

        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(purchase.this, bill.class);
                String a = String.valueOf(customerlist.get(custname.getSelectedItemPosition()).getCustomerID());
                in.putExtra("CustomerID", a);
                startActivity(in);
                finish();
            }
        });



        fun(this);
    }


    public void initialise(){
        custname = findViewById(R.id.purchase_customerName);
        proname = findViewById(R.id.purchase_productName);
        etgst = findViewById(R.id.purchase_gst);
        etprice = findViewById(R.id.purchase_price);
        etquantity = findViewById(R.id.purchase_quantity);
        btnadd = findViewById(R.id.purchase_additem);
        btnfinish = findViewById(R.id.purchase_finish);
        rvpurchase = findViewById(R.id.purchase_rv_product);

        databasehandler = new DatabaseHandler(this);

    }

    public void additem(){

        price = Integer.parseInt(etprice.getText().toString());
        gst = Integer.parseInt(etgst.getText().toString());
        quantity = Integer.parseInt(etquantity.getText().toString());

        float x = (gst * price) ;
        x = x / 100;

        totalprice = price + x;

        totalprice = totalprice * quantity;


        ContentValues c = new ContentValues();

        c.put("PurchaseQuantity", etquantity.getText().toString());
        c.put("CustomerID",customerlist.get(custname.getSelectedItemPosition()).getCustomerID());
        c.put("PurchaseTotalPrice",String.valueOf(totalprice));
        c.put("ProductID",productlist.get(proname.getSelectedItemPosition()).getProductID());

        rvpurchase.setAdapter(adapter);

        if (databasehandler.insertPurchasedetail(c) > 0 ) {
            ex();
        }
        else {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }

    }


    public void fun(com.appi.gstbill.activity.purchase p){

        custname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ex();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


        proname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String a = productlist.get(proname.getSelectedItemPosition()).getProductName();
                String gst = databasehandler.getgst(a);
                String price = databasehandler.getprice(a);
                etprice.setText(price);
                etgst.setText(gst);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    public void ex(){
        purchase = databasehandler.getPurchaseDetail(customerlist.get(custname.getSelectedItemPosition()).getCustomerID());
        temppurchase.addAll(purchase);
        rvpurchase.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new PurchaseDetailAdapter(this, purchase, new PurchaseDetailAdapter.OnDeletePurchase() {
            @Override
            public void onDeletePurchase() {
                purchase.clear();
                temppurchase.clear();
                purchase = databasehandler.getPurchaseDetail(customerlist.get(custname.getSelectedItemPosition()).getCustomerID());
                temppurchase.addAll(purchase);
                adapter.notifyDataSetChanged();
                ex();
            }
        });
        rvpurchase.setAdapter(adapter);
    }
}