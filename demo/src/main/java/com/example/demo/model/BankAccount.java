package com.example.demo.model;
import org.springframework.stereotype.Component;

@Component
public class BankAccount {
	private int User_id;//使用者帳號
	
	private float Tw_deposit; 
	
	private float Forign_deposit;
	
	private String Account_number; //存款帳號
	
	public BankAccount() {}// constructor for bean and controller and service
	
	
	
	public BankAccount(float tw_deposit, float forign_deposit) {
		super();
		Tw_deposit = tw_deposit;
		Forign_deposit = forign_deposit;
	}

	public BankAccount(int user_id, float tw_deposit, float forign_deposit) { // constructor for account creation
		super();
		User_id = user_id;
		Tw_deposit = tw_deposit;
		Forign_deposit = forign_deposit;
	}


	public BankAccount(int user_id, float tw_deposit, float forign_deposit, String account_num) { // full parameters constructor
		super();
		User_id = user_id;
		Tw_deposit = tw_deposit;
		Forign_deposit = forign_deposit;
		Account_number = account_num;
	}



	public int getUser_id() {
		return User_id;
	}



	public void setUser_id(int user_id2) {
		User_id = user_id2;
	}



	public float getTw_deposit() {
		return Tw_deposit;
	}



	public void setTw_deposit(float tw_deposit) {
		Tw_deposit = tw_deposit;
	}



	public float getForign_deposit() {
		return Forign_deposit;
	}



	public void setForign_deposit(float forign_deposit) {
		Forign_deposit = forign_deposit;
	}



	public String getAccount_number() {
		return Account_number;
	}



	public void setAccount_number(String account_number) {
		Account_number = account_number;
	}


}