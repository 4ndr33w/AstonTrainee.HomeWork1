package AccountServices;

import AccountServices.Abstractions.BankAccount;
import Models.ClientAccount;
import AccountServices.Abstractions.Interfaces.TransactionValidator;

public class DebitAccount extends BankAccount implements TransactionValidator {

    public DebitAccount(ClientAccount clientAccount) {
        super(clientAccount);

        transactionLimit = 10000;
    }

    @Override
    public Boolean withdraw(double amount) {

        Boolean isValidated = validate(amount);

        if (balance >= amount && isValidated) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean validate(double amount) {

        if(amount <= transactionLimit) {
            return true;
        }
        return false;
    }
}
