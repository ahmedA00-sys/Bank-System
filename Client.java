package com.bank.system.Users;

import com.bank.system.Account.Account;
import com.bank.system.Bank_System.bank_System;

public class Client {
    private static int Max=0;
    private String ID;
    private String FName;
    private String LName;
    private String Phone;
    private String ClientNumber;
    private String Address;
    private Account account;
     //Constructor
//Load
    public Client(String clientNumber ,String FName, String ID, String LName, String phone,String Address,Account account) {
        this.ClientNumber=clientNumber;
        this.FName = FName;
        this.ID = ID;
        this.LName = LName;
        Phone = phone;
        this.Address=Address;
        this.account=account;

        int number=Integer.parseInt(ClientNumber.substring(3));
        if(Max<number){
            Max=number;
        }

    }

    // New Client
    public Client(String FName, String ID, String LName, String phone,String Address,Account account) {
    this.FName = FName;
    this.ID = ID;
    this.LName = LName;
    Phone = phone;
    this.Address=Address;
    this.account=account;
    this.ClientNumber= "CL-"+(++Max);
}
    //Getter
    public String getClientNumber() {
        return ClientNumber;
    }

    public String getFName() {
        return FName;
    }

    public String getID() {
        return ID;
    }

    public String getLName() {
        return LName;
    }

    public String getFullName() {
        return getFName()+" "+getLName();
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public Account getAccount() {
        return account;
    }

// Setter

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }


    public void setAddress(String address) {
        Address = address;
    }
    public String Toline() {
        return ClientNumber + "," + //0
                FName + "," +  //1
                ID + "," + //2
                LName + "," +//3
                Phone + "," +//4
                Address + "," +//5
                account.getAccountNumber() + "," +//6
                account.getAccountType() + "," +//7
                account.getBalance() + "," +//8
                account.getStateOfTheAccount() + "," +//9
                account.getStartDate()+","+//10
                account.getPassword()+","+//11
                account.getUserName();//12
    }

}
