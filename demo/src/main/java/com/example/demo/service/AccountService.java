package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.example.demo.dao.BankAccountDAO;
import com.example.demo.model.BankAccount;

@Service
public class AccountService {
	@Autowired
	private BankAccountDAO accountDAO;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public AccountService(BankAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
			
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String[] createBankAccount(BankAccount account) throws SQLException {
		  final String[] result_array = new String[2];

		  
		  boolean status =  accountDAO.InsertAccount(account);
		  if (status) {
			  result_array[0] = "200";
			  result_array[1] = "Create account successfully";
			  return result_array;

		  }
		  
		  //System.out.println("Inserted user failed with email: "+ (new_user.getEmail()));
		  result_array[0] = "400";
		  result_array[1] = "Insertion failed due to unkown reasons";
		  
		  return result_array;
		  
	}
	
	public List<BankAccount> findUserBankAccounts(int user_id) throws SQLException {

	    List<BankAccount> user_accounts = accountDAO.queryForAccountWithUserId(user_id);
		return user_accounts;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void twDepositTrading() {
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void forignDepositTrading() {
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void tradingLog() {
		
	}
	

}
