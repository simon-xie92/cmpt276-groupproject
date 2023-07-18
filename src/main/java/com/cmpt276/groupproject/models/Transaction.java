package com.cmpt276.groupproject.models;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;
    private int uid;
    private LocalDate date;
    private String reason;
    private String type;
    private double amount;

    public Transaction() {
    }

    public Transaction(int uid, LocalDate date, String reason, double amount, String type) {
        this.uid = uid;
        this.date = date;
        this.reason = reason;
        this.amount = amount;
        this.type = type;
    }
    
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
