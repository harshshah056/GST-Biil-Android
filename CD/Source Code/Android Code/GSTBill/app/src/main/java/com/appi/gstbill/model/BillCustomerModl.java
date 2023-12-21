package com.appi.gstbill.model;

public class BillCustomerModl {
    int BillID, TotalBill, CustomerID;
    String BillDate, IsPaid, CustomerName, CustomerCIty, CustomerContactNumber, CreatedDate, BillTime;

    public String getBillTime() {
        return BillTime;
    }

    public void setBillTime(String billTime) {
        BillTime = billTime;
    }

    public int getBillID() {
        return BillID;
    }

    public void setBillID(int billID) {
        BillID = billID;
    }

    public int getTotalBill() {
        return TotalBill;
    }

    public void setTotalBill(int totalBill) {
        TotalBill = totalBill;
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

    public String getIsPaid() {
        return IsPaid;
    }

    public void setIsPaid(String isPaid) {
        IsPaid = isPaid;
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
