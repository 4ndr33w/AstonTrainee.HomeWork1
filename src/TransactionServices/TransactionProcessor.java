package TransactionServices;

import AccountServices.Abstractions.BankAccount;

import java.util.*;

public class TransactionProcessor {

    public  void processTransaction(List<BankAccount> accounts, double amount) {

        accounts.forEach(account -> account.withdraw(amount));
    }
}
