package com.cmpt276.groupproject.models;

import jakarta.persistence.*;

@Entity
@Table(name="goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int gid;
    private int uid;
    private String item;
    private double amount;
    private int time;
    public Goal() {
    }
    
    public Goal(int uid, String item, double amount, int time) {
        this.uid = uid;
        this.item = item;
        this.amount = amount;
        this.time = time;
    }

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }
    
    

}
