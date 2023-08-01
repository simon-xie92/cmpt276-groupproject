package com.cmpt276.groupproject.models;



public class CandleStickData {

    private double[] c;
    private double[] h;
    private double[] l;
    private double[] o;
    private String s;
    private double[] t;
    private double[] v;

    

    public CandleStickData() {
    }

    public CandleStickData(double[] c, double[] h, double[] l, double[] o, String s, double[] t, double[] v) {
        this.c = c;
        this.h = h;
        this.l = l;
        this.o = o;
        this.s = s;
        this.t = t;
        this.v = v;
    }

    public double[] getC() {
        return c;
    }

    public void setC(double[] c) {
        this.c = c;
    }

    public double[] getH() {
        return h;
    }

    public void setH(double[] h) {
        this.h = h;
    }

    public double[] getL() {
        return l;
    }

    public void setL(double[] l) {
        this.l = l;
    }

    public double[] getO() {
        return o;
    }

    public void setO(double[] o) {
        this.o = o;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public double[] getT() {
        return t;
    }

    public void setT(double[] t) {
        this.t = t;
    }

    public double[] getV() {
        return v;
    }

    public void setV(double[] v) {
        this.v = v;
    }

    


    
}
