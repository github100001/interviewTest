package service;

import pojo.AccountAmountChangeRecord;

import java.util.List;

public interface AccountAmountChangeRecordMapper {
    List<AccountAmountChangeRecord> selectAll();
}
