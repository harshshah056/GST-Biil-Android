package com.appi.gstbill.Canvas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appi.gstbill.R;
import com.appi.gstbill.activity.MainActivity;
import com.appi.gstbill.adapter.BillAdapter;
import com.appi.gstbill.adapter.CanvasAdapter;
import com.appi.gstbill.dbhandler.DatabaseHandler;
import com.appi.gstbill.model.CanvasModel;
import com.appi.gstbill.model.ProductModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DisplayCanvas extends AppCompatActivity {

    PdfDocument mypdfdocument = new PdfDocument();
    Paint mypaint = new Paint();
    Button btn1;
    DatabaseHandler databaseHandler;
    RecyclerView canvasrv;
    ArrayList<CanvasModel> canvaslist = new ArrayList<>();
    CanvasAdapter canvasAdapter;
    TextView canvas_date, canvas_time, canvas_name, canvas_phone, canvas_total;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_canvas);

        String a = getIntent().getStringExtra("iid");
        int custid = Integer.parseInt(a);
        btn1 = findViewById(R.id.canvas_close);

        databaseHandler = new DatabaseHandler(this);

        canvasrv = findViewById(R.id.canvas_rv_data);
        canvas_total = findViewById(R.id.canvas_total);
        canvas_name = findViewById(R.id.canvas_name);
        canvas_phone = findViewById(R.id.canvas_phone);
        canvas_date = findViewById(R.id.canvas_date);
        canvas_time = findViewById(R.id.canvas_time);

        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        String data = String.valueOf(s);


        String currentTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());
        fun(custid, data, currentTime);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(in);
            }
        });

        PdfDocument.PageInfo mypageinfo = new PdfDocument.PageInfo.Builder(1000,900,1).create();
        PdfDocument.Page mypage = mypdfdocument.startPage(mypageinfo);
        Canvas canvas = mypage.getCanvas();


        mypaint.setTextSize(80);
        canvas.drawText("GST",30,80,mypaint);



        mypaint.setTextSize(30);
        canvas.drawText("Bill Generator",30,120,mypaint);

        mypaint.setColor(Color.rgb(150,150,150));
        canvas.drawRect(30,150,canvas.getWidth()-30,160,mypaint);


        mypaint.setColor(Color.BLACK);
        canvas.drawText("Customer Name:", 30,200,mypaint);
        canvas.drawText(canvas_name.getText().toString(),280,200,mypaint);

        canvas.drawText("Phone No:",30,250,mypaint);
        canvas.drawText(canvas_phone.getText().toString(),280,250,mypaint);

        mypaint.setColor(Color.BLACK);
        canvas.drawText("Date:",30,300,mypaint);
        canvas.drawText(data, 280, 300, mypaint);

        canvas.drawText(currentTime, 530, 300, mypaint);




        mypaint.setColor(Color.rgb(150,150,150));
        canvas.drawRect(30,350,canvas.getWidth()-30,400, mypaint);

        mypaint.setColor(Color.WHITE);
        canvas.drawText("Item", 50, 385, mypaint);
        canvas.drawText("GST", 250, 385, mypaint);
        canvas.drawText("Price", 450, 385, mypaint);
        canvas.drawText("Quantity", 650, 385, mypaint);
        mypaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Total Price", canvas.getWidth()-45, 385, mypaint);

        int y = 430;

        Cursor cursor = databaseHandler.getitemcanvas(custid);

        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            canvas.drawText(cursor.getString(cursor.getColumnIndex("ProductName")), 50, y, mypaint);
            canvas.drawText(cursor.getString(cursor.getColumnIndex("ProductGSTPercent")), 250, y, mypaint);
            canvas.drawText(cursor.getString(cursor.getColumnIndex("ProductPrice")), 450, y, mypaint);
            canvas.drawText(cursor.getString(cursor.getColumnIndex("PurchaseQuantity")), 650, y, mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);

            int gst = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ProductGSTPercent")));
            int price = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ProductPrice")));
            int quantity = Integer.parseInt(cursor.getString(cursor.getColumnIndex("PurchaseQuantity")));
            float x = (gst * price) ;
            x = x / 100;

            float totalprice = price + x;

            totalprice = totalprice * quantity;
            canvas.drawText(String.valueOf(totalprice), canvas.getWidth()-45, y, mypaint);
            y = y + 50;
            cursor.moveToNext();
        }
        databaseHandler.close(cursor);


        mypaint.setTextAlign(Paint.Align.RIGHT);
        mypaint.setColor(Color.rgb(150,150,150));
        canvas.drawRect(30,y,canvas.getWidth()-30,y+10,mypaint);

        y = y +50;
        mypaint.setColor(Color.BLACK);
        mypaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Total:",550,y,mypaint);


        mypaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(canvas_total.getText().toString(),970,y,mypaint);

        mypaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        mypaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Thank you very much come again",30,840,mypaint);
        mypdfdocument.finishPage(mypage);
//        File file = new File("Android SDK built for x86/","hello.pdf");
        String filename = "GSTBill_"+ canvas_name.getText().toString() +"_"+data+"_"+currentTime+".pdf";
        File file = new File(this.getExternalFilesDir("/"), filename);




        try {
            mypdfdocument.writeTo(new FileOutputStream(file));
            Toast.makeText(this, filename+" has saved at Android/data/com.appi.gstbill/files/",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (databaseHandler.deletepurchasehistory(custid) > 0){
        }
    }

    public void fun(int custmerid, String date, String time) {
        canvaslist = databaseHandler.getitems(custmerid);
        canvasrv.setLayoutManager(new GridLayoutManager(this, 1));
        canvasAdapter = new CanvasAdapter(this, canvaslist);
        canvasrv.setAdapter(canvasAdapter);

        float temp = databaseHandler.gettotal(custmerid);
        canvas_total.setText(String.valueOf(temp));

        canvas_name.setText(databaseHandler.getname(custmerid));
        canvas_phone.setText(databaseHandler.getphone(custmerid));
        canvas_time.setText(time);
        canvas_date.setText(date);

    }
}
