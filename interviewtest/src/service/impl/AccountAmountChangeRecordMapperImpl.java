package service.impl;

import pojo.AccountAmountChangeRecord;
import resource.Util;
import service.AccountAmountChangeRecordMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//这里使用的数据库表的方式记录日志需要定期自动删除时间较长的日志
public class AccountAmountChangeRecordMapperImpl implements AccountAmountChangeRecordMapper {
    @Override
    public List<AccountAmountChangeRecord> selectAll() {
        List<AccountAmountChangeRecord> accountAmountChangeRecordMaps = new ArrayList<AccountAmountChangeRecord>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = Util.getConnection();
            String sql = "select type,money from t_account_amount_change_list";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                AccountAmountChangeRecord accountAmountChangeRecord =
                        new AccountAmountChangeRecord(rs.getString("type"),rs.getDouble("money"));
            accountAmountChangeRecordMaps.add(accountAmountChangeRecord);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountAmountChangeRecordMaps;//返回一个list集合到表示作数据展示
    }
}
