package AccountServices.Abstractions;

import Models.ClientAccount;

public abstract class BankAccount {
    protected long accountNumber;
    protected double balance;
    protected String accountHolder;

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public BankAccount(long accountNumber, double balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public BankAccount(ClientAccount clientAccount) {
        accountNumber = clientAccount.getId();
        this.balance = clientAccount.getBalance();
        this.accountHolder = clientAccount.getName();
    }

    public abstract Boolean withdraw (double amount);

    public Boolean deposit (double amount)
    {
        balance += amount;
        return true;
    }
}
