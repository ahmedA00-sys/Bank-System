package com.bank.system.Account;
import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.system.Bank_System.bank_System;
import com.bank.system.Users.Client;
import com.bank.system.transactions.Transaction;
import com.bank.system.transactions.enTransactionType;

import static com.bank.system.Bank_System.bank_System.SearchForClientByAccountNumber;

public abstract class Account {
    private static int AccountCounter = 0;
    protected String AccountNumber;
    protected enAccountState StateOfTheAccount;
    protected enAccountType AccountType;
    protected float Balance;
    protected LocalDate StartDate;
    private String UserName;
    private String Password;
    // Constructor

    //when Load Data
    public Account(String AccountNumber, enAccountType accountType, float balance, enAccountState stateOfTheAccount, LocalDate StartDate,String UserName,String Password) {
        this.AccountNumber = AccountNumber;
        this.AccountType = accountType;
        this.Balance = balance;
        this.StateOfTheAccount = stateOfTheAccount;
        this.StartDate = StartDate;
        this.UserName=UserName;
        this.Password=Password;

        int number=Integer.parseInt(AccountNumber.substring(3));

        if(AccountCounter<number){
            AccountCounter=number;
        }
    }

    //When Create New Account
    public Account(enAccountType accountType, float balance,String UserName,String Password) {
        AccountNumber = "AC-" + (++AccountCounter);
        AccountType = accountType;
        Balance = balance;
        StateOfTheAccount = enAccountState.Active;
        this.UserName=UserName;
        this.Password=Password;
        this.StartDate = LocalDate.now();
    }

    //Getter

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public String getAccountTypeForTable() {
        return AccountType.toString();
    }

    public enAccountType getAccountType() {
        return AccountType;
    }

    public float getBalance() {
        return Balance;
    }


    public LocalDate getStartDate() {
        return StartDate;
    }

    public String getStateOfTheAccountForTable() {
        return StateOfTheAccount.toString();
    }

    public enAccountState getStateOfTheAccount() {
        return StateOfTheAccount;
    }
//Setter

    public void setBalance(float balance) {
        this.Balance = balance;
    }

    public void setStateOfTheAccount(enAccountState stateOfTheAccount) {
        StateOfTheAccount = stateOfTheAccount;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    //Method

    public ArrayList<Transaction> FindTransactionHistory() {

        ArrayList<Transaction> TransactionHistory = new ArrayList<>();
        for (Transaction transaction : bank_System.transactionsList) {
            if (transaction.getSourceAccount().equals(this.AccountNumber)||transaction.getBeneficiaryAccountNumber().equals(this.AccountNumber)) {
                TransactionHistory.add(transaction);
            }
        }
        return TransactionHistory;
    }

    public String Deposit(float Amount) {
        Balance += Amount;
        Client client = SearchForClientByAccountNumber(AccountNumber);
        Transaction Transaction = new Transaction(Amount, enTransactionType.Deposit,AccountNumber, client.getClientNumber(),client.getFName(), AccountNumber,client.getFullName());
        bank_System.transactionsList.add(Transaction);
        return "Deposit successful. Your current balance is " + Balance + ".";
    }

    public abstract String WithDraw(float Amount);

    public abstract String TransferMoney(float Amount, String DestinationAccount);


}

