package service.impl;

import mapper.AccountMapper;
import mapper.impl.AccountMapperImpl;
import pojo.Account;
import resource.Util;
import service.AccountService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
    Connection conn=null;
    PreparedStatement ps=null;
    int count=0;

    AccountMapper accountMapper = new AccountMapperImpl();
    public void AccountConsumer(double money,String userName) {
        Account account = new Account(userName,money);
        double balance = accountMapper.selectByName(userName);
        if (balance>=money) {
            //余额充足
            account.setBalance(balance-money);
            //修改数据库中信息
            accountMapper.update(account);
            //消费成功,跳转到表示层进行数据展示或者跳转success.jsp
            try {
                conn = Util.getConnection();
                String sql="insert into t_account_amount_change_list(type,money) value(?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1,"消费");
                ps.setDouble(2,money);
                count = ps.executeUpdate();
                if (count==1) {
                    //记录成功
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                Util.close(conn,ps,null);
            }
        }else{
            //重定向到error.jsp或者自定义抛出balanceNotEnoughException异常自定义信息"余额不足"
        }
    }

    @Override
    public void AccountRefund(double money, String userName) {
        Account account = new Account(userName,money);
        account.setBalance(account.getBalance()+money);
        accountMapper.update(account);
        //退款成功,跳转到表示层进行数据展示或者跳转success.jsp
        try {
            conn = Util.getConnection();
            String sql="insert into t_account_amount_change_list(type,money) value(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"退款");
            ps.setDouble(2,money);
            count = ps.executeUpdate();
            if (count==1) {
                //记录成功
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Util.close(conn,ps,null);
        }
    }

}