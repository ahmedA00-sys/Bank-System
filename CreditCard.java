package com.bank.system.Account;
import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.system.Bank_System.bank_System;
import com.bank.system.Users.Client;
import com.bank.system.transactions.Transaction;
import com.bank.system.transactions.enTransactionType;

import static com.bank.system.Bank_System.bank_System.SearchForClientByAccountNumber;

public class CreditCard {
    private int Counter=1;
    private String cardNumber;
    private String cardHolderClientNumber;
    private final float MAX_LIMIT = 20000f;
    public float availableLimit;
    boolean isActive;
    int loyaltyPoints;
    private final float loyaltypointCash =0.1f;

    //Load
    public CreditCard(String cardNumber, String cardHolderAccountNumber,float availableLimit,boolean isActive,int loyaltyPoints) {
        this.cardNumber = cardNumber;
        this.cardHolderClientNumber=cardHolderAccountNumber;
        this.availableLimit=availableLimit;
        this.isActive=isActive;
        this.loyaltyPoints=loyaltyPoints;

        int number=Integer.parseInt(cardNumber.substring(3));
        if(Counter<number){
            Counter=number;
        }

    }
    //new
    public CreditCard(String cardHolderAccountNumber) {
        cardNumber="CR-"+Counter++;
        this.cardHolderClientNumber=cardHolderAccountNumber;
        availableLimit=20000;
        isActive=true;
        loyaltyPoints=0;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCounter() {
        return Counter;
    }

    public boolean isActive() {
        return isActive;
    }

    public float getMAX_LIMIT() {
        return MAX_LIMIT;
    }

    public String getcardHolderClientNumber() {
        return cardHolderClientNumber;
    }

    public double getAvailableLimit() {
        return availableLimit;
    }

    public String getCardHolderClientNumber() {
        return cardHolderClientNumber;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setcardHolderClientNumber(String cardHolderAccountNumber) {
        this.cardHolderClientNumber = cardHolderAccountNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCounter(int counter) {
        Counter = counter;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }


    public void disable() {
        isActive = false;
    }

    public void pay(double amount) {
        availableLimit -= amount;
        loyaltyPoints += 5;
    }

    public void PointsToCash(){
        availableLimit+=loyaltyPoints*loyaltypointCash;
    }

    public String ToLine() {
        return cardNumber + "," +
                cardHolderClientNumber + "," +
                availableLimit+","+
                isActive + "," +
                loyaltyPoints;
    }


}