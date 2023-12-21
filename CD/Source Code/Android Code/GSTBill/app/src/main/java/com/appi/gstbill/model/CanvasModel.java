package com.appi.gstbill.model;

public class CanvasModel {
    int BillID,CustomerID;
    String BillDate,CustomerName,ProductName,ProductID,ProductGSTPercent,ProductPrice,PurchaseQuantity,PurchaseTotalPrice;

    public int getBillID() {
        return BillID;
    }

    public String getPurchaseQuantity() {
        return PurchaseQuantity;
    }

    public void setPurchaseQuantity(String purchaseQuantity) {
        PurchaseQuantity = purchaseQuantity;
    }

    public void setBillID(int billID) {
        BillID = billID;
    }

    public String getPurchaseTotalPrice() {
        return PurchaseTotalPrice;
    }

    public void setPurchaseTotalPrice(String purchaseTotalPrice) {
        PurchaseTotalPrice = purchaseTotalPrice;
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
}
