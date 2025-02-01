package AccountServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.Abstractions.Interfaces.InterestBearing;
import Models.ClientAccount;

public class SavingsAccount extends BankAccount implements InterestBearing {

    private double interestRate = 0.05;
    private double monthlyInterestRate = interestRate / 12;
    ClientAccount clientAccount;

    public SavingsAccount(ClientAccount clientAccount) {
        super(clientAccount);
    }

    @Override
    public Boolean withdraw(double amount) {
        return null;
    }

    @Override
    public double applyInterest() {
        balance += balance * monthlyInterestRate;
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount * monthlyInterestRate;
    }
}
