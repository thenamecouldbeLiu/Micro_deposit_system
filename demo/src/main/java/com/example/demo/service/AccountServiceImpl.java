package com.example.demo.service;

import com.example.demo.beans.BankAccount;
import com.example.demo.dao.BankAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AccountServiceImpl implements AccountService{
    @Autowired
    private BankAccountDAO accountDAO;


    @Transactional(rollbackFor = Exception.class)
    public String[] createBankAccount(BankAccount account) throws Exception {
        final String[] result_array = new String[2];


        boolean status =  accountDAO.InsertAccount(account);
        if (status) {
            result_array[0] = "200";
            result_array[1] = "Create account successfully";
            return result_array;

        }

        //System.out.println("Inserted user failed with email: "+ (new_user.getEmail()));
        result_array[0] = "400";
        result_array[1] = "Insertion failed due to unknown reasons";

        return result_array;

    }

    public List<BankAccount> findUserBankAccounts(int user_id) throws Exception {

        List<BankAccount> user_accounts = accountDAO.queryForAccountWithUserId(user_id);
        return user_accounts;
    }
}
