package com.cmpt276.groupproject.models;

public class Earning {

    private String date;
    private float epsActual;
    private float epsEstimate;
    private float hour;
    private float quarter;
    private float revenueActual;
    private float revenueEstimate;
    private String symbol;
    private int year;


    public Earning() {
    }


    public Earning(String date, float epsActual, float epsEstimate, float hour, float quarter, float revenueActual,
            float revenueEstimate, String symbol, int year) {
        this.date = date;
        this.epsActual = epsActual;
        this.epsEstimate = epsEstimate;
        this.hour = hour;
        this.quarter = quarter;
        this.revenueActual = revenueActual;
        this.revenueEstimate = revenueEstimate;
        this.symbol = symbol;
        this.year = year;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public float getEpsActual() {
        return epsActual;
    }


    public void setEpsActual(float epsActual) {
        this.epsActual = epsActual;
    }


    public float getEpsEstimate() {
        return epsEstimate;
    }


    public void setEpsEstimate(float epsEstimate) {
        this.epsEstimate = epsEstimate;
    }


    public float getHour() {
        return hour;
    }


    public void setHour(float hour) {
        this.hour = hour;
    }


    public float getQuarter() {
        return quarter;
    }


    public void setQuarter(float quarter) {
        this.quarter = quarter;
    }


    public float getRevenueActual() {
        return revenueActual;
    }


    public void setRevenueActual(float revenueActual) {
        this.revenueActual = revenueActual;
    }


    public float getRevenueEstimate() {
        return revenueEstimate;
    }


    public void setRevenueEstimate(float revenueEstimate) {
        this.revenueEstimate = revenueEstimate;
    }


    public String getSymbol() {
        return symbol;
    }


    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public int getYear() {
        return year;
    }


    public void setYear(int year) {
        this.year = year;
    }


    
    
    
    
}
