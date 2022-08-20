package com.example.demo.beans;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String Name;
	
	private String Email;
	
	private String Tw_id;
	
	private String Password;
	
	private int User_id;

	public User() {} // constructor for bean and controller and service
	
	
	public User(String email, String tw_id, String password) {
		super();
		Email = email;
		Tw_id = tw_id;
		Password = password;
	}


	public User(String name, String email, String tw_id, String password) {
		super();
		Name = name;
		Email = email;
		Tw_id = tw_id;
		Password = password;
	}

	public int getUser_id() {
		return User_id;
	}


	public void setUser_id(int id) {
		this.User_id = id;
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getTw_id() {
		return Tw_id;
	}

	public void setTw_id(String new_Tw_id) {
		Tw_id = new_Tw_id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public String getEmail() {
		return Email;
	}


	public void setEmail(String new_email){
		this.Email = new_email;
		
	}

	
	
}

/*
private int age ;

private String commodity ;

private int price ;

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getCommodity() {
	return commodity;
}

public void setCommodity(String commodity) {
	this.commodity = commodity;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}
*/