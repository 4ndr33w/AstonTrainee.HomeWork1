package AccountServices.Abstractions;

import Models.ClientModel;
import Resources.StaticResources;

import java.math.BigDecimal;

public abstract class BankAccount {

    protected double transactionLimit = StaticResources.TRANSACTION_LIMIT;

    protected final long accountNumber;
    protected BigDecimal balance;
    protected final String accountHolder;

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public BankAccount(ClientModel clientModel) {
        accountNumber = clientModel.getId();
        balance = clientModel.getBalance();
        accountHolder = clientModel.getName();
    }

    public BankAccount(ClientModel clientModel, double transactionLimit) {
        accountNumber = clientModel.getId();
        balance = clientModel.getBalance();
        accountHolder = clientModel.getName();
        this.transactionLimit = transactionLimit;
    }

    public abstract Boolean withdraw(double amount);

    public void deposit(double amount) {

        balance = balance.add(new BigDecimal(amount)).stripTrailingZeros();
    }
}
