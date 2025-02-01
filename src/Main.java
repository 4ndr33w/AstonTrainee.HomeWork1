import AccountServices.Abstractions.BankAccount;
import AccountServices.CreditAccount;
import AccountServices.DebitAccount;
import AccountServices.SavingsAccount;
import Models.ClientModel;
import AccountTransactionsServices.TransactionProcessor;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final double SMALL_AMOUNT_VALUE = 995;
    private static final double BIG_AMOUNT_VALUE = 6100;
    private static final double MID_AMOUNT_VALUE = 4300;

    private static List<BankAccount> accounts = new ArrayList<BankAccount>();

    private static TransactionProcessor transactionProcessor;

    public static void main(String[] args) throws SQLException {
        transactionProcessor = new TransactionProcessor();

        printAccounts(SMALL_AMOUNT_VALUE);
        printAccounts(MID_AMOUNT_VALUE);
        printAccounts(BIG_AMOUNT_VALUE);
    }

    private static void printAccounts(double amount) throws SQLException {
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

    private static ArrayList<BankAccount> getAccountCollection() throws SQLException {
        ArrayList<BankAccount> collection = new ArrayList<BankAccount>();

        collection.add(new DebitAccount(new ClientModel(0, "Andr33w McFly", BigDecimal.valueOf(1000))));
        collection.add(new CreditAccount(new ClientModel(1, "Vasya Pupkin", BigDecimal.valueOf(1000))));
        collection.add(new SavingsAccount(new ClientModel(2, "Andron Kamikadzeviсh", BigDecimal.valueOf(1000))));

        return collection;
    }
}