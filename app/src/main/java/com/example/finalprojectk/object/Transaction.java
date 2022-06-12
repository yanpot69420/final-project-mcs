package com.example.finalprojectk.object;

public class Transaction {
    private Integer transactionID;
    private Integer userID;
    private String productID;
    private String transactionDate;
    private Integer quantity;

    public Transaction(Integer transactionID, Integer userID, String productID, String transactionDate, Integer quantity) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.productID = productID;
        this.transactionDate = transactionDate;
        this.quantity = quantity;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getProductID() {
        return productID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
