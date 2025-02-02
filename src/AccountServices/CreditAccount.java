package AccountServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.Abstractions.Interfaces.TransactionFee;
import Models.ClientAccount;
import AccountServices.Abstractions.Interfaces.TransactionValidator;

import java.math.BigDecimal;

public class CreditAccount extends BankAccount implements TransactionFee, TransactionValidator {

    private final double CREDIT_LIMIT = 5000;
    private final double TRANSACTION_FEE = 0.01;


    public CreditAccount(ClientAccount clientAccount) {
        super(clientAccount);
    }

    @Override
    public Boolean withdraw(double amount) {

        double fee = applyFee(amount);
        Boolean isValidated = validate(amount);
        BigDecimal balanceAfterTransaction = balance.subtract(BigDecimal.valueOf(amount + fee));
        Boolean isTransactionResultNonNegative = balanceAfterTransaction.
                compareTo(BigDecimal.valueOf(-CREDIT_LIMIT)) >= 0;

        if (isTransactionResultNonNegative && isValidated) {
            balance = balanceAfterTransaction;
            return true;
        }
        return false;
    }

    @Override
    public double applyFee(double amount) {
        return amount * TRANSACTION_FEE;
    }

    @Override
    public boolean validate(double amount) {

        if(amount <= TRANSACTION_LIMIT) {
            return true;
        }
        return false;
    }
}
