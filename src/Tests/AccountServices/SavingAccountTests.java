package Tests.AccountServices;

import AccountServices.SavingsAccount;
import Models.ClientModel;
import Resources.StaticResources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingAccountTests {

    private double MONTHLY_INTEREST_RATE;
    private BigDecimal startBalance;
    private SavingsAccount debitAccount;

    @BeforeEach
    public void setUp() {
        double INTEREST_RATE = StaticResources.INTEREST_RATE;
        MONTHLY_INTEREST_RATE = INTEREST_RATE / 12;
        startBalance = BigDecimal.valueOf(1000);
        ClientModel user = new ClientModel(1, "Vasya Pupkin", startBalance );
        debitAccount = new SavingsAccount(user);
    }

    @Test
    @DisplayName("test interestRate apply to current balance")
    public void testApplyInterest() {
        BigDecimal expectedResult = startBalance.add(startBalance.multiply(BigDecimal.valueOf(MONTHLY_INTEREST_RATE)));
        debitAccount.applyInterest();

        assertEquals(expectedResult, debitAccount.getBalance());
    }

    @Test
    @DisplayName("test incoming deposit amount 100 up to balance with monthly interest rate applied to result balance")
    public void testDeposit() {
        double depositAmount = 100;
        BigDecimal sum = startBalance.add(BigDecimal.valueOf(depositAmount));
        BigDecimal expectedResult = sum.add(sum.multiply(BigDecimal.valueOf(MONTHLY_INTEREST_RATE)));

        debitAccount.deposit(depositAmount);

        assertEquals(expectedResult.stripTrailingZeros(), debitAccount.getBalance() );
    }

    @Test
    @DisplayName("test incoming deposit amount 100 up to balance with 6 months term interestRate applied to result balance")
    public void depositWithTermParam() {
        double depositAmount = 100;
        int depositTerm = 6;
        BigDecimal sum = startBalance.add(BigDecimal.valueOf(depositAmount));
        BigDecimal expectedResult = sum.add(sum.multiply(BigDecimal.valueOf(MONTHLY_INTEREST_RATE * depositTerm)));

        debitAccount.deposit(depositAmount, depositTerm);

        assertEquals(expectedResult.stripTrailingZeros(), debitAccount.getBalance());
    }
}
