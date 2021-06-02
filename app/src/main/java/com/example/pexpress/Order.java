package com.example.pexpress;

public class Order {
    String orderId , status;
    int bornYear,bornDay,bornMonth;

    public Order(String orderId, String status, int bornYear, int bornDay, int bornMonth) {
       setOrderId(orderId);
        setStatus(status);
        setBornYear(bornYear);
        setBornDay(bornDay);
        setBornMonth(bornMonth);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    public int getBornDay() {
        return bornDay;
    }

    public void setBornDay(int bornDay) {
        this.bornDay = bornDay;
    }

    public int getBornMonth() {
        return bornMonth;
    }

    public void setBornMonth(int bornMonth) {
        this.bornMonth = bornMonth;
    }
    public String toString() {
        return ("order Id :" + getOrderId() + "\nstatus" + getStatus()+"\nOrder Date :" + getBornDay() + "/" + getBornMonth() + "/" + getBornYear());
    }
}
