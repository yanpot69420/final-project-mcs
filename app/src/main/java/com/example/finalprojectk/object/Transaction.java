package com.example.finalprojectk.object;

public class Transaction {
    private Integer transactionID;
    private String userID;
    private String productID;
    private String transactionDate;
    private Integer quantity;

    public Transaction(Integer transactionID, String userID, String productID, String transactionDate, Integer quantity) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.productID = productID;
        this.transactionDate = transactionDate;
        this.quantity = quantity;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
