package com.example.calculator;

public class Calc {
    private double billAmount;
    private double percent;

    public double calculateTip(double billAmount, double percent) {
        return billAmount*(1+percent);
    }

    public double calculateTotal(double billAmount, double percent) {
        return billAmount * (1+percent);
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }
    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }


}
