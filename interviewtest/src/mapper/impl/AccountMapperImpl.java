package mapper.impl;
import mapper.AccountMapper;
import pojo.Account;
import resource.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//driver及url、root、password最好使用配置文件作资源绑定
//jdbc代码可封装
public class AccountMapperImpl implements AccountMapper {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    public double selectByName(String userName){
        double balance=0;
        try {
            //使用封装的JDBC工具获取连接
            Connection conn = Util.getConnection();
            String sql="select balance from t_account where user_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);

            //执行sql语句返回结果集
            rs = ps.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            Util.close(conn,ps,rs);
        }
        return balance;
    }

    @Override
    public void update(Account account) {
        try {
            conn = Util.getConnection();
            String sql = "update t_account set balance=? where user_name=?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1,account.getBalance());
            ps.setString(2, account.getUserName());
            int count = ps.executeUpdate();
            Util.close(conn,ps,null);
            if (count==1) {
                //修改成功
            }else{
                //失败，抛出异常
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
