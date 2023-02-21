package pojo;

public class Account {
    String userName;
    double balance;

    public Account(String userName, double balance) {
        this.userName = userName;
        this.balance = balance;
    }

    public Account() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }

}
