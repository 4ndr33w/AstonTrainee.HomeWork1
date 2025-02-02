package Tests.AccountServices;

import AccountServices.DebitAccount;
import Models.ClientAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DebitAccountTests {

    private BigDecimal startBalance = BigDecimal.valueOf(1000);
    Boolean transactionResult;

    ClientAccount user;
    DebitAccount debitAccount;

    @BeforeEach
    void setUp() {
        user = new ClientAccount(1, "Vasya Pupkin", startBalance );
        debitAccount = new DebitAccount(user);
    }

    @Test
    void withdrawSuccess() {
/*
        double withdrawAmount = 900;
        double operationLimit = 5000;
        double totalBalance = startBalance - withdrawAmount;

        transactionResult = debitAccount.withdraw(withdrawAmount );

        assertTrue(transactionResult);
        assertTrue(debitAccount.getBalance() >= (0));
        assertTrue(withdrawAmount <= operationLimit);
        assertEquals(totalBalance, debitAccount.getBalance());

        System.out.println("withdrawResult: = " + transactionResult);
        System.out.println("totalBalance: = " + totalBalance);*/
    }

    @Test
    void withdrawFail() {
/*
        double withdrawAmount = 1001;
        double totalBalance = startBalance - withdrawAmount;

        transactionResult = debitAccount.withdraw(withdrawAmount );

        assertFalse(transactionResult);
        assertTrue(debitAccount.getBalance() >= (0));
        assertEquals(startBalance, debitAccount.getBalance());

        System.out.println("withdrawResult: = " + transactionResult);
        System.out.println("totalBalance: = " + totalBalance);*/
    }
}
