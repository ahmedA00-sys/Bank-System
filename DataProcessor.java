package com.bank.system.Files;

import com.bank.system.Account.*;
import com.bank.system.Users.Client;
import com.bank.system.Users.Employee;
import com.bank.system.transactions.Transaction;
import com.bank.system.transactions.enTransactionType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DataProcessor {

    public static ArrayList<Client> processClientData(ArrayList<String> rawData) {
        ArrayList<Client> clients = new ArrayList<>();
        for (String line : rawData) {
            try {
                String[] fields = line.split(","); // Split line into fields using ','

                Account account;
                enAccountType type = enAccountType.valueOf(fields[7]);

                if (type == enAccountType.CURRENT) {
                    account = new CurrentAccount(fields[6], Float.parseFloat(fields[8]), enAccountState.valueOf(fields[9]), LocalDate.parse(fields[10], DateTimeFormatter.ofPattern("yyyy-MM-dd")),fields[12],fields[11]);
                } else
                    account = new SavingAccount(fields[6], Float.parseFloat(fields[8]), enAccountState.valueOf(fields[9]), LocalDate.parse(fields[10], DateTimeFormatter.ofPattern("yyyy-MM-dd")),fields[12],fields[11]);

                Client client = new Client(
                        fields[0],                  //ClientNumber
                        fields[1],                  // First Name
                        fields[2],                  //ID
                        fields[3],                  // Last Name
                        fields[4],                  // Phone
                        fields[5],                  // Address
                        account                     // Account
                );

                clients.add(client); // Add client to the list
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clients;
    }

    public static ArrayList<Employee> processEmployeeData(ArrayList<String> rawData) {
        ArrayList<Employee> employees = new ArrayList<>();
        for (String line : rawData) {
            try {
                String[] fields = line.split(","); // Split line into fields using ','

                // Handle date parsing that might include single-digit month
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate yearOfGraduation = LocalDate.parse(fields[7], dateFormatter);

                Employee employee = new Employee(
                        fields[0],                  // EmployeeNumber
                        fields[1],// ID
                        fields[2],                  // First Name
                        fields[3],                  // Last Name
                        fields[4],                  // Address
                        fields[5],                  // Position
                        fields[6],                  // Graduate College
                        yearOfGraduation,           // Year of Graduation
                        fields[8],                  // Total Grade
                        fields[9],                  // User Name
                        fields[10]                  // Password
                );

                employees.add(employee); // Add employee to the list
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return employees;
    }


    public static ArrayList<Transaction> processTransactionData(ArrayList<String> rawData) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (String line : rawData) {
            try {
                String[] fields = line.split(","); // Split line into fields using ','

                Transaction transaction = new Transaction(
                        fields[0], //Transaction Number
                        Float.parseFloat(fields[1]), // Amount
                        enTransactionType.valueOf(fields[2]), // Transaction Type
                        fields[3], // Source Account
                        fields[4], // Initiated By User Number
                        fields[5], // Initiated Name
                        fields[6], // Beneficiary Account Number
                        fields[7], // Beneficiary Name
                        LocalDate.parse(fields[8], DateTimeFormatter.ofPattern("yyyy-MM-dd")) // Transaction Date
                );

                transactions.add(transaction); // Add transaction to the list
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return transactions;
    }

    public static ArrayList<CreditCard> processCreditCardData(ArrayList<String> rawData) {
        ArrayList<CreditCard> creditCards = new ArrayList<>();
        for (String line : rawData) {
            try {
                String[] fields = line.split(",");
                boolean isActive = fields[3].equals("true") ? true : false;
                CreditCard creditCard =new CreditCard(fields[0],fields[1],Float.parseFloat(fields[2]),isActive,Integer.parseInt(fields[4]));
                creditCards.add(creditCard); // Add transaction to the list
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return creditCards;
    }

}



