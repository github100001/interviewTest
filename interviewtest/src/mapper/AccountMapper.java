package mapper;

import pojo.Account;

public interface AccountMapper {
    double selectByName(String userName);
    void update(Account account);
}
