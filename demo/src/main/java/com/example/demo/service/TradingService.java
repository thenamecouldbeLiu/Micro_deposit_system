package com.example.demo.service;


public interface TradingService {

    boolean saveTwDeposit(int account_id, float cur_money, float saved_money) throws Exception;

    boolean withdrawTwDeposit(int account_id, float cur_money, float withdrawal_money) throws Exception;

    boolean tradeTwDeposit(int send_account_id, int receive_account_id,float cur_money, float trade_amount)
            throws Exception;


    boolean saveForeignDeposit(int account_id, float cur_money, float saved_money) throws Exception;

    boolean withdrawForeignDeposit(int account_id, float cur_money, float withdrawal_money) throws Exception;

    boolean tradeForeignDeposit(int send_account_id, int receive_account_id,float cur_money, float trade_amount)
            throws Exception;


}
