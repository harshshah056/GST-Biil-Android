package com.appi.gstbill.model;

public class CustomerModel {
    int CustomerID;
    String CustomerName, CustomerCIty, CustomerContactNumber, CreatedDate;

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerCIty() {
        return CustomerCIty;
    }

    public void setCustomerCIty(String customerCIty) {
        CustomerCIty = customerCIty;
    }

    public String getCustomerContactNumber() {
        return CustomerContactNumber;
    }

    public void setCustomerContactNumber(String customerContactNumber) {
        CustomerContactNumber = customerContactNumber;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}
