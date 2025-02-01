package AccountServices.Abstractions.Interfaces;

import java.math.BigDecimal;

public interface TransactionFee {

    public BigDecimal applyFee(double amount);
}
