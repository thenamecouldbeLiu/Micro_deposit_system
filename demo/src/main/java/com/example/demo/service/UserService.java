package com.example.demo.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDAO;
import com.example.demo.beans.User;

@Service
public interface UserService {


    Object[] findUser(User query_user);


    String[] addUser(User new_user) throws SQLException;
}