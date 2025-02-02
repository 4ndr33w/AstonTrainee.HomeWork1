package AccountServices.Abstractions;

import Models.ClientAccount;

import java.math.BigDecimal;

public abstract class BankAccount {

    protected long accountNumber;
    protected BigDecimal balance;
    protected String accountHolder;

    public BigDecimal getBalance() {
        return balance;
    }

    public BankAccount(ClientAccount clientAccount) {
        accountNumber = clientAccount.getId();
        this.balance = clientAccount.getBalance();
        this.accountHolder = clientAccount.getName();
    }

    public abstract Boolean withdraw (double amount);

    public void deposit (double amount)
    {
        balance = balance.add(BigDecimal.valueOf(amount));
    }
}
