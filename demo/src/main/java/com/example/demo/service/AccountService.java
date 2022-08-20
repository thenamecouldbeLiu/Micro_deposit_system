package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.beans.BankAccount;

@Service
public interface AccountService {

	

	String[] createBankAccount(BankAccount account) throws Exception;

	List<BankAccount> findUserBankAccounts(int user_id) throws Exception;

	

	

}
