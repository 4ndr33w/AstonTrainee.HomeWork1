package AccountTransactionsServices;

import AccountServices.Abstractions.BankAccount;

import java.util.List;


public class TransactionProcessor{

    public  void processTransaction(List<BankAccount> accounts, double amount) {

        accounts.forEach(account -> account.withdraw(amount));
    }
}
