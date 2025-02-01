package AccountServices;

import AccountServices.Abstractions.BankAccount;
import Models.ClientAccount;

public class CreditAccount extends BankAccount {

    private double creditLimit = 5000;

    public CreditAccount(ClientAccount clientAccount) {
        super(clientAccount);
    }

    @Override
    public Boolean withdraw(double amount) {
        if (balance - amount >= (-creditLimit)) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
