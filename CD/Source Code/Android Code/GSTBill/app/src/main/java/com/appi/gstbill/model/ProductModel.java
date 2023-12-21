package com.appi.gstbill.model;

public class ProductModel {

    String ProductID, ProductGSTPercent, ProductPrice, AvailableQuantity, ProductName;

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

    public String getAvailableQuantity() {
        return AvailableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        AvailableQuantity = availableQuantity;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
}
