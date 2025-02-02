package AccountServices;

import AccountServices.Abstractions.BankAccount;
import Models.ClientAccount;
import AccountServices.Abstractions.Interfaces.TransactionValidator;

import java.math.BigDecimal;

public class DebitAccount extends BankAccount implements TransactionValidator {

    public DebitAccount(ClientAccount clientAccount) {
        super(clientAccount, 10000);
    }

    @Override
    public Boolean withdraw(double amount) {

        Boolean isValidated = validate(amount);

        if (balance.compareTo(BigDecimal.valueOf(amount)) >= 0 && isValidated) {
            balance = balance.subtract(BigDecimal.valueOf(amount));
            return true;
        }
        return false;
    }

    @Override
    public boolean validate(double amount) {

        if(amount <= TRANSACTION_LIMIT) {
            return true;
        }
        return false;
    }
}
