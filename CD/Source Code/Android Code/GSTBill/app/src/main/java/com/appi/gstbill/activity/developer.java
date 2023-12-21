package com.appi.gstbill.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appi.gstbill.R;

public class developer extends AppCompatActivity {

    TextView tv_number_1 , tv_number_2 , tv_number_3 , tv_mail_1 , tv_mail_2 , tv_mail_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        setTitle("Developers");

        tv_number_1 = findViewById(R.id.tv_number_1);
        tv_number_2 = findViewById(R.id.tv_number_2);

        tv_mail_1 = findViewById(R.id.tv_mail_1);
        tv_mail_2 = findViewById(R.id.tv_mail_2);


        tv_number_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8866531027"));
                startActivity(intent);
            }
        });

        tv_number_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8758600024"));
                startActivity(intent);
            }
        });



        tv_mail_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:186620307025@darshan.ac.in"));
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });

        tv_mail_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:186620307030@darshan.ac.in"));
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });



    }
}