package Tests.AccountTransactionsServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.CreditAccount;
import AccountServices.DebitAccount;
import AccountServices.SavingsAccount;
import Models.ClientModel;
import Resources.StaticResources;
import AccountTransactionsServices.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionProcessorTests {

    private double CREDIT_LIMIT;
    private TransactionProcessor transactionProcessor;
    private List<BankAccount> accounts;

    @BeforeEach
    public void setup() {
        CREDIT_LIMIT = StaticResources.CREDIT_LIMIT;
        transactionProcessor = new TransactionProcessor();
        accounts = getAccountCollection();
    }

    @Test
    @DisplayName("Withdraw amount 3000 expected success for credit account and fail for debit and savings accounts")
    public void testWithdraw_amount3000() {
        double midAmountValue = 3000;
        transactionProcessor.processTransaction(accounts, midAmountValue);

        accounts.forEach(account -> {
            double startBalance = 1000;
            BigDecimal expectedBalance;
            BigDecimal actualBalance = account.getBalance().add(BigDecimal.valueOf(0.0));

            if(account instanceof CreditAccount) {
                double transactionFee = 0.01;
                double fee = midAmountValue * transactionFee;

                expectedBalance = BigDecimal.valueOf(startBalance - midAmountValue - fee);

                assertEquals(expectedBalance.stripTrailingZeros(), actualBalance.stripTrailingZeros());
                assertTrue(account.getBalance().compareTo(BigDecimal.valueOf(CREDIT_LIMIT)) >= 0);
            }

            else {
                expectedBalance = BigDecimal.valueOf(startBalance);

                assertEquals(expectedBalance.stripTrailingZeros(), actualBalance.stripTrailingZeros());
                assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) >= 0);
            }
        });
    }

    @Test
    @DisplayName("Withdraw amount 995 expected success for all accounts")
    public void testWithdraw_amount995() {
        double smallAmountValue = 995;
        transactionProcessor.processTransaction(accounts, smallAmountValue);

        accounts.forEach(account -> {

            double startBalance = 1000;
            BigDecimal expectedBalance;
            BigDecimal actualBalance = account.getBalance();

            if(account instanceof CreditAccount) {
                double transactionFee = 0.01;
                double fee = smallAmountValue * transactionFee;
                BigDecimal smallAmountValueFee = BigDecimal.valueOf( smallAmountValue + fee);

                expectedBalance = BigDecimal.valueOf(startBalance).subtract(smallAmountValueFee);
                assertTrue(expectedBalance.subtract(BigDecimal.valueOf(CREDIT_LIMIT)).compareTo(BigDecimal.ZERO) >= 0);
            }

            else {
                expectedBalance = BigDecimal.valueOf(startBalance - smallAmountValue);
                assertTrue(expectedBalance.compareTo(BigDecimal.ZERO) >= 0);
            }
            assertEquals(expectedBalance.stripTrailingZeros(), actualBalance.stripTrailingZeros());
        });
    }

    @Test
    @DisplayName("Withdraw amount 6100 expected fail operation for all accounts")
    public void testWithdraw_amount6100() {
        double bigAmountValue = 6100;
        transactionProcessor.processTransaction(accounts, bigAmountValue);

        accounts.forEach(account -> {

            BigDecimal startBalance = BigDecimal.valueOf(1000);

            assertEquals(startBalance, account.getBalance());
        });
    }

    private ArrayList<BankAccount> getAccountCollection() {
        ArrayList<BankAccount> collection = new ArrayList<BankAccount>();

        collection.add(new DebitAccount(new ClientModel(0, "Andr33w McFly", BigDecimal.valueOf(1000))));
        collection.add(new CreditAccount(new ClientModel(1, "Vasya Pupkin", BigDecimal.valueOf(1000))));
        collection.add(new SavingsAccount(new ClientModel(2, "Andron Kamikadzevich", BigDecimal.valueOf(1000))));

        return collection;
    }
}
