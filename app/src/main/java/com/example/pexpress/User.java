package com.example.pexpress;

public class User {
    public String firstName;
    public String lastName;
  //  String rank;
  public  String email;
    public String password;
    public String uid;


    public User(){}

    public User(String firstName,String lastName, String email,String password,String UID) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setUID(UID);
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    public String toString(){
        return ("full name :" + getFirstName() + " " + getLastName() + "\n email: "+getEmail()  );
    }
    public String getUID() {
        return uid;
    }

    public void setUID(String uid) {
        this.uid = uid;
    }
}
