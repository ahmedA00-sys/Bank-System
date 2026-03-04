package com.bank.system.Users;

import com.bank.system.Account.Account;
import com.bank.system.Account.CurrentAccount;
import com.bank.system.Account.SavingAccount;
import com.bank.system.Account.enAccountType;
import com.bank.system.Bank_System.bank_System;
import com.bank.system.transactions.Transaction;
import com.bank.system.transactions.enTransactionType;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.bank.system.Bank_System.bank_System.SearchForClientByAccountNumber;
import static com.bank.system.Bank_System.bank_System.SearchForClientByID;

public class Employee {

    // Static counter to generate unique employee numbers.
    private static int Counter = 0;

   //Attribute
    private String EmployeeNumber;
    private final String ID;
    private String firstName;
    private String lastName;
    private String address;
    private String position;
    private String graduatedCollege;
    private LocalDate yearOfGraduation;
    private String totalGrade;
    private String UserName;
    private String password;

    // Constructor

    // Constructor for loading existing employees from a file.
    public Employee(String EmployeeNumber, String ID, String firstName, String lastName, String address,
                    String position, String graduatedCollege,
                    LocalDate yearOfGraduation, String totalGrade,String userName,String password) {
        this.EmployeeNumber = EmployeeNumber;
        this.ID = ID ;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address =address;
        this.position = position;
        this.graduatedCollege = graduatedCollege;
        this.yearOfGraduation = yearOfGraduation;
        this.totalGrade = totalGrade;
        this.UserName=userName;
        this.password=password;
        Counter++;
    }

    // Constructor for creating a new employee.
    public Employee(String ID, String firstName, String lastName, String address,
                    String position, String graduatedCollege,
                    LocalDate yearOfGraduation, String totalGrade,String userName,String password) {
        this.EmployeeNumber = "EM-"+(++Counter);
        this.ID = ID ;
        this.firstName =firstName;
        this.lastName =lastName;
        this.address =address;
        this.position = position;
        this.graduatedCollege = graduatedCollege;
        this.yearOfGraduation = yearOfGraduation;
        this.totalGrade =totalGrade;
        this.UserName=userName;
        this.password=password;
    }

    // Getters

    public String getEmployeeNumber() {
        return EmployeeNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return firstName+" "+lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPosition() {
        return position;
    }

    public String getGraduatedCollege() {
        return graduatedCollege;
    }

    public LocalDate getYearOfGraduation() {
        return yearOfGraduation;
    }

    public String getYearOfGraduationForTable() {
        return  yearOfGraduation.toString() ;
    }



    public String getTotalGrade() {
        return totalGrade;
    }

    // Setters to update address and position.
    public void setAddress(String address) {
        this.address =address;
    }

    public void setPosition(String position) {
        this.position =position;
    }

    //Method


    // Convert employee details into a line for saving in files.

    public String toLine() {
        return EmployeeNumber + "," + ID + "," + firstName + "," + lastName + "," +
                address + "," + position + "," + graduatedCollege + "," + yearOfGraduation
                + "," + totalGrade + "," + UserName + "," + password;
    }


    // Delete a client account.

    public String DeleteClientAccount(String AccountNumber) {
        Client client = SearchForClientByAccountNumber(AccountNumber);// Find the client.

        if (client != null) {
            bank_System.ClientsList.remove(client);  // Remove the client from the list.
            return "Account " + AccountNumber + " deleted successfully.";
        }

        return "Account " + AccountNumber + " not found.";
    }


    // Create a new client account.

    public String CreateClientAccount(String FName, String ID, String LName, String password, String phone,
                                      String userName, String Address, enAccountType accountType, float balance) {
        Account account;

        // Create account type based on AccountType.

        if (accountType == enAccountType.CURRENT) {
            account = new CurrentAccount(balance,userName,password);
        } else {
            account = new SavingAccount(balance,userName,password);
        }

        Client SClient = SearchForClientByID(ID); // Search for an existing client with the same ID.
        Client client;

        if (SClient != null) {
            // If client exists, The account take same client number.
            client = new Client(SClient.getClientNumber(), FName, ID, LName, phone,Address, account);
            // Update the old Client account
            SClient.setFName(FName);
            SClient.setLName(LName);
            SClient.setAddress(Address);
        }
        else {
            // Otherwise, create a new client.
            client = new Client(FName,ID, LName, phone, Address, account);
        }

        bank_System.ClientsList.add(client);              // Add the client to Clients List.

        return "The account has been created successfully.";
    }

    // Deposit money into a client's account.

    public String Deposit(String AccountNumber, float Amount) {
        Client client = SearchForClientByAccountNumber(AccountNumber);

        client.getAccount().setBalance(Amount + client.getAccount().getBalance());// Update the balance
        Transaction Transaction = new Transaction(Amount, enTransactionType.Deposit, AccountNumber, EmployeeNumber, getFullName(), AccountNumber, client.getFullName());
        bank_System.transactionsList.add(Transaction);            // Add transaction to transactions list .
        return "Deposit successful. The current balance is " + client.getAccount().getBalance() + ".";

    }

    // Withdraw money from a client's account.

    public  String WithDraw(String AccountNumber,float Amount) {
        Client client = SearchForClientByAccountNumber(AccountNumber);
        client.getAccount().setBalance(client.getAccount().getBalance() - Amount);
        Transaction Transaction = new Transaction(Amount, enTransactionType.Withdraw, AccountNumber, EmployeeNumber, getFullName(), AccountNumber, client.getFullName());
        bank_System.transactionsList.add(Transaction);// Add transaction to transactions list .
        return "Withdrawal successful. The current balance is " + client.getAccount().getBalance() + ".";

    }


    // Transfer money between accounts.

    public  String TransferMoney(String SourceAccount,float Amount, String DestinationAccount){
        Client SClient = SearchForClientByAccountNumber(SourceAccount);
        Client Dclient = SearchForClientByAccountNumber(DestinationAccount);

        // Update the balance
        SClient.getAccount().setBalance(SClient.getAccount().getBalance() - Amount);
        Dclient.getAccount().setBalance(Dclient.getAccount().getBalance() + Amount);
        // Add transaction to transactions list .
        Transaction Transaction = new Transaction(Amount, enTransactionType.Transfer, SourceAccount, EmployeeNumber, getFullName(), DestinationAccount, Dclient.getFullName());
        bank_System.transactionsList.add(Transaction);

        return "Transfer Money To " + DestinationAccount + " successfully.";
    }

}

