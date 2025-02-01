package AccountServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.Abstractions.Interfaces.TransactionFee;
import Models.ClientAccount;

public class CreditAccount extends BankAccount implements TransactionFee {

    private double creditLimit = 5000;
    private double transactionFee = 0.01;

    public CreditAccount(ClientAccount clientAccount) {
        super(clientAccount);
    }

    @Override
    public Boolean withdraw(double amount) {

        double fee = applyFee(amount);

        if (balance - (amount + fee)>= (-creditLimit)) {
            balance -= (amount + fee);
            return true;
        }
        return false;
    }

    @Override
    public double applyFee(double amount) {
        return amount * transactionFee;
    }
}
