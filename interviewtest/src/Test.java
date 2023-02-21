import pojo.AccountAmountChangeRecord;
import service.impl.AccountAmountChangeRecordMapperImpl;
import service.impl.AccountServiceImpl;

import java.util.List;


public class Test {
    public static void main(String[] args) {
        AccountServiceImpl accountService = new AccountServiceImpl();
        //accountService.AccountRefund(100,"张三");
        //accountService.AccountConsumer(100,"张三");
        AccountAmountChangeRecordMapperImpl accountAmountChangeRecordMapper =
                new AccountAmountChangeRecordMapperImpl();
        List<AccountAmountChangeRecord> accountAmountChangeRecords = accountAmountChangeRecordMapper.selectAll();
        for (AccountAmountChangeRecord accountAmountChangeRecord:accountAmountChangeRecords
             ) {
            System.out.println(accountAmountChangeRecord);
        }
    }
}
