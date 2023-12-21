package com.appi.gstbill.model;

public class PurchaseModel {
    int PurchaseID;
    String ProductName, PurchaseQuantity, PurchaseTotalPrice, CustomerID, ProductID, ProductGSTPercent;

    public int getPurchaseID() {
        return PurchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        PurchaseID = purchaseID;
    }

    public String getPurchaseQuantity() {
        return PurchaseQuantity;
    }

    public void setPurchaseQuantity(String purchaseQuantity) {
        PurchaseQuantity = purchaseQuantity;
    }

    public String getPurchaseTotalPrice() {
        return PurchaseTotalPrice;
    }

    public void setPurchaseTotalPrice(String purchaseTotalPrice) {
        PurchaseTotalPrice = purchaseTotalPrice;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
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

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
}
