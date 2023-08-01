package com.cmpt276.groupproject.models;

public class Metric {

    private float currentRatio;
    private float quickRatio;
    private float debtEquity;
    private float dividendYield;

    public Metric() {
    }

    public Metric(float currentRatio, float quickRatio, float debtEquity, float dividendYield) {
        this.currentRatio = currentRatio;
        this.quickRatio = quickRatio;
        this.debtEquity = debtEquity;
        this.dividendYield = dividendYield;
    }

    public float getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(float currentRatio) {
        this.currentRatio = currentRatio;
    }

    public float getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(float quickRatio) {
        this.quickRatio = quickRatio;
    }

    public float getDebtEquity() {
        return debtEquity;
    }

    public void setDebtEquity(float debtEquity) {
        this.debtEquity = debtEquity;
    }

    public float getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(float dividendYield) {
        this.dividendYield = dividendYield;
    }

    
    

    
    
}
