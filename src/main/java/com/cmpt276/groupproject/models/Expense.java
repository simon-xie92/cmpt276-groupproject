package com.cmpt276.groupproject.models;

import jakarta.persistence.*;

@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int eid;
    private int uid;
    private String reason;
    private double amount;
    public Expense() {
    }
    public Expense(int uid, String reason, double amount) {
        this.uid = uid;
        this.reason = reason;
        this.amount = amount;
    }
    public int getEid() {
        return eid;
    }
    public void setEid(int eid) {
        this.eid = eid;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
}
