package AccountServices;

import AccountServices.Abstractions.BankAccount;
import Models.ClientAccount;

public class DebitAccount extends BankAccount {

    public DebitAccount(ClientAccount clientAccount) {
        super(clientAccount);
    }

    @Override
    public Boolean withdraw(double amount) {

        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
