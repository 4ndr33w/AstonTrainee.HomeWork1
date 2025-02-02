package AccountServices.Abstractions;

import Models.ClientAccount;

import java.math.BigDecimal;

public abstract class BankAccount {

    protected final long accountNumber;
    protected BigDecimal balance;
    protected final String accountHolder;

    protected final double TRANSACTION_LIMIT;

    public BigDecimal getBalance() {
        return balance;
    }
    public String getAccountHolder() {
        return accountHolder;
    }

    public BankAccount(ClientAccount clientAccount) {
        accountNumber = clientAccount.getId();
        balance = clientAccount.getBalance();
        accountHolder = clientAccount.getName();
        TRANSACTION_LIMIT = 5000;
    }

    public BankAccount(ClientAccount clientAccount, double transactionLimit) {
        accountNumber = clientAccount.getId();
        balance = clientAccount.getBalance();
        accountHolder = clientAccount.getName();
        TRANSACTION_LIMIT = transactionLimit;
    }

    public abstract Boolean withdraw (double amount);

    public void deposit (double amount) {
        balance = balance.add(new BigDecimal(amount));
    }
}
