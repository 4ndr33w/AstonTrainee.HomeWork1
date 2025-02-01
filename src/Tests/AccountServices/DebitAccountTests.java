package Tests.AccountServices;

import AccountServices.DebitAccount;
import Models.ClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DebitAccountTests {

    private BigDecimal startBalance;
    private Boolean transactionResult;
    private DebitAccount debitAccount;

    @BeforeEach
    public void setUp() {
        startBalance = BigDecimal.valueOf(1000);
        ClientModel user = new ClientModel(1, "Vasya Pupkin", startBalance);
        debitAccount = new DebitAccount(user);
    }

    @Test
    @DisplayName("test withdraw amount less than current balance")
    public void testWithdraw_expectedTrue() {

        double withdrawAmount = 900;
        BigDecimal totalBalance = startBalance.subtract(BigDecimal.valueOf(withdrawAmount));
        transactionResult = debitAccount.withdraw(withdrawAmount);

        assertTrue(transactionResult);
        assertTrue(debitAccount.getBalance().compareTo(BigDecimal.ZERO) >= 0);
        assertEquals(totalBalance.stripTrailingZeros(), debitAccount.getBalance());
    }

    @Test
    @DisplayName("test withdraw amount more than allows current balance")
    public void testWithdraw_expectedFalse() {

        double withdrawAmount = 1001;
        transactionResult = debitAccount.withdraw(withdrawAmount);

        assertFalse(transactionResult);
        assertTrue(debitAccount.getBalance().compareTo(BigDecimal.ZERO) >= 0);
        assertEquals(startBalance, debitAccount.getBalance());
    }
}