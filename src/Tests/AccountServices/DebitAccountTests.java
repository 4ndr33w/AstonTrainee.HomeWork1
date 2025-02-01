package Tests.AccountServices;

import AccountServices.DebitAccount;
import Models.ClientAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DebitAccountTests {

    private double startBalance = 1000;
    ClientAccount user = new ClientAccount(1, "Vasya Pupkin", startBalance );
    DebitAccount debitAccount = new DebitAccount(user);
    Boolean withdrawResult;

    @Test
    void withdrawSuccess() {

        double withdrawAmount = 900;
        double totalBalance = startBalance - withdrawAmount;

        withdrawResult = debitAccount.withdraw(withdrawAmount );

        assertTrue(withdrawResult);
        assertTrue(debitAccount.getBalance() >= (0));
        assertEquals(totalBalance, debitAccount.getBalance());

        System.out.println("withdrawResult: = " + withdrawResult);
        System.out.println("totalBalance: = " + totalBalance);
    }

    @Test
    void withdrawFail() {

        double withdrawAmount = 1001;
        double totalBalance = startBalance - withdrawAmount;

        withdrawResult = debitAccount.withdraw(withdrawAmount );

        assertFalse(withdrawResult);
        assertTrue(debitAccount.getBalance() >= (0));
        assertEquals(startBalance, debitAccount.getBalance());

        System.out.println("withdrawResult: = " + withdrawResult);
        System.out.println("totalBalance: = " + totalBalance);
    }
}
