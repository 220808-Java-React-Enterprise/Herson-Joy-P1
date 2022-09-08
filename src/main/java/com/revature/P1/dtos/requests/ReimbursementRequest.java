package com.revature.P1.dtos.requests;

public class ReimbursementRequest {
    private String author_id;
    private String type;
    private double amount;
    private String description;

    public  ReimbursementRequest(){};

    public ReimbursementRequest(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReimbursementRequest{" +
                "author_id='" + author_id + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
