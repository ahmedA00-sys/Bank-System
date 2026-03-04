package com.bank.system.Users;

import com.bank.system.Bank_System.bank_System;
import com.bank.system.transactions.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.bank.system.Bank_System.bank_System.*;

public class Admin {
    private final String UserName="admin";
    private final String Password="admin";

    // Methods

    // Create new employee
    public String CreateNewEmployee(String ID, String firstName, String lastName, String address,
                                    String position, String graduatedCollege,
                                    LocalDate yearOfGraduation, String totalGrade,String userName,String password) {

        if (!ISEmployeeExist(ID)) {
            Employee NewEmployee=new Employee(ID,firstName,lastName,address,position,graduatedCollege,yearOfGraduation,totalGrade,userName,password);
            EmployeesList.add(NewEmployee);
            return "The Employee Account has been created successfully.";
        }

        return "Employee is already Exist.";
    }


}

