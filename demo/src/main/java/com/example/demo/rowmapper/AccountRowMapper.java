package com.example.demo.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.beans.BankAccount;


 public class AccountRowMapper implements RowMapper<BankAccount>{
 
     @Override
     public BankAccount mapRow(ResultSet resultSet, int i) throws SQLException {
         //      獲取account rows的物件
         int user_id = resultSet.getInt("USER_ID");
         float tw_deposit = resultSet.getFloat("TW_DEPOSIT");
         float forign_deposit = resultSet.getFloat("FORIGN_DEPOSIT");
         String account_number = resultSet.getString("ACCOUNT_NUMBER");
 //      封裝成account
         BankAccount bankAccount = new BankAccount();
         bankAccount.setUser_id(user_id);
         bankAccount.setTw_deposit(tw_deposit);
         bankAccount.setForeign_deposit(forign_deposit);
         bankAccount.setAccount_number(account_number);
        return bankAccount;
     }
 }


