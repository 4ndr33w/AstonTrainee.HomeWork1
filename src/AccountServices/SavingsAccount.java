package AccountServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.Abstractions.Interfaces.InterestBearing;
import Models.ClientAccount;

import java.math.BigDecimal;

public class SavingsAccount extends BankAccount implements InterestBearing {

    private double interestRate = 0.05;
    private double monthlyInterestRate = interestRate / 12;
    private ClientAccount clientAccount;

    public SavingsAccount(ClientAccount clientAccount) {
        super(clientAccount);
    }

    @Override
    public Boolean withdraw(double amount) {

        if (balance.compareTo(BigDecimal.valueOf(amount)) >= 0) {
            balance = balance.subtract(BigDecimal.valueOf(amount));
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal applyInterest() {
        BigDecimal balanceInteresed = balance.multiply(BigDecimal.valueOf(monthlyInterestRate));
        balance = balance.add(balanceInteresed);
        return balance;
    }

    @Override
    public void deposit(double amount) {
        BigDecimal sum = balance.add(BigDecimal.valueOf(amount));
        BigDecimal monthlyInterestedSum = sum.multiply(BigDecimal.valueOf(monthlyInterestRate));

        balance = balance.add(monthlyInterestedSum).add(BigDecimal.valueOf(amount));
    }

    // подумал, что стоит создать перегруженный метод с параметром,
    // который будет указывать на срок вклада
    public void deposit(double amount, int depositTerm) {

        double interestRate = monthlyInterestRate * depositTerm;
        BigDecimal sum = balance.add(BigDecimal.valueOf(amount));

        balance = balance.add(sum.multiply(BigDecimal.valueOf(interestRate)).add(BigDecimal.valueOf(amount)));
    }
}
