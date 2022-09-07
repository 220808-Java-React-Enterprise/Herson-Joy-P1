package com.revature.P1.models;

import java.time.LocalDateTime;

public class Reimbursement {
    private String author_id;
    private String reimb_id;
    private String status_id;
    private String type_id;
    private LocalDateTime submitted;
    private double amount;
    private String description;

    public Reimbursement(String author_id, String reimb_id, String status_id, String type_id, LocalDateTime submitted, double amount, String description) {
        this.author_id = author_id;
        this.reimb_id = reimb_id;
        this.status_id = status_id;
        this.type_id = type_id;
        this.submitted = submitted;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "author_id='" + author_id + '\'' +
                ", reimb_id='" + reimb_id + '\'' +
                ", status_id='" + status_id + '\'' +
                ", type_id='" + type_id + '\'' +
                ", submitted=" + submitted +
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

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public LocalDateTime getSubmitted() {
        return submitted;
    }

    public void setSubmitted(LocalDateTime submitted) {
        this.submitted = submitted;
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
