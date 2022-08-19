package com.example.demo.dao;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.mockito.internal.stubbing.defaultanswers.ReturnsMocks;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


import java.sql.PreparedStatement;  
import java.sql.SQLException;  


import com.example.demo.model.User;

import net.bytebuddy.asm.Advice.This;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;


 


@Repository
public class UserDAO {
	
	
	//private static final String Integer = null;
	@Autowired
	private JdbcTemplate jdbcTemplate; // used for db operation in java

	
	@Autowired
	private NamedParameterJdbcTemplate jdbcNameTemplate;

	
    
	public boolean InsertUser(User new_user) throws SQLException{
		
		
		//System.out.println("INSERTING USER");
		String sql_insert = "INSERT INTO HW_TRADING_USER (EMAIL, PASSWORD, TW_ID, NAME, CREATE_DATE) "+"VALUES (:email,:password,:tw_id,:name, CURRENT_TIMESTAMP)";

		//System.out.println(sql_insert);
	
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(new_user); //bean parameter inject to sql statement
		
		int status = jdbcNameTemplate.update(sql_insert, paramSource); //if the update is successful

		
		//System.out.println("AFTER INSERTING USER");
		
		return status>0;
		
	}
	
	
	public boolean isValidEmail(String email) {
		String sql_user_email = "SELECT COUNT(USER_ID) FROM HW_TRADING_USER WHERE EMAIL = ?";
		
		Integer row_count = this.jdbcTemplate.queryForObject(
				sql_user_email, Integer.class, new Object[] { email});
		
		return row_count==0;
	}
	
	public boolean isValidTwid(String tw_id) {
		
		String sql_user_twid = "SELECT COUNT(USER_ID) FROM HW_TRADING_USER WHERE TW_ID = ?";
		
		
		Integer row_count = this.jdbcTemplate.queryForObject(
				sql_user_twid, Integer.class, new Object[] { tw_id});
		
		return row_count==0;
	}
	
	public User queryForUser(User user) {
		
		String sql_search_user = "SELECT * FROM HW_TRADING_USER WHERE EMAIL = ? AND PASSWORD = ? AND TW_ID = ?";
		String email = user.getEmail();
		String password = user.getPassword();
		String tw_id = user.getTw_id();


		List<User> result = jdbcTemplate.query(sql_search_user, new BeanPropertyRowMapper<User>(User.class), new Object[] { email, password, tw_id });
		if(result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
		
	}

}
