package com.example.readingisgood.dto.statistics;

public class StatisticsItemDto {

    private String month;
    private int totalOrderCount;
    private int totalBookCount;
    private Float totalPurchasedAmount;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(int totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public int getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(int totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public Float getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public void setTotalPurchasedAmount(Float totalPurchasedAmount) {
        this.totalPurchasedAmount = totalPurchasedAmount;
    }
}
