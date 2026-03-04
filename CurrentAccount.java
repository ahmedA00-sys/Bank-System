package com.bank.system.Account;

import com.bank.system.Bank_System.bank_System;
import com.bank.system.Users.Client;
import com.bank.system.transactions.Transaction;
import com.bank.system.transactions.enTransactionType;

import java.time.LocalDate;

import static com.bank.system.Bank_System.bank_System.SearchForClientByAccountNumber;

public class CurrentAccount extends Account {
    private  final float MIN_BALANCE = 3000;
    private  final float FEE = 50;

// When Load Data

    public CurrentAccount(String AccountNumber, float balance, enAccountState stateOfTheAccount, LocalDate StartDate,String UserName,String Password) {
        super( AccountNumber, enAccountType.CURRENT, balance, stateOfTheAccount, StartDate,UserName,Password);
    }

    // When Create New Account

    public CurrentAccount(float balance,String UserName,String Password) {
        super(enAccountType.CURRENT, balance,UserName,Password);
    }

    public float getFEE() {
        return FEE;
    }

    public float getMIN_BALANCE() {
        return MIN_BALANCE;
    }


    @Override
    public  String WithDraw(float Amount){
        Client client = SearchForClientByAccountNumber(AccountNumber);
        Transaction Transaction=new Transaction(Amount, enTransactionType.Withdraw,super.AccountNumber,client.getClientNumber(),client.getFName(),AccountNumber,client.getFullName());
        bank_System.transactionsList.add(Transaction);
        super.Balance-=Amount;
        return "Withdrawal successful. Your current balance is " + super.Balance + ".";

    }

    @Override
    public  String TransferMoney(float Amount, String DestinationAccount) {
        Client Dclient = SearchForClientByAccountNumber(DestinationAccount);
            super.Balance -= Amount;
            Dclient.getAccount().setBalance(Dclient.getAccount().getBalance()+Amount);
            Client Iclient = SearchForClientByAccountNumber(AccountNumber);
            Transaction Transaction = new Transaction(Amount, enTransactionType.Transfer,super.AccountNumber,Iclient.getClientNumber(),Iclient.getFName(), super.AccountNumber,Dclient.getClientNumber());
            bank_System.transactionsList.add(Transaction);
            return "Transfer Money To " + DestinationAccount + " successful Your current balance is " + super.Balance + ".";

    }

}
