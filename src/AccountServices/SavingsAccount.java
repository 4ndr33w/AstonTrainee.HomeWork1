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

        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public double applyInterest() {
        balance += balance * monthlyInterestRate;
        return balance;
    }

    @Override
    public void deposit(double amount) {
        double sum = balance + amount;
        balance += amount + sum * monthlyInterestRate;
    }

    // подумал, что стоит создать перегруженный метод с параметром,
    // который будет указывать на срок вклада
    public void deposit(double amount, int depositTerm) {

        double interestRate = monthlyInterestRate * depositTerm;
        double sum = balance + amount;
        balance += amount + sum * interestRate;
    }
}
