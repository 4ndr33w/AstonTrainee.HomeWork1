package Tests.AccountServices;

import AccountServices.CreditAccount;
import Models.ClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class CreditAccountTests {

    private BigDecimal startBalance;
    private double transactionFee;
    private CreditAccount creditAccount;

    @BeforeEach
    public void setUp() {
        startBalance = BigDecimal.valueOf(1000);
        transactionFee = 0.01;
        ClientModel user = new ClientModel(1, "Vasya Pupkin", startBalance);
        creditAccount = new CreditAccount(user);
    }

    @Test
    @DisplayName("test withdraw amount that fits within the credit limit")
    public void testWithdraw_expectedTrue() {
        double withdrawAmount = 4940;
        Boolean transactionResult = creditAccount.withdraw(withdrawAmount);
        BigDecimal expectedBalance = startBalance.subtract(BigDecimal.valueOf(withdrawAmount + withdrawAmount * transactionFee));

        assertTrue(transactionResult);
        assertEquals(expectedBalance, creditAccount.getBalance().stripTrailingZeros());
    }

    @Test
    @DisplayName("test withdraw amount more than allows credit limit")
    public void testWithdraw_expectedFalse() {
        double withdrawAmount = 6002;
        Boolean transactionResult = creditAccount.withdraw(withdrawAmount);

        assertFalse(transactionResult);
        assertEquals(startBalance, creditAccount.getBalance());
    }

    @Test
    @DisplayName("test incoming deposit amount 6000 up to balance")
    public void testDeposit() {
        double depositAmount = 6000;
        BigDecimal expectedBalance = startBalance.add(BigDecimal.valueOf(depositAmount));;
        creditAccount.deposit(depositAmount);
        BigDecimal actualBalance = creditAccount.getBalance();

        assertTrue(creditAccount.getBalance().compareTo(BigDecimal.valueOf(-5000)) >= 0);
        assertEquals(expectedBalance.stripTrailingZeros(), actualBalance.stripTrailingZeros());
    }

    @Test
    @DisplayName("test transaction fee when incoming amount 1000 up to balance")
    public void testApplyFee() {
        double amount = 1000;
        BigDecimal fee = creditAccount.applyFee(amount);
        BigDecimal expectedFee = BigDecimal.valueOf(amount * transactionFee);

        assertEquals(expectedFee.stripTrailingZeros(), fee.stripTrailingZeros());
    }
}