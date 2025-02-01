package Resources;

public class StaticResources {

    public static final double TRANSACTION_LIMIT = Double.parseDouble(Configuration.getProperties().getProperty("TRANSACTION_LIMIT"));
    public static final double CREDIT_LIMIT = Double.parseDouble(Configuration.getProperties().getProperty("CREDIT_LIMIT"));
    public static final double TRANSACTION_FEE= Double.parseDouble(Configuration.getProperties().getProperty("TRANSACTION_FEE"));
    public static final double INTEREST_RATE= Double.parseDouble(Configuration.getProperties().getProperty("INTEREST_RATE"));
}

