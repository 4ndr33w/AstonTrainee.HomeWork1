package AccountServices.Abstractions.Interfaces;

import Resources.StaticResources;

public interface TransactionValidator{

    public default boolean validate(double amount) {
        if (amount <= StaticResources.TRANSACTION_LIMIT) {
            return true;
        }
        return false;
    }
}
