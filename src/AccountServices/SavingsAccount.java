package AccountServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.Abstractions.Interfaces.InterestBearing;
import Models.ClientModel;
import Resources.StaticResources;

import java.math.BigDecimal;

public class SavingsAccount extends BankAccount implements InterestBearing {

    private static final double INTEREST_RATE = StaticResources.INTEREST_RATE;
    private static final double MONTHLY_INTEREST_RATE = INTEREST_RATE / 12;

    public SavingsAccount(ClientModel clientModel) {
        super(clientModel);
    }

    @Override
    public Boolean withdraw(double amount) {

        if (balance.compareTo(BigDecimal.valueOf(amount)) >= 0) {
            balance = balance.subtract(BigDecimal.valueOf(amount)).stripTrailingZeros();
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal applyInterest() {
        BigDecimal balanceInterested = balance.multiply(BigDecimal.valueOf(MONTHLY_INTEREST_RATE));
        balance = balance.add(balanceInterested);

        return balance.stripTrailingZeros();
    }

    @Override
    public void deposit(double amount) {
        BigDecimal sum = balance.add(BigDecimal.valueOf(amount));
        BigDecimal monthlyInterestedSum = sum.multiply(BigDecimal.valueOf(MONTHLY_INTEREST_RATE));

        balance = balance.add(monthlyInterestedSum).add(BigDecimal.valueOf(amount)).stripTrailingZeros();
    }

    // подумал, что стоит создать перегруженный метод с параметром,
    // который будет указывать на срок вклада
    public void deposit(double amount, int depositTerm) {
        double interestRate = MONTHLY_INTEREST_RATE * depositTerm;
        BigDecimal sum = balance.add(BigDecimal.valueOf(amount));

        balance = balance.add(sum.multiply(BigDecimal.valueOf(interestRate)).add(BigDecimal.valueOf(amount))).stripTrailingZeros();
    }
}
