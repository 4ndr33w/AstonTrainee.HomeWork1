package Models;

public class ClientAccount {
    private long id;
    private String name;
    private double balance;

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public long getId() { return id; }

    public ClientAccount(long id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
}
