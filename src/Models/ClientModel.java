package Models;

import java.math.BigDecimal;

public class ClientModel {
    private long id;
    private String name;
    private BigDecimal balance;

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public long getId() {
        return id;
    }

    public ClientModel(long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
}
