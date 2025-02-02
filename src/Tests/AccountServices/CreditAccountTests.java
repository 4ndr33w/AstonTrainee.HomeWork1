package Tests.AccountServices;

import AccountServices.CreditAccount;
import Models.ClientAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTests {

    private double startBalance = 1000;
    private double transactionFee = 0.01;

    ClientAccount user;
    CreditAccount creditAccount;

    @BeforeEach
    public void setUp() {
        user = new ClientAccount(1, "Vasya Pupkin", startBalance );
        creditAccount = new CreditAccount(user);
    }

    @Test
    void withdrawSuccess() {

        double withdrawAmount = 4940;
        double operationLimit = 5000;
        double creditLimit = -5000;
        double totalBalance = startBalance - withdrawAmount;

        Boolean transactionResult = creditAccount.withdraw(withdrawAmount);
        double expectedBalance = startBalance - (withdrawAmount + withdrawAmount * transactionFee);

        assertTrue(transactionResult);
        assertTrue(creditAccount.getBalance() >= creditLimit);
        assertTrue(withdrawAmount <= operationLimit);
        assertEquals(expectedBalance, creditAccount.getBalance());

        System.out.println("actualBalance: = " + creditAccount.getBalance());
        System.out.println("expectedBalance: = " + expectedBalance );
    }

    @Test
    void withdrawFail() {
        double withdrawAmount = 6002;

        Boolean transactionResult = creditAccount.withdraw(withdrawAmount);

        assertFalse(transactionResult);
        assertEquals(startBalance, creditAccount.getBalance());

        System.out.println("transactionResult: = " + transactionResult);
        System.out.println("totalBalance: = " + creditAccount.getBalance());
    }

    @Test
    void depositSuccess() {
        double depositAmount = 6000;
        double totalBalance = startBalance + depositAmount;
        creditAccount.deposit(depositAmount);

        assertTrue(creditAccount.getBalance() >= (-5000));
        assertEquals(totalBalance, creditAccount.getBalance());

        System.out.println("totalBalance: = " + totalBalance);
    }

    @Test
    void applyFeeSuccess() {
        double amount = 1000;
        double fee = creditAccount.applyFee(amount);

        assertEquals(amount * transactionFee, fee);

        System.out.println("fee: = " + fee);
    }
}