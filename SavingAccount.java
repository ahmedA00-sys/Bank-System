package com.bank.system.Account;

import com.bank.system.Bank_System.bank_System;
import com.bank.system.Users.Client;
import com.bank.system.transactions.Transaction;
import com.bank.system.transactions.enTransactionType;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.bank.system.Bank_System.bank_System.SearchForClientByAccountNumber;

public class SavingAccount extends Account{
    private final float INTEREST_RATE = 0.07f;
    //Load
    public SavingAccount( String AccountNumber, float balance, enAccountState stateOfTheAccount, LocalDate StartDate ,String UserName,String Password) {
        super( AccountNumber, enAccountType.SAVING, balance, stateOfTheAccount, StartDate,UserName,Password);
        if(LocalDate.now().equals(StartDate.plusYears(1))){
            super.setBalance(balance+balance*0.07f);
        }
    }
    // Create New Account
    public SavingAccount(float balance ,String UserName,String Password) {

        super(enAccountType.SAVING, balance,UserName,Password);
    }

    public float getINTEREST_RATE() {
        return INTEREST_RATE;
    }

    @Override
    public  String WithDraw(float Amount){
        if(super.Balance<Amount){
            return "You cannot withdraw the amount because your balance is insufficient.";
        }
        Client client = SearchForClientByAccountNumber(AccountNumber);

        Transaction Transaction=new Transaction(Amount, enTransactionType.Withdraw,super.AccountNumber,client.getClientNumber(),client.getFName(),AccountNumber,client.getFullName());
        bank_System.transactionsList.add(Transaction);

        super.Balance-=Amount;
        return "Withdrawal successful. Your current balance is " + super.Balance + ".";

    }

    @Override
    public String TransferMoney(float Amount, String DestinationAccount) {
        Client Dclient = SearchForClientByAccountNumber(DestinationAccount);
        Client Iclient = SearchForClientByAccountNumber(AccountNumber);
        Iclient.getAccount().setBalance(getBalance()-Amount);
        Dclient.getAccount().setBalance(Dclient.getAccount().getBalance() + Amount);
        Transaction Transaction = new Transaction(Amount, enTransactionType.Transfer, super.AccountNumber, Iclient.getClientNumber(), Iclient.getFName(), super.AccountNumber, Dclient.getClientNumber());
        bank_System.transactionsList.add(Transaction);
        return "Transfer Money To " + DestinationAccount + " successful Your current balance is " + Iclient.getAccount().getBalance() +".";
    }

}
