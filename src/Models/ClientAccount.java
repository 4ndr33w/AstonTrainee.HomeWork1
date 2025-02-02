package Models;

import java.math.BigDecimal;

public class ClientAccount {
    private long id;
    private String name;
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public ClientAccount(long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
}
