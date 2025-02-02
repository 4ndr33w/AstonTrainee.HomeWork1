package AccountServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.Abstractions.Interfaces.InterestBearing;
import Models.ClientAccount;

import java.math.BigDecimal;

public class SavingsAccount extends BankAccount implements InterestBearing {

    private final double INTEREST_RATE = 0.05;
    private final double MONTHLY_INTEREST_RATE = INTEREST_RATE / 12;

    private ClientAccount clientAccount;

    public SavingsAccount(ClientAccount clientAccount) {
        super(clientAccount);
    }

    @Override
    public Boolean withdraw(double amount) {

        BigDecimal balanceAfterTransaction = balance.subtract(new BigDecimal(amount));
        if (balance.compareTo(new BigDecimal(amount)) >= 0) {
            balance = balanceAfterTransaction;
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal applyInterest() {
        BigDecimal monthlyIntetestedBalance = balance.multiply(new BigDecimal(MONTHLY_INTEREST_RATE));
        balance = balance.add(monthlyIntetestedBalance);
        return balance;
    }

    @Override
    public void deposit(double amount) {
        BigDecimal sum = balance.add((new BigDecimal(amount)));
        BigDecimal monthlyInterestedSum = sum.multiply(new BigDecimal(MONTHLY_INTEREST_RATE));
        balance = balance.add(new BigDecimal(amount)).add(monthlyInterestedSum);
    }

    // подумал, что стоит создать перегруженный метод с параметром,
    // который будет указывать на срок вклада
    public void deposit(double amount, int depositTerm) {
        double interestRate = MONTHLY_INTEREST_RATE * depositTerm;

        BigDecimal sum = balance.add((new BigDecimal(amount)));
        BigDecimal monthlyInterestedSum = sum.multiply(new BigDecimal(interestRate));
        balance = balance.add(new BigDecimal(amount)).add(monthlyInterestedSum);
    }
}
