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

    public BankAccount(ClientAccount clientAccount) {
        accountNumber = clientAccount.getId();
        this.balance = clientAccount.getBalance();
        this.accountHolder = clientAccount.getName();
    }

    public abstract Boolean withdraw (double amount);


    public void deposit (double amount)
    {
        balance += amount;
    }
}
