package com.bank.system.Bank_System;

import com.bank.system.Files.DataProcessor;
import com.bank.system.Files.File;
import com.bank.system.Users.Client;
import com.bank.system.Users.Employee;
import com.bank.system.transactions.Transaction;
import com.bank.system.transactions.enTransactionType;
import com.bank.system.Account.*;

import javax.lang.model.element.Name;
import java.time.LocalDate;
import java.util.ArrayList;

public class bank_System {

    public static ArrayList<Transaction> transactionsList;
    public static ArrayList<Client> ClientsList;
    public static ArrayList<Employee> EmployeesList;
    public static ArrayList<CreditCard> CreditCardList;

    public static boolean IsUserNameExist(String UserName) {
        for (Employee employee : EmployeesList) {
            if (employee.getUserName().equals(UserName)) {
                return true;
            }
        }
        for (Client client : ClientsList) {
            if (client.getAccount().getUserName().equals(UserName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsEmployeeExist(String UserName, String Password) {
        for (Employee employee : EmployeesList) {
            if (employee.getUserName().equals(UserName) && employee.getPassword().equals(Password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsClientExist(String UserName, String Password) {
        for (Client client : ClientsList) {
            if (client.getAccount().getUserName().equals(UserName) && client.getAccount().getPassword().equals(Password)) {
                return true;
            }
        }
        return false;
    }

    public  static boolean ISClientExist(String ClientNumber) {
        for (Client client : ClientsList) {
            if (client.getClientNumber().equals(ClientNumber)) {
                return true;
            }
        }
        return false;
    }

    public static boolean ISEmployeeExist(String ID) {
        for (Employee employee : EmployeesList) {
            if (employee.getID().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean ISEmployeeNumberExist(String EmployeeNumber) {
        for (Employee employee : EmployeesList) {
            if (employee.getEmployeeNumber().equals(EmployeeNumber)) {
                return true;
            }
        }
        return false;
    }

    public static Client SearchForClientByID(String ID) {
        for (Client client : ClientsList) {
            if (client.getID().equals(ID)) {
                return client;
            }
        }
        return null;
    }

    public static Employee SearchForEmployee(String UserName,String Password ) {
        for (Employee employee : EmployeesList) {
            if (employee.getUserName().equals(UserName)&&employee.getPassword().equals(Password)) {
                return employee;
            }
        }
        return null;
    }

    public static Client SearchForClient(String UserName,String Password ) {
        for (Client client : ClientsList) {
            if (client.getAccount().getUserName().equals(UserName)&&client.getAccount().getPassword().equals(Password)) {
                return client;
            }
        }
        return null;
    }

    public static ArrayList<Client> SearchForClientsByName(String Name) {
        ArrayList<Client> matchingClients = new ArrayList<>();
        for (Client client : ClientsList) {
            if (Name.equals(client.getFullName())) {
                matchingClients.add(client);
            }
        }
        return matchingClients.isEmpty() ? null : matchingClients;
    }

    public static Client SearchForClientByAccountNumber(String AccountNumber) {
        for (Client client : ClientsList) {
            if (client.getAccount().getAccountNumber().equals(AccountNumber)) {
                return client;
            }
        }
        return null;
    }

   // Display all employees
   public static   ArrayList<Employee> DisplayAllEmployees() {
      return (EmployeesList.isEmpty())?null:EmployeesList;
   }

   // Display all clients
   public static   ArrayList<Client> DisplayAllClients() {
       return (ClientsList.isEmpty())?null:ClientsList;
   }

   // Show transactions Done BY ....

   // Show transactions Done BY Employee/Client

   public static ArrayList<Transaction> ShowAllTransactionsDoneByUser(String UserNumber) {
        ArrayList<Transaction>Transactions=new ArrayList<>();
      for (Transaction transaction:transactionsList){
         if (transaction.getInitiatedByUserNumebr().equals(UserNumber)) {
            Transactions.add(transaction);
         }
      }
      return Transactions;
   }


   public static ArrayList<Transaction> ShowAllTransactionsDoneByData(LocalDate Data) {
      ArrayList<Transaction>Transactions=new ArrayList<>();
      for (Transaction transaction: transactionsList){
         if (transaction.getTransactionDate().equals(Data)) {
            Transactions.add(transaction);
         }
      }
      return Transactions;
   }

   public static boolean ISClientHaveCreditCard(String ClientNumber){
       for (CreditCard creditCard : CreditCardList) {
           if (creditCard.getcardHolderClientNumber().equals(ClientNumber)) {
               return true;
           }
       }
       return false;
   }

    public static CreditCard SearchClientHaveCreditCard(String ClientNumber){
        for (CreditCard creditCard : CreditCardList) {
            if (creditCard.getcardHolderClientNumber().equals(ClientNumber)) {
                return creditCard;
            }
        }
        return null;
    }

}
