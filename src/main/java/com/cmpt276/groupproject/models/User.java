package com.cmpt276.groupproject.models;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private String password;
    private double balance;
    private double monthlyincome;

    public User(){
        
    }

    public User(String name, String password, double balance, double monthlyincome) {
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.monthlyincome = monthlyincome;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMonthlyincome() {
        return monthlyincome;
    }

    public void setMonthlyincome(double monthlyincome) {
        this.monthlyincome = monthlyincome;
    }

   
    
    
}
