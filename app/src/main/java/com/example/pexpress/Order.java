package com.example.pexpress;
import java.time.LocalDateTime;
import java.util.Date;

public class Order {

    public String status;
    public String userEmail;
    public String country_from;
    public String address_to;
    public Date date;

    public Order(String status, String userEmail, String country_from, String address_to) {
        this.status = status;
        this.userEmail = userEmail;
        this.date = new Date();
        this.country_from = country_from;
        this.address_to = address_to;
    }

    public Order(){}

//    public Order( String address_to, String country_from,Date date,String status, String userEmail) {
//        this.status = status;
//        this.userEmail = userEmail;
//        this.date = date;
//        this.country_from = country_from;
//        this.address_to = address_to;
//    }

public String print(){
      return status+userEmail+country_from+address_to;
}


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountry_from() {
        return country_from;
    }

    public void setCountry_from(String country_from) {
        this.country_from = country_from;
    }

    public String getAddress_to() {
        return address_to;
    }

    public void setAddress_to(String address_to) {
        this.address_to = address_to;
    }
//    SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd");

}
