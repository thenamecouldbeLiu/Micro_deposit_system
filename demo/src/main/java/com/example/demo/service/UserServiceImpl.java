package com.example.demo.service;

import com.example.demo.beans.User;
import com.example.demo.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDao;


    public Object[] findUser(User query_user) {


        User queryed_user = this.userDao.queryForUser(query_user);
        final Object[] pair_array = new Object[2];

        if (queryed_user != null){
            pair_array[0] = true;
            pair_array[1] = queryed_user;

        }else {
            pair_array[0] = false;
            pair_array[1] = "No user found";
        }

        return pair_array;
    }


    @Transactional
    public String[] addUser(User new_user) throws SQLException {
        final String[] result_array = new String[2];

        boolean isValidEmail = userDao.isValidEmail(new_user.getEmail());
        if (!isValidEmail) {
            result_array[0] = "400";
            result_array[1] = "Email already used";
            return result_array;
        }

        boolean isValidTwid = userDao.isValidTwid(new_user.getTw_id());
        if (!isValidTwid) {
            result_array[0] = "400";
            result_array[1] = "TWID already used";
            return result_array;
        }

	  /*
	  boolean ifUserExist = (boolean) this.findUser(new_user)[0];
	  if (ifUserExist) {return "User already exist";}
	  */


        boolean status =  this.userDao.InsertUser(new_user);
        if (status) {
            result_array[0] = "200";
            result_array[1] = "Create user successfully";
            return result_array;

        }

        //System.out.println("Inserted user failed with email: "+ (new_user.getEmail()));
        result_array[0] = "400";
        result_array[1] = "Insertion failed due to unkown reasons";

        return result_array;
    }
}
