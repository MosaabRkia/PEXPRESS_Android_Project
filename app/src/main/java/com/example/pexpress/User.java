package com.example.pexpress;

public class User {
    String firstName,lastName,rank;
    int i =0;
    int j=0;
    public static int id=0;
    int bornYear,bornDay,bornMonth;
    FamilyUser[] familyList;
    Order[] orderList;

    public User(String firstName,String lastName, String rank ,int bornYear,int bornDay,int bornMonth){
        id=id++;
        setFirstName(firstName);
        setLastName(lastName);
        setRank("member");
        setBornDay(bornDay);
        setBornMonth(bornMonth);
        setBornYear(bornYear);
    }

    public void addFamilyUser(int idNumber,String firstName,String lastName){
        familyList[i] = new FamilyUser(idNumber,firstName,lastName);
        i++;
    }

    public void addOrder(String orderId, String status, int bornYear, int bornDay, int bornMonth){
        orderList[j] = new Order( orderId,  status,  bornYear,  bornDay,  bornMonth);
        j++;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public FamilyUser[] getFamilyList() {
        return familyList;
    }



    public Order[] getOrderList() {
        return orderList;
    }

    public String toString(){
        return ("full name :" + getFirstName() + " " + getLastName() + "\n birthday: "+ getBornDay()+"/"+getBornMonth()+"/"+getBornYear());
    }
}
