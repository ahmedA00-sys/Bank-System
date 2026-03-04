package com.bank.system.transactions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int transactionCounter = 0;
    private String transactionNumber;
    private LocalDate transactionDate;
    private float amount;
    private enTransactionType transactionType;
    private String SourceAccount;
    private String InitiatedByUserNumebr;
    private String InitiatedName;
    private String BeneficiaryAccountNumber;
    private String BeneficiarynName;

    // Constructor

    // Load Data

    public Transaction(String transactionNumber,float amount,enTransactionType transactionType,String SourceAccount,String initiatedByUserNumebr,String InitiatedName
            ,  String beneficiaryAccountNumber,
                       String BeneficiarynName ,LocalDate transactionDate) {
        this.transactionNumber=transactionNumber;
        this.InitiatedByUserNumebr = initiatedByUserNumebr;
        this.InitiatedName=InitiatedName;
        this.amount = amount;
        BeneficiaryAccountNumber = beneficiaryAccountNumber;
        this.BeneficiarynName=BeneficiarynName;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.SourceAccount=SourceAccount;
        ++transactionCounter;
    }

    // Constructor for Creating a New Transaction
    public Transaction(float amount,enTransactionType transactionType,String SourceAccount,String initiatedByUserNumebr,String InitiatedName
           ,  String beneficiaryAccountNumber,String BeneficiarynName) {
        this.transactionNumber="TR-"+(++transactionCounter);
        this.InitiatedByUserNumebr = initiatedByUserNumebr;
        this.InitiatedName=InitiatedName;
        this.amount = amount;
        BeneficiaryAccountNumber = beneficiaryAccountNumber;
        this.BeneficiarynName=BeneficiarynName;
        this.transactionType = transactionType;
        this.SourceAccount=SourceAccount;
        this.transactionDate=LocalDate.now();
    }

    // Getters


    public String getSourceAccount() {
        return SourceAccount;
    }

    public float getAmount() {
        return amount;
    }

    public String getBeneficiaryAccountNumber() {
        return BeneficiaryAccountNumber;
    }

    public String getBeneficiarynName() {
        return BeneficiarynName;
    }

    public String getInitiatedByUserNumebr() {
        return InitiatedByUserNumebr;
    }

    public String getInitiatedName() {
        return InitiatedName;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionDateForTable() {
        return transactionDate.toString();
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public enTransactionType getTransactionType() {
        return transactionType;
    }

    //Methods

    public String Toline() {
        String formattedDate = transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return
                transactionNumber + "," +
                        amount + "," +
                        transactionType + "," +
                        SourceAccount + "," +
                        InitiatedByUserNumebr + "," +
                        InitiatedName + "," +
                        BeneficiaryAccountNumber + "," +
                        BeneficiarynName + "," +
                        formattedDate;
    }



}
