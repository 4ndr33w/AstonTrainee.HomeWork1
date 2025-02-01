package AccountServices;

import AccountServices.Abstractions.BankAccount;
import AccountServices.Abstractions.Interfaces.TransactionFee;
import AccountServices.Abstractions.Interfaces.TransactionValidator;
import Models.ClientModel;
import Resources.StaticResources;

import java.math.BigDecimal;

public class CreditAccount extends BankAccount implements TransactionFee, TransactionValidator {

    public CreditAccount(ClientModel clientModel) {
        super(clientModel);
    }

    @Override
    public Boolean withdraw(double amount) {
        BigDecimal fee = applyFee(amount);
        BigDecimal feeWithAmount = fee.add(new BigDecimal(amount));
        BigDecimal balanceAfterTransaction = balance.subtract(feeWithAmount);

        Boolean isValidated = validate(amount);
        Boolean isTransactionCanBeExecuted = balanceAfterTransaction.
                compareTo(BigDecimal.valueOf( StaticResources.CREDIT_LIMIT )) >= 0;

        if (isTransactionCanBeExecuted && isValidated) {
            balance = balanceAfterTransaction;
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal applyFee(double amount) {
        BigDecimal decimalAmount = BigDecimal.valueOf(amount);
        BigDecimal decimalTransactionFee = BigDecimal.valueOf(StaticResources.TRANSACTION_FEE);
        return decimalAmount.multiply(decimalTransactionFee).stripTrailingZeros();
    }
}