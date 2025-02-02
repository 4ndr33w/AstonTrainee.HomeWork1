package AccountServices;

import AccountServices.Abstractions.BankAccount;
import Models.ClientAccount;

import java.math.BigDecimal;

public class DebitAccount extends BankAccount {

    public DebitAccount(ClientAccount clientAccount) {
        super(clientAccount);
    }

    @Override
    public Boolean withdraw(double amount) {

        BigDecimal balanceAfterTransaction = balance.subtract(new BigDecimal(amount));
        if (balance.compareTo(new BigDecimal(amount)) >= 0) {
            balance = balanceAfterTransaction;
            return true;
        }
        return false;
    }
}
