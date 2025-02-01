package Tests.AccountServices;

import AccountServices.CreditAccount;
import Models.ClientAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTests {

    private double startBalance = 1000;
    private double transactionFee = 0.01;
    ClientAccount user = new ClientAccount(1, "Vasya Pupkin", startBalance );
    CreditAccount creditAccount = new CreditAccount(user);

    @Test
    void withdrawSuccess() {

        double withdrawAmount = 5940;
        double totalBalance = startBalance - withdrawAmount;

        Boolean actualResult = creditAccount.withdraw(withdrawAmount);

        double expectedResult = startBalance - (withdrawAmount + withdrawAmount * transactionFee);

        assertTrue(actualResult);
        assertTrue(creditAccount.getBalance() >= (-5000));
        assertEquals(expectedResult, creditAccount.getBalance());

        System.out.println("actualBalance: = " + creditAccount.getBalance());
        System.out.println("expectedBalance: = " + expectedResult );
    }

    @Test
    void withdrawFail() {
        double withdrawAmount = 6002;
        double totalBalance = startBalance - withdrawAmount;

        Boolean withdrawResult = creditAccount.withdraw(withdrawAmount);

        assertFalse(withdrawResult);
        assertTrue(creditAccount.getBalance() >= (-5000));
        assertEquals(startBalance, creditAccount.getBalance());

        System.out.println("withdrawResult: = " + withdrawResult);
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