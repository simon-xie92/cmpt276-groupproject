package com.cmpt276.groupproject.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    private String symbol;
    private int quantity;
    private double purchasePrice;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;


    public Stock() {
    }


    


    public Stock(int id, User user, String symbol, int quantity, double purchasePrice, LocalDateTime entryTime) {
        this.id = id;
        this.user = user;
        this.symbol = symbol;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.entryTime = entryTime;
    }





    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public String getSymbol() {
        return symbol;
    }


    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double getPurchasePrice() {
        return purchasePrice;
    }


    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }





    public LocalDateTime getEntryTime() {
        return entryTime;
    }





    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }


    
    

    

    
}
    

