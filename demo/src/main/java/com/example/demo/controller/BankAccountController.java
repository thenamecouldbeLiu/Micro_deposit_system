package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BankAccount;

import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {
    
    public int genAccountNumber() {//回一個隨機的9位數帳號
        int min = 100000000; 
        int max = 999999999;
        
        int account_num = (int)Math.floor(Math.random()*(max-min+1)+min);
        
        return account_num;
    }
	
	@Autowired
	AccountService accountService;
	
	
	@PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody BankAccount new_account, HttpSession session) throws SQLException {
		//System.out.print("first line");
	    
	    int account_num = this.genAccountNumber();
	    
		String account_num_str = String.valueOf(account_num);
		
		int user_id = (int) session.getAttribute("user_id");
		
		new_account.setAccount_number(account_num_str);
		new_account.setUser_id(user_id);
		//System.out.print("2 line");
		String[] create_status = accountService.createBankAccount(new_account);
		//System.out.print("3 line");
		
		if (create_status[0] == "200") {
		return ResponseEntity.ok(create_status[1]); // idx 1 是訊息回報
		}
		
		return new ResponseEntity<>(create_status[1], HttpStatus.BAD_REQUEST);
		
    }
	
	
	@PostMapping("/getUserAccounts") //要找各種存款直接回這裡 從前端處理計算較好
    public List<BankAccount> getUserAccounts(HttpSession session) throws SQLException {
		int user_id = (int) session.getAttribute("user_id");
		List<BankAccount> bankAccountsArray = accountService.findUserBankAccounts(user_id);

        return bankAccountsArray;
    }
	
	

}
