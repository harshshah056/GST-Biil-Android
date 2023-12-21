package com.appi.gstbill.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appi.gstbill.R;
import com.appi.gstbill.dbhandler.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class add_customer extends AppCompatActivity {
    EditText etdate, etname, etcontact, etcity;
    Spinner spcity;
    Button btnadd;
    DatabaseHandler databasehandler;
    String[] cityList = new String[] {
            "Rajkot", "Morbi", "Surat", "Ahmedabad", "Vadodara", "Gandhinagar", "Patan", "Gondal", "Anand"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        setTitle("Add Customer");

        etdate = findViewById(R.id.add_customer_date);
        etname = findViewById(R.id.add_customer_name);
        etcontact = findViewById(R.id.add_customer_contact);
        spcity = findViewById(R.id.add_customer_city);
        btnadd = findViewById(R.id.add_customer_add);
        databasehandler = new DatabaseHandler(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cityList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spcity.setAdapter(adapter);


        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        etdate.setText(formattedDate);
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

    void insert(){
        ContentValues c = new ContentValues();
        c.put("CustomerName",etname.getText().toString());
        c.put("CustomerContactNumber", etcontact.getText().toString());
        c.put("CreatedDate",etdate.getText().toString());
        String city = spcity.getSelectedItem().toString();
        c.put("CustomerCIty",city);

        if (databasehandler.insertcustomerdetail(c) > 0 ) {
            Toast.makeText(this,"Registration Successfully",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }

    boolean valid(){
        if(TextUtils.isEmpty(etname.getText().toString())) {
            etname.setError("Enter name");
            return false;
        }
        if(TextUtils.isEmpty(etcontact.getText().toString())) {
            etcontact.setError("Enter Contact Number");
            return false;
        }
        if (etcontact.length() < 10){
            etcontact.setError("Enter Valid contact");
            return false;
        }
        return true;
    }
}