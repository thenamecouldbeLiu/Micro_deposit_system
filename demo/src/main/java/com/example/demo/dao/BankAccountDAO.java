package com.example.demo.dao;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.beans.BankAccount;
import com.example.demo.rowmapper.AccountRowMapper;

@Repository
public
class BankAccountDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate; // used for db operation in java

	@Autowired
	private NamedParameterJdbcTemplate jdbcNameTemplate;
	
	@Transactional(rollbackFor = Exception.class)
	public boolean InsertAccount(BankAccount account) throws SQLException{
		
		
		
		String sql_insert = "INSERT INTO HW_TRADING_ACCOUNT (USER_ID, TW_DEPOSIT, FOREIGN_DEPOSIT, ACCOUNT_NUMBER, ACCOUNT_CREATE_DATE) "+"VALUES (:user_id,:tw_deposit,:forign_deposit,:account_number, CURRENT_TIMESTAMP)";

		//System.out.println(sql_insert);
	
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(account); //bean parameter inject to sql statement
		
		int status = jdbcNameTemplate.update(sql_insert, paramSource); //if the update is successful

		
		//System.out.println("AFTER INSERTING USER");
		
		return status>0;
		
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public List<BankAccount> queryForAccountWithUserId(int user_id) throws SQLException{
		
		String sql_search_account = "SELECT * FROM HW_TRADING_ACCOUNT WHERE USER_ID = ?";
		List<BankAccount> result = jdbcTemplate.query(sql_search_account, new AccountRowMapper(), new Object[] { user_id });    
		/*
		if(result != null && result.size() > 0) {
			return result;
		}*/
		return result;
		
	}
	


}
