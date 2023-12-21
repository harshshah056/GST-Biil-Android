package com.appi.gstbill.model;

public class HistoryModel {
    int BillID,CustomerID;
    String BillDate,BillTime,CustomerName,ProductName,ProductID,ProductGSTPercent,ProductPrice,HistoryQuantity,HistoryTotal;

    public int getBillID() {
        return BillID;
    }

    public void setBillID(int billID) {
        BillID = billID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
    }

    public String getBillTime() {
        return BillTime;
    }

    public void setBillTime(String billTime) {
        BillTime = billTime;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductGSTPercent() {
        return ProductGSTPercent;
    }

    public void setProductGSTPercent(String productGSTPercent) {
        ProductGSTPercent = productGSTPercent;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getHistoryQuantity() {
        return HistoryQuantity;
    }

    public void setHistoryQuantity(String historyQuantity) {
        HistoryQuantity = historyQuantity;
    }

    public String getHistoryTotal() {
        return HistoryTotal;
    }

    public void setHistoryTotal(String historyTotal) {
        HistoryTotal = historyTotal;
    }
}
