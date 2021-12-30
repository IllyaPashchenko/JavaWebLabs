package com.example.javalab2.domain;

import com.example.javalab2.domain.enums.Status;

public class Order extends Entity{
    private int cost;
    private User master;
    private User client;
    private StatusEntity completionStatus;
    private StatusEntity paymentStatus;
    private String description;
    private String feedback;

    public Order() {
    }

    public Order(Integer id, User master, User client, StatusEntity completionStatus, StatusEntity paymentStatus, String description, String feedback, int cost) {
        super(id);
        this.master = master;
        this.client = client;
        this.completionStatus = completionStatus;
        this.paymentStatus = paymentStatus;
        this.description = description;
        this.feedback = feedback;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public StatusEntity getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(StatusEntity completionStatus) {
        this.completionStatus = completionStatus;
    }

    public StatusEntity getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(StatusEntity paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}