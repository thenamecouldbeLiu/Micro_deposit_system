package com.example.demo.dao;

import com.example.demo.beans.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public class TradingDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate; // used for db operation in java


    @Transactional(rollbackFor = Exception.class)
    public boolean setTwMoney(int account_id, float tw_money) throws SQLException {
        // 該設的錢在service layer就算好了 這邊只負責存進table

        String sql_set_tw_money =
        "UPDATE HW_TRADING_ACCOUNT SET TW_DEPOSIT = ? WHERE ACCOUNT_ID = >";
        //"INSERT INTO HW_TRADING_ACCOUNT (USER_ID, TW_DEPOSIT, FOREIGN_DEPOSIT, ACCOUNT_NUMBER, ACCOUNT_CREATE_DATE) "+"VALUES (:user_id,:tw_deposit,:foreign_deposit,:account_number, CURRENT_TIMESTAMP)";

        //System.out.println(sql_insert);

        int status = jdbcTemplate.update(sql_set_tw_money, new Object[] {tw_money, account_id}); //if the update is successful


        //System.out.println("AFTER INSERTING USER");

        return status>0;

    }

    @Transactional(rollbackFor = Exception.class)
    public boolean setForeignMoney(int account_id, float foreign_money) throws Exception {
        // 該設的錢在service layer就算好了 這邊只負責存進table

        String sql_set_tw_money =
                "UPDATE HW_TRADING_ACCOUNT SET FOREIGN_DEPOSIT = ? WHERE ACCOUNT_ID = >";

        int status = jdbcTemplate.update(sql_set_tw_money, new Object[] {foreign_money, account_id}); //if the update is successful


        //System.out.println("AFTER INSERTING USER");

        return status>0;

    }



}
