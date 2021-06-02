package com.example.pexpress;

import java.io.FileInputStream;

public class FamilyUser {
    int idNumber;
    String firstName,lastName;

    public FamilyUser(int idNumber, String firstName, String lastName) {
        setIdNumber(idNumber);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
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
    public String toString() {
        return ("id Number : " + getIdNumber() +"\nfull name :"+ getFirstName() + getLastName() );
    }
}
