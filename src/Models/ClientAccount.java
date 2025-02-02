package Models;

import java.math.BigDecimal;

public class ClientAccount {
    private long id;
    private String name;
    private BigDecimal balance;

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public long getId() { return id; }

    public ClientAccount(long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
}
