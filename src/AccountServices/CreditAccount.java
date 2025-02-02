package AccountServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.Abstractions.Interfaces.TransactionFee;
import Models.ClientAccount;

import java.math.BigDecimal;

public class CreditAccount extends BankAccount implements TransactionFee {

    private final double CREDIT_LIMIT = 5000;
    private final double TRANSACTION_FEE = 0.01;

    public CreditAccount(ClientAccount clientAccount) {
        super(clientAccount);
    }

    @Override
    public Boolean withdraw(double amount) {

        double fee = applyFee(amount);
        BigDecimal balanceAfterTransaction = balance.subtract((BigDecimal.valueOf(amount + fee)));

        if (balanceAfterTransaction.compareTo(BigDecimal.valueOf(CREDIT_LIMIT))>= 0) {
            balance = balanceAfterTransaction;
            return true;
        }
        return false;
    }

    @Override
    public double applyFee(double amount) {
        return amount * TRANSACTION_FEE;
    }
}
