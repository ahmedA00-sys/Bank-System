package com.bank.system.Files;

import com.bank.system.Account.CreditCard;
import com.bank.system.Users.Client;
import com.bank.system.Users.Employee;
import com.bank.system.transactions.Transaction;

import java.util.ArrayList;

import static com.bank.system.Bank_System.bank_System.ClientsList;

public class DataToLine {

    public static ArrayList<String>EmployeeDataToLines(ArrayList<Employee>EmployeesList){
        ArrayList<String>Lines=new ArrayList<>();
        for (Employee employee:EmployeesList){
            Lines.add(employee.toLine());
        }
        return Lines;
    }

    public static ArrayList<String>TransactionDataToLines(ArrayList<Transaction>TransactionsList){
        ArrayList<String>Lines=new ArrayList<>();
        for (Transaction transaction:TransactionsList){
            Lines.add(transaction.Toline());
        }
        return Lines;
    }

    public static ArrayList<String>ClientsDataToLines(ArrayList<Client>ClientsList){
        ArrayList<String>Lines=new ArrayList<>();
        for (Client client: ClientsList){
            Lines.add(client.Toline());
        }
        return Lines;
    }

    public static ArrayList<String>CreditCardListDataToLine(ArrayList<CreditCard>CreditCardList){
        ArrayList<String>Lines=new ArrayList<>();
        for (CreditCard creditCard:CreditCardList ){
            Lines.add(creditCard.ToLine());
        }
        return Lines;
    }
}
