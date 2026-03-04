package com.GUI.Controller;

import com.bank.system.Bank_System.bank_System;

public class LoginControl {

  public static enUsers IsLogin(String UserName, String Password){

       if(UserName.equals("admin")&&Password.equals("admin")){
           return enUsers.Admin;
       }
       else if(bank_System.IsClientExist(UserName,Password)){
           return enUsers.Client;
       }
       else if(bank_System.IsEmployeeExist(UserName,Password)){
           return enUsers.Employee;
       }

       return null;
   }
}
