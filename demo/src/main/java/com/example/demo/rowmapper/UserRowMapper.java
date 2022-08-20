package com.example.demo.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.beans.User;

 /**
9  * 实现RowMapper接口，返回User对象
10  * */
 public class UserRowMapper implements RowMapper<User>{
 
     @Override
     public User mapRow(ResultSet resultSet, int i) throws SQLException {
 //        获取结果集中的数据
         String name = resultSet.getString("NAME");
         String tw_id = resultSet.getString("TW_ID");
         String password = resultSet.getString("PASSWORD");
         String email = resultSet.getString("EAMIL");
 //        把数据封装成User对象
         User user = new User();
         user.setName(name);
         user.setTw_id(tw_id);
         user.setPassword(password);
         user.setEmail(email);
         
         return user;
     }
 }


