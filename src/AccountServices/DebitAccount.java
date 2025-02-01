package AccountServices;

import AccountServices.Abstractions.BankAccount;
import Models.ClientModel;
import AccountServices.Abstractions.Interfaces.TransactionValidator;

import java.math.BigDecimal;

public class DebitAccount extends BankAccount implements TransactionValidator {

    public DebitAccount(ClientModel clientModel) {
        super(clientModel, 10000);
    }

    @Override
    public Boolean withdraw(double amount) {
        boolean isValidated = validate(amount);
        boolean isTransactionCanBeExecuted = balance.compareTo(BigDecimal.valueOf(amount)) >= 0;

        if (isTransactionCanBeExecuted && isValidated) {
            balance = balance.subtract(BigDecimal.valueOf(amount)).stripTrailingZeros();
            return true;
        }
        return false;
    }

    @Override
    public boolean validate(double amount) {

        return amount <= transactionLimit;
    }
}
