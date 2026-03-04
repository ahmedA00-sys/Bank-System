package com.GUI;

import com.bank.system.Bank_System.bank_System;
import com.bank.system.Files.DataProcessor;
import com.bank.system.Files.DataToLine;
import com.bank.system.Files.File;
import com.bank.system.Users.Client;
import com.bank.system.Users.Employee;
import com.bank.system.transactions.Transaction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainAPP extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/GUI/View/LoginForm.fxml"));
        AnchorPane root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
      bank_System.transactionsList = DataProcessor.processTransactionData(File.LoadData("TransactionsList"));
     bank_System.ClientsList  =DataProcessor.processClientData(File.LoadData("ClientsList"));;
     bank_System.EmployeesList =DataProcessor.processEmployeeData(File.LoadData("EmployeesList"));
     bank_System.CreditCardList =DataProcessor.processCreditCardData(File.LoadData("CreditCardList"));

        launch(args);
        // Sava Data To File
        File.SaveData("ClientsList", DataToLine.ClientsDataToLines(bank_System.ClientsList));
        File.SaveData("EmployeesList",DataToLine.EmployeeDataToLines(bank_System.EmployeesList));
        File.SaveData("TransactionsList",DataToLine.TransactionDataToLines(bank_System.transactionsList));
        File.SaveData("CreditCardList",DataToLine.CreditCardListDataToLine(bank_System.CreditCardList));

    }
}