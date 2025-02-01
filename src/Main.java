import AccountServices.Abstractions.BankAccount;
import AccountServices.CreditAccount;
import AccountServices.DebitAccount;
import AccountServices.SavingsAccount;
import Models.ClientAccount;
import TransactionServices.TransactionProcessor;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static List<BankAccount> accounts;
    private static double smallAmountValue = 995;
    private static double bigAmountValue = 6100;
    private static double midAmountValue = 4300;

    static TransactionProcessor transactionProcessor;

    public static void main(String[] args) {

        transactionProcessor = new TransactionProcessor();

        printAccounts(smallAmountValue);
        printAccounts(midAmountValue);
        printAccounts(bigAmountValue);
    }

    private static void printAccounts(double amount) {
        accounts.clear();
        accounts = getAccountCollection();

        transactionProcessor.processTransaction(accounts, amount );

        System.out.println("withdraw amount: " +  amount);
        System.out.println(" ");

        for (BankAccount account : accounts) {
            System.out.println(account.getClass().getSimpleName() + " balance: " + account.getBalance());
        }
        System.out.println(" ");
        System.out.println("-------------------");
    }

    private static ArrayList<BankAccount> getAccountCollection() {
        ArrayList<BankAccount> collection = new ArrayList<BankAccount>();

        collection.add(new DebitAccount(new ClientAccount(0, "Andreew McFly", 1000)));
        collection.add(new CreditAccount(new ClientAccount(1, "Vasya Pupkin", 1000)));
        collection.add(new SavingsAccount(new ClientAccount(2, "Andron Kamikadzevixh", 1000)));

        return collection;
    }
}