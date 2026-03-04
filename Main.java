package com.bank.system;

import com.bank.system.Bank_System.bank_System;
import com.bank.system.Files.DataProcessor;
import com.bank.system.Files.DataToLine;
import com.bank.system.Files.File;

import com.bank.system.Users.Admin;

public class Main {
    public static void main(String[] args) {
        // Load Data From File
        bank_System.ClientsList= DataProcessor.processClientData(File.LoadData("ClientsList"));
        bank_System.EmployeesList=DataProcessor.processEmployeeData(File.LoadData("EmployeesList"));
        bank_System.transactionsList=DataProcessor.processTransactionData(File.LoadData("TransactionsList"));
        bank_System.CreditCardList=DataProcessor.processCreditCardData(File.LoadData("CreditCardList"));


        // Sava Data To File
        File.SaveData("ClientsList",DataToLine.ClientsDataToLines(bank_System.ClientsList));
        File.SaveData("EmployeesList",DataToLine.EmployeeDataToLines(bank_System.EmployeesList));
        File.SaveData("TransactionsList",DataToLine.TransactionDataToLines(bank_System.transactionsList));
        File.SaveData("CreditCardList",DataToLine.CreditCardListDataToLine(bank_System.CreditCardList));

    }
}







