package com.example.demo.service;

import com.example.demo.dao.TradingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TradingServiceImpl implements TradingService{
    @Autowired
    TradingDAO tradingDAO;

    @Transactional(rollbackFor = Exception.class)
    public boolean saveTwDeposit(int account_id, float cur_money, float saved_money) throws Exception{
        final float new_amount = cur_money+saved_money;
        boolean status = tradingDAO.setTwMoney(account_id, new_amount);

        return status;

    }

    @Transactional(rollbackFor = Exception.class)
    public boolean withdrawTwDeposit(int account_id, float cur_money, float withdrawal_money) throws Exception{
        final float new_amount = cur_money-withdrawal_money;
        boolean status = tradingDAO.setTwMoney(account_id, new_amount);

        return status;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean tradeTwDeposit(int send_account_id, int receive_account_id,float cur_money, float trade_amount)
            throws Exception{
        final float new_amount_receive = cur_money+trade_amount;
        final float new_amount_send = cur_money-trade_amount;

        boolean status_send = tradingDAO.setTwMoney(send_account_id, new_amount_send);
        boolean status_receive = tradingDAO.setTwMoney(receive_account_id, new_amount_receive);


        return status_send && status_receive;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveForeignDeposit(int account_id, float cur_money, float saved_money) throws Exception{
        final float new_amount = cur_money+saved_money;
        boolean status = tradingDAO.setForeignMoney(account_id, new_amount);

        return status;

    }

    @Transactional(rollbackFor = Exception.class)
    public boolean withdrawForeignDeposit(int account_id, float cur_money, float withdrawal_money) throws Exception{
        final float new_amount = cur_money-withdrawal_money;
        boolean status = tradingDAO.setForeignMoney(account_id, new_amount);

        return status;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean tradeForeignDeposit(int send_account_id, int receive_account_id,float cur_money, float trade_amount)
            throws Exception{
        final float new_amount_receive = cur_money+trade_amount;
        final float new_amount_send = cur_money-trade_amount;

        boolean status_send = tradingDAO.setForeignMoney(send_account_id, new_amount_send);
        boolean status_receive = tradingDAO.setForeignMoney(receive_account_id, new_amount_receive);


        return status_send && status_receive;
    }

}
