package Tests.TransactionServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.CreditAccount;
import AccountServices.DebitAccount;
import AccountServices.SavingsAccount;
import Models.ClientAccount;
import TransactionServices.TransactionProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionProcessorTests {

    private double smallAmountValue = 995;
    private double bigAmountValue = 6100;
    private double midAmountValue = 3000;
    private double creditLimit = -5000;

    private TransactionProcessor transactionProcessor;

    private List<BankAccount> accounts;

    @BeforeEach
    public void setup() {

        transactionProcessor = new TransactionProcessor();
        accounts = getAccountCollection();
    }

    @Test
    public void processTransactionWithdrawDebitAndSavingsFailedCreditSuccess() {

        transactionProcessor.processTransaction(accounts, midAmountValue);

        accounts.forEach(account -> {

            double startBalance = 1000;
            double expectedBalance = startBalance;

            if(account instanceof CreditAccount)
            {
                double transactionFee = 0.01;
                double fee = midAmountValue * transactionFee;

                expectedBalance = startBalance - midAmountValue - fee;

                assertEquals(expectedBalance, account.getBalance());
                assertTrue(account.getBalance() >= creditLimit);
            }

            else {
                assertEquals(startBalance, account.getBalance());
                assertTrue(account.getBalance() >= 0);
            }
        });
    }

    @Test
    public void processTransactionWithdrawSuccessAll() {

        transactionProcessor.processTransaction(accounts, smallAmountValue);

        accounts.forEach(account -> {

            double startBalance = 1000;
            double expectedBalance = startBalance;

            if(account instanceof CreditAccount)
            {
                double transactionFee = 0.01;
                double fee = smallAmountValue * transactionFee;

                expectedBalance = startBalance - (smallAmountValue + fee);
                assertTrue(expectedBalance >= creditLimit);
            }

            else {
                expectedBalance = startBalance - smallAmountValue;
                assertTrue(expectedBalance >= 0);
            }
            assertEquals(expectedBalance, account.getBalance());

            System.out.println(account.getClass().getName() + ": " + account.getBalance());

        });
    }

    @Test
    public void processTransactionWithdrawFailedAll() {
        transactionProcessor.processTransaction(accounts, bigAmountValue);

        accounts.forEach(account -> {

            double startBalance = 1000;

            assertEquals(startBalance, account.getBalance());
        });
    }

    private ArrayList<BankAccount> getAccountCollection() {
        ArrayList<BankAccount> collection = new ArrayList<BankAccount>();

        collection.add(new DebitAccount(new ClientAccount(0, "Andreew McFly", BigDecimal.valueOf(1000))));
        collection.add(new CreditAccount(new ClientAccount(1, "Vasya Pupkin", BigDecimal.valueOf(1000))));
        collection.add(new SavingsAccount(new ClientAccount(2, "Andron Kamikadzevixh", BigDecimal.valueOf(1000))));

        return collection;
    }
}
