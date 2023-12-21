package com.appi.gstbill.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appi.gstbill.model.BillCustomerModl;
import com.appi.gstbill.model.CanvasModel;
import com.appi.gstbill.model.CustomerModel;
import com.appi.gstbill.model.HistoryModel;
import com.appi.gstbill.model.ProductModel;
import com.appi.gstbill.model.PurchaseModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteAssetHelper {

    public static final String db_name = "gst.db";
    public static final int db_ver = 1;

    public DatabaseHandler(Context context) {
        super(context, db_name, null, db_ver);
    }

    public ArrayList<ProductModel> getProduct() {

        ArrayList<ProductModel> product = new ArrayList<>();
        String SQL = "SELECT * FROM ProductDetail";
        SQLiteDatabase db = getReadableDatabase();           
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            ProductModel c = new ProductModel();
            c.setProductID(cursor.getString(cursor.getColumnIndex("ProductID")));
            c.setProductName(cursor.getString(cursor.getColumnIndex("ProductName")));
            c.setAvailableQuantity(cursor.getString(cursor.getColumnIndex("ProductQuantity")));
            c.setProductGSTPercent(cursor.getString(cursor.getColumnIndex("ProductGSTPercent")));
            c.setProductPrice(cursor.getString(cursor.getColumnIndex("ProductPrice")));

            product.add(c);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return product;
    }

    public ArrayList<CanvasModel> getitems(int ccid) {

        ArrayList<CanvasModel> canvas = new ArrayList<>();
        String iid = String.valueOf(ccid);
        String SQL = "SELECT * FROM  ProductDetail pd, PurchaseDetail purd, CustomerDetail cd WHERE purd.CustomerID = "+iid+ " AND purd.CustomerID = cd.CustomerID  AND purd.ProductID = pd.ProductID";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            CanvasModel c = new CanvasModel();
            c.setProductID(cursor.getString(cursor.getColumnIndex("ProductID")));
            c.setPurchaseTotalPrice(cursor.getString(cursor.getColumnIndex("PurchaseTotalPrice")));
            c.setCustomerID(cursor.getInt(cursor.getColumnIndex("CustomerID")));
            c.setPurchaseQuantity(cursor.getString(cursor.getColumnIndex("PurchaseQuantity")));
            c.setCustomerName(cursor.getString(cursor.getColumnIndex("CustomerName")));
            c.setProductName(cursor.getString(cursor.getColumnIndex("ProductName")));
            c.setProductGSTPercent(cursor.getString(cursor.getColumnIndex("ProductGSTPercent")));
            c.setProductPrice(cursor.getString(cursor.getColumnIndex("ProductPrice")));

            canvas.add(c);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return canvas;
    }

    public ArrayList<HistoryModel> gethistoryitems(int ccid) {

        ArrayList<HistoryModel> canvas = new ArrayList<>();
        String iid = String.valueOf(ccid);
        String SQL = "SELECT * FROM BillCount bc, CustomerDetail cd, History hd, ProductDetail pd WHERE hd.BillID = " + iid + " AND bc.BillID = " +iid+ " AND hd.CustomerID = cd.CustomerID AND hd.ProductID = pd.ProductID";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            HistoryModel c = new HistoryModel();
            c.setProductID(cursor.getString(cursor.getColumnIndex("ProductID")));
            c.setHistoryTotal(cursor.getString(cursor.getColumnIndex("HistoryTotal")));
            c.setCustomerID(cursor.getInt(cursor.getColumnIndex("CustomerID")));
            c.setHistoryQuantity(cursor.getString(cursor.getColumnIndex("HistoryQuantity")));
            c.setCustomerName(cursor.getString(cursor.getColumnIndex("CustomerName")));
            c.setProductName(cursor.getString(cursor.getColumnIndex("ProductName")));
            c.setProductGSTPercent(cursor.getString(cursor.getColumnIndex("ProductGSTPercent")));
            c.setProductPrice(cursor.getString(cursor.getColumnIndex("ProductPrice")));
            c.setBillID(cursor.getInt(cursor.getColumnIndex("BillID")));
            c.setBillDate(cursor.getString(cursor.getColumnIndex("BillDate")));
            c.setBillTime(cursor.getString(cursor.getColumnIndex("BillTime")));

            canvas.add(c);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return canvas;
    }

    public Cursor getitemcanvas(int ccid) {

        String iid = String.valueOf(ccid);
        String SQL = "SELECT * FROM  ProductDetail pd, PurchaseDetail purd, CustomerDetail cd WHERE purd.CustomerID = "+iid+ " AND purd.CustomerID = cd.CustomerID  AND purd.ProductID = pd.ProductID";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++) {
            System.out.println(i);
            cursor.moveToNext();
        }


        return cursor;
    }
    public void close(Cursor cursor){
        cursor.close();
    }

    public Cursor gethistorycanvas(int ccid) {

        ArrayList<HistoryModel> canvas = new ArrayList<>();
        String iid = String.valueOf(ccid);
        String SQL = "SELECT * FROM BillCount bc, CustomerDetail cd, History hd, ProductDetail pd WHERE hd.BillID = " + iid + " AND bc.BillID = " +iid+ " AND hd.CustomerID = cd.CustomerID AND hd.ProductID = pd.ProductID";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++) {
            System.out.println(i);
            cursor.moveToNext();
        }


        return cursor;
    }



    public ArrayList<CustomerModel> getCustomer() {

        ArrayList<CustomerModel> product = new ArrayList<>();
        String SQL = "SELECT * FROM CustomerDetail ORDER BY CustomerID DESC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            CustomerModel c = new CustomerModel();
            c.setCustomerID(cursor.getInt(cursor.getColumnIndex("CustomerID")));
            c.setCustomerName(cursor.getString(cursor.getColumnIndex("CustomerName")));
            c.setCustomerContactNumber(cursor.getString(cursor.getColumnIndex("CustomerContactNumber")));
            c.setCustomerCIty(cursor.getString(cursor.getColumnIndex("CustomerCIty")));
            c.setCreatedDate(cursor.getString(cursor.getColumnIndex("CreatedDate")));

            product.add(c);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return product;
    }

    public float gettotal(int customerid){

        float pricedata = 0;
        String id = String.valueOf(customerid);
        String SQL = "SELECT PurchaseTotalPrice FROM PurchaseDetail WHERE CustomerID ="+id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            String data;
            data = cursor.getString(cursor.getColumnIndex("PurchaseTotalPrice"));

            Float temp = Float.parseFloat(data);
            pricedata = pricedata + temp;
            cursor.moveToNext();
        }

        db.close();
        cursor.close();
        return pricedata;
    }

    public String gethistorytotal(int bid){

        String data;
        String id = String.valueOf(bid);
        String SQL = "SELECT * FROM BillCount WHERE BillID = " + id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        data = cursor.getString(cursor.getColumnIndex("TotalBill"));
        db.close();
        cursor.close();
        return  data;
    }

    public String getdate(int bid){

        String data;
        String id = String.valueOf(bid);
        String SQL = "SELECT * FROM BillCount WHERE BillID = " + id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        data = cursor.getString(cursor.getColumnIndex("BillDate"));
        db.close();
        cursor.close();
        return  data;
    }

    public String gettime(int bid){

        String data;
        String id = String.valueOf(bid);
        String SQL = "SELECT * FROM BillCount WHERE BillID = " + id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        data = cursor.getString(cursor.getColumnIndex("BillTime"));
        db.close();
        cursor.close();
        return  data;
    }

    public String getname(int customerid){
        String a;
        String id = String.valueOf(customerid);
        String SQL = "SELECT CustomerName FROM CustomerDetail WHERE CustomerID ="+id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        a = cursor.getString(cursor.getColumnIndex("CustomerName"));
        return a;
    }

    public String gethistoryname(int cid){
        String a;
        String id = String.valueOf(cid);
        String SQL = "SELECT * FROM CustomerDetail cd, BillCount bc WHERE bc.BillID = "+id+" AND bc.CustomerID = cd.CustomerID";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        a = cursor.getString(cursor.getColumnIndex("CustomerName"));
        return a;
    }
    public String gethistoryphone(int cid){
        String a;
        String id = String.valueOf(cid);
        String SQL = "SELECT * FROM CustomerDetail cd, BillCount bc WHERE bc.BillID = "+id+" AND bc.CustomerID = cd.CustomerID";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        a = cursor.getString(cursor.getColumnIndex("CustomerContactNumber"));
        return a;
    }

    public String getphone(int customerid){
        String a;
        String id = String.valueOf(customerid);
        String SQL = "SELECT CustomerContactNumber FROM CustomerDetail WHERE CustomerID ="+id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        a = cursor.getString(cursor.getColumnIndex("CustomerContactNumber"));
        return a;
    }

    public String getgst(String ProductName) {

        String gst;
        String SQL = "SELECT ProductGSTPercent FROM ProductDetail WHERE ProductName = \""+ ProductName + "\"";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        gst = cursor.getString(cursor.getColumnIndex("ProductGSTPercent"));

        db.close();
        cursor.close();
        return gst;
    }

    public String getprice(String ProductName) {

        String price;
        String SQL = "SELECT ProductPrice FROM ProductDetail WHERE ProductName = \""+ ProductName + "\"";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        price = cursor.getString(cursor.getColumnIndex("ProductPrice"));

        db.close();
        cursor.close();
        return price;
    }

    public int getBillID(){
        String SQL = "SELECT * FROM BillCount";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToLast();
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("BillID")));
        db.close();
        cursor.close();
        return id;

    }

    public long insertcustomerdetail(ContentValues c){
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert("CustomerDetail",null,c);
        db.close();
        return id;
    }

    public long insertproductdetail(ContentValues c){
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert("ProductDetail",null,c);
        db.close();
        return id;
    }

    public long insertbill(ContentValues c){
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert("BillCount",null,c);
        db.close();
        return id;
    }





    public ArrayList<CustomerModel> getCustomerdetail() {

        ArrayList<CustomerModel> user = new ArrayList<>();
        String SQL = "SELECT * FROM CustomerDetail ORDER BY CustomerID DESC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount() ; i++){
            CustomerModel u = new CustomerModel();
            u.setCustomerID(cursor.getInt(cursor.getColumnIndex("CustomerID")));
            u.setCustomerName(cursor.getString(cursor.getColumnIndex("CustomerName")));
            u.setCustomerCIty(cursor.getString(cursor.getColumnIndex("CustomerCIty")));
            u.setCustomerContactNumber(cursor.getString(cursor.getColumnIndex("CustomerContactNumber")));

            user.add(u);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return user;
    }

    public ArrayList<ProductModel> getProductdetail() {

        ArrayList<ProductModel> product = new ArrayList<>();
        String SQL = "SELECT * FROM ProductDetail ORDER BY ProductID DESC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount() ; i++){
            ProductModel u = new ProductModel();
            u.setProductID(cursor.getString(cursor.getColumnIndex("ProductID")));
            u.setProductName(cursor.getString(cursor.getColumnIndex("ProductName")));
            u.setProductPrice(cursor.getString(cursor.getColumnIndex("ProductPrice")));
            u.setProductGSTPercent(cursor.getString(cursor.getColumnIndex("ProductGSTPercent")));
            u.setAvailableQuantity(cursor.getString(cursor.getColumnIndex("ProductQuantity")));

            product.add(u);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return product;
    }
    public ArrayList<BillCustomerModl> getcustname(){
        ArrayList<BillCustomerModl> cname = new ArrayList<>();
        String SQL = "SELECT * FROM BillCount bc, CustomerDetail cd WHERE bc.CustomerID = cd.CustomerID";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL,null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++){
            BillCustomerModl cm = new BillCustomerModl();
            cm.setCustomerID(cursor.getInt(cursor.getColumnIndex("CustomerID")));
            cm.setCustomerName(cursor.getString(cursor.getColumnIndex("CustomerName")));
            cm.setCustomerCIty(cursor.getString(cursor.getColumnIndex("CustomerCIty")));
            cm.setCustomerContactNumber(cursor.getString(cursor.getColumnIndex("CustomerContactNumber")));
            cm.setBillID(cursor.getInt(cursor.getColumnIndex("BillID")));
            cm.setTotalBill(cursor.getInt(cursor.getColumnIndex("TotalBill")));
            cm.setBillDate(cursor.getString(cursor.getColumnIndex("BillDate")));
            cm.setIsPaid(cursor.getString(cursor.getColumnIndex("IsPaid")));
            cm.setCreatedDate(cursor.getString(cursor.getColumnIndex("CreatedDate")));
            cm.setBillTime(cursor.getString(cursor.getColumnIndex("BillTime")));

            cname.add(cm);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return cname;
    }

    public ArrayList<HistoryModel> gethistorycustdetail(){
        ArrayList<HistoryModel> cname = new ArrayList<>();
        String SQL = "SELECT * FROM BillCount bc, CustomerDetail cd WHERE bc.CustomerID = cd.CustomerID  ORDER BY bc.BillID DESC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL,null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++){
            HistoryModel c = new HistoryModel();
            c.setCustomerID(cursor.getInt(cursor.getColumnIndex("CustomerID")));
            c.setCustomerName(cursor.getString(cursor.getColumnIndex("CustomerName")));
            c.setBillID(cursor.getInt(cursor.getColumnIndex("BillID")));
            c.setBillDate(cursor.getString(cursor.getColumnIndex("BillDate")));
            c.setBillTime(cursor.getString(cursor.getColumnIndex("BillTime")));

            cname.add(c);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return cname;
    }

    public ArrayList<PurchaseModel> getPurchaseDetail(int a){

        String b = String.valueOf(a);
        ArrayList<PurchaseModel> purchase = new ArrayList<>();
        String SQL = "SELECT * FROM PurchaseDetail, ProductDetail WHERE PurchaseDetail.ProductID = ProductDetail.ProductID AND PurchaseDetail.CustomerID = "+ b + " ORDER BY PurchaseID DESC";
        System.out.println(SQL);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            PurchaseModel u = new PurchaseModel();
            u.setPurchaseID(cursor.getInt(cursor.getColumnIndex("PurchaseID")));
            u.setProductID(cursor.getString(cursor.getColumnIndex("ProductID")));
            u.setProductName(cursor.getString(cursor.getColumnIndex("ProductName")));
            u.setPurchaseQuantity(cursor.getString(cursor.getColumnIndex("PurchaseQuantity")));
            u.setPurchaseTotalPrice(cursor.getString(cursor.getColumnIndex("PurchaseTotalPrice")));
            u.setProductGSTPercent(cursor.getString(cursor.getColumnIndex("ProductGSTPercent")));


            purchase.add(u);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return purchase;


    }

    public ArrayList<PurchaseModel> getBill(int a){

        String b = String.valueOf(a);
        ArrayList<PurchaseModel> purchase = new ArrayList<>();
        String SQL = "SELECT * FROM PurchaseDetail, ProductDetail WHERE PurchaseDetail.ProductID = ProductDetail.ProductID AND PurchaseDetail.CustomerID = "+ b;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            PurchaseModel u = new PurchaseModel();
            u.setProductName(cursor.getString(cursor.getColumnIndex("ProductName")));
            u.setPurchaseQuantity(cursor.getString(cursor.getColumnIndex("PurchaseQuantity")));
            u.setPurchaseTotalPrice(cursor.getString(cursor.getColumnIndex("PurchaseTotalPrice")));
            u.setProductGSTPercent(cursor.getString(cursor.getColumnIndex("ProductGSTPercent")));

            purchase.add(u);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return purchase;

    }

    public long insertPurchasedetail(ContentValues c){
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert("PurchaseDetail",null,c);
        db.close();
        return id;
    }

    public long insertHistory(ContentValues c){
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert("History",null,c);
        db.close();
        return id;
    }

    public int deletecustomer(int CustomerID){
        int a = CustomerID;
        SQLiteDatabase db = getWritableDatabase();
        int id = db.delete("CustomerDetail", "CustomerID = ?", new String[]{String.valueOf(a)});
        db.close();
        return id;
    }
    public int deleproduct(int ProductID){
        int a = ProductID;
        SQLiteDatabase db = getWritableDatabase();
        int id = db.delete("ProductDetail", "ProductID = ?", new String[]{String.valueOf(a)});
        db.close();
        return id;
    }

    public int deletehistory(int CustomerID){
        int a = CustomerID;
        SQLiteDatabase db = getWritableDatabase();
        int id = db.delete("History", "CustomerID = ?", new String[]{String.valueOf(a)});
        db.close();
        return id;
    }

    public int deletepurchasehistory(int CustomerID){
        int a = CustomerID;
        SQLiteDatabase db = getWritableDatabase();
        int id = db.delete("PurchaseDetail", "CustomerID = ?", new String[]{String.valueOf(a)});
        db.close();
        return id;
    }

    public int deletepurchase(int PurchaseID){
        SQLiteDatabase db = getWritableDatabase();
        int id = db.delete("PurchaseDetail", "PurchaseID = ?", new String[]{String.valueOf(PurchaseID)});
        db.close();
        return id;

    }


}

