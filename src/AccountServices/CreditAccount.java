package AccountServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.Abstractions.Interfaces.TransactionFee;
import Models.ClientAccount;
import Tests.TransactionServices.Interfaces.TransactionValidator;

public class CreditAccount extends BankAccount implements TransactionFee, TransactionValidator {

    private double creditLimit = 5000;
    private double transactionFee = 0.01;



    public CreditAccount(ClientAccount clientAccount) {
        super(clientAccount);

        transactionLimit = 5000;
    }

    @Override
    public Boolean withdraw(double amount) {

        double fee = applyFee(amount);
        Boolean isValidated = validate(amount);
        Boolean isTransactionResultNonNegative = balance - (amount + fee) >= (-creditLimit);

        if (isTransactionResultNonNegative && isValidated) {
            balance -= (amount + fee);
            return true;
        }
        return false;
    }

    @Override
    public double applyFee(double amount) {
        return amount * transactionFee;
    }

    @Override
    public boolean validate(double amount) {

        if(amount <= transactionLimit) {
            return true;
        }
        return false;
    }
}
