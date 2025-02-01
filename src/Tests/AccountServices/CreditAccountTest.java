package Tests.AccountServices;

import AccountServices.CreditAccount;
import Models.ClientAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTest {

    private double startBalance = 1000;
    ClientAccount user = new ClientAccount(1, "Vasya Pupkin", startBalance );
    CreditAccount creditAccount = new CreditAccount(user);

    @Test
    void withdrawSuccess() {

        double withdrawAmount = 5999;
        double totalBalance = startBalance - withdrawAmount;

        Boolean withdrawResult = creditAccount.withdraw(withdrawAmount);

        assertTrue(withdrawResult);
        assertTrue(creditAccount.getBalance() >= (-5000));
        assertEquals(totalBalance, creditAccount.getBalance());

        System.out.println("withdrawResult: = " + withdrawResult);
        System.out.println("totalBalance: = " + totalBalance);
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

        assertTrue(creditAccount.getBalance() >= (-5000));
        assertEquals(totalBalance, creditAccount.getBalance());

        System.out.println("totalBalance: = " + totalBalance);
    }
}