package Tests.AccountServices;

import AccountServices.SavingsAccount;
import Models.ClientAccount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingAccountTests {
    private BigDecimal startBalance = 1000;
    ClientAccount user = new ClientAccount(1, "Vasya Pupkin", startBalance );
    SavingsAccount debitAccount = new SavingsAccount(user);
    Boolean withdrawResult;

    private double interestRate = 0.05;
    private double monthlyInterestRate = interestRate / 12;

    @Test
    public void applyInterestSuccess() {

        double expectedResult = startBalance + startBalance * monthlyInterestRate;
        debitAccount.applyInterest();

        assertEquals(expectedResult, debitAccount.getBalance() );

        System.out.println(debitAccount.getBalance());
    }

    @Test
    public void depositSuccess() {

        double depositAmount = 100;
        double sum = startBalance + depositAmount;
        double expectedResult = sum + sum * monthlyInterestRate;
        debitAccount.deposit(depositAmount);

        assertEquals(expectedResult, debitAccount.getBalance() );

        System.out.println(debitAccount.getBalance());
    }

    @Test
    public void depositWithTermParameterSuccess() {
        double depositAmount = 100;
        int depositTerm = 6;
        double sum = startBalance + depositAmount;
        double expectedResult = sum + (sum * monthlyInterestRate * depositTerm);
        debitAccount.deposit(depositAmount, depositTerm);

        assertEquals(expectedResult, debitAccount.getBalance() );

        System.out.println(debitAccount.getBalance());
    }
}
